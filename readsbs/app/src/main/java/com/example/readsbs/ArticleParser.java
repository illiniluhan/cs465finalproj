package com.example.readsbs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;

public class ArticleParser {

    public static class ParsedArticle {
        private String title;
        private String content;
        private String author;
        private String publishDate;
        private double estimatedReadingTime;
        private List<ParsedSection> sections;

        public ParsedArticle(String title, String content, String author, String publishDate, double estimatedReadingTime, List<ParsedSection> sections) {
            this.title = title;
            this.content = content;
            this.author = author;
            this.publishDate = publishDate;
            this.estimatedReadingTime = estimatedReadingTime;
            this.sections = sections;
        }

        // Getters
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public String getAuthor() { return author; }
        public String getPublishDate() { return publishDate; }
        public double getEstimatedReadingTime() { return estimatedReadingTime; }
        public List<ParsedSection> getSections() { return sections; }
    }

    public static class ParsedSection {
        private String header;
        private String content;
        private double estimatedTime;

        public ParsedSection(String header, String content, double estimatedTime) {
            this.header = header;
            this.content = content;
            this.estimatedTime = estimatedTime;
        }

        // Getters
        public String getHeader() { return header; }
        public String getContent() { return content; }
        public double getEstimatedTime() { return estimatedTime; }
    }

    public static ParsedArticle fetchAndParseArticle(String url) throws Exception {
        Document document = Jsoup.connect(url).get();

        // Extract title, author, publish date, and main content
        String title = document.select("h1").text();
        String author = document.select("meta[name=author]").attr("content");
        String publishDate = document.select("meta[name=date]").attr("content");

        Element contentDiv = document.selectFirst("div.article__body");
        if (contentDiv == null) throw new Exception("Content not found");

        List<ParsedSection> sections = new ArrayList<>();
        StringBuilder contentBuilder = new StringBuilder();
        String currentHeader = "Introduction";

        for (Element element : contentDiv.children()) {
            if (element.tagName().matches("h[2-6]")) {
                if (contentBuilder.length() > 0) {
                    sections.add(new ParsedSection(
                            currentHeader, contentBuilder.toString().trim(), estimateReadingTime(contentBuilder.toString())
                    ));
                    contentBuilder.setLength(0);
                }
                currentHeader = element.text();
            } else if (element.tagName().equals("p")) {
                contentBuilder.append(element.text()).append("\n\n");
            }
        }

        if (contentBuilder.length() > 0) {
            sections.add(new ParsedSection(
                    currentHeader, contentBuilder.toString().trim(), estimateReadingTime(contentBuilder.toString())
            ));
        }

        return new ParsedArticle(title, String.join("\n\n", sections.toString()), author, publishDate, estimateTotalTime(sections), sections);
    }

    private static double estimateReadingTime(String content) {
        // Calculate reading time based on words (200 words per minute)
        int wordCount = content.split("\\s+").length;
        return wordCount / 200.0;
    }

    private static double estimateTotalTime(List<ParsedSection> sections) {
        double totalTime = 0;
        for (ParsedSection section : sections) {
            totalTime += section.getEstimatedTime();
        }
        return totalTime;
    }
}
