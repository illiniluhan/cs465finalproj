package com.example.readsbs.repository;

import android.content.Context;
import com.example.readsbs.data.ArticleEntity;
import com.example.readsbs.data.SectionEntity;
import com.example.readsbs.data.AppDatabase;
import com.example.readsbs.ArticleParser;
import com.example.readsbs.ArticleParser.ParsedArticle;
import com.example.readsbs.ArticleParser.ParsedSection;

public class ArticleRepository {

    public static boolean importArticle(String url, Context context) {
        try {
            ParsedArticle articleContent = ArticleParser.fetchAndParseArticle(url);

            AppDatabase db = AppDatabase.getInstance(context);

            // Insert Article
            ArticleEntity article = new ArticleEntity(
                    url, articleContent.getTitle(), articleContent.getContent(),
                    articleContent.getAuthor(), articleContent.getPublishDate(),
                    articleContent.getEstimatedReadingTime()
            );
            long articleId = db.articleDao().insertArticle(article);

            // Insert Sections
            for (ParsedSection section : articleContent.getSections()) {
                SectionEntity sectionEntity = new SectionEntity(
                        (int) articleId, section.getHeader(), section.getContent(), section.getEstimatedTime()
                );
                db.sectionDao().insertSection(sectionEntity);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
