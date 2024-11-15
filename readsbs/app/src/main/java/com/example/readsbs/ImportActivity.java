package com.example.readsbs;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

public class ImportActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private TextView textViewResult;
    private DatabaseReference userRef;
    private StorageReference storageReference;
    private final OkHttpClient client = new OkHttpClient();
    private static final String TAG = "ImportActivity";

    private String userId = "exampleUserId"; // Placeholder for authenticated user ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        editTextUrl = findViewById(R.id.editTextUrl);
        textViewResult = findViewById(R.id.textViewResult);
        Button buttonFetch = findViewById(R.id.buttonFetch);

        // Initialize Firebase references
        userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        storageReference = FirebaseStorage.getInstance().getReference("article_files");

        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = editTextUrl.getText().toString().trim();
                if (!TextUtils.isEmpty(url)) {
                    fetchAndProcessArticle(url);
                } else {
                    Toast.makeText(ImportActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchAndProcessArticle(String url) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(ImportActivity.this, "Failed to fetch article", Toast.LENGTH_SHORT).show());
                Log.e(TAG, "Failed to fetch article", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String htmlContent = response.body().string();
                    Document document = Jsoup.parse(htmlContent);
                    processArticle(document);
                } else {
                    runOnUiThread(() -> Toast.makeText(ImportActivity.this, "Failed to fetch article", Toast.LENGTH_SHORT).show());
                    Log.e(TAG, "Response not successful");
                }
            }
        });
    }

    private void processArticle(Document document) {
        // Extract the article title
        String articleTitle = document.select("h1.citation__title").text();
        Log.d(TAG, "Article Title: " + articleTitle);

        // Extract the authors
        Elements authorElements = document.select("span.loa__author-name");
        List<String> authorsList = new ArrayList<>();
        for (Element authorElement : authorElements) {
            authorsList.add(authorElement.text());
        }
        String authors = TextUtils.join(", ", authorsList);
        Log.d(TAG, "Authors: " + authors);

        // Extract the publishing date
        String publishingDate = document.select("span.epub-section__date").text();
        Log.d(TAG, "Publishing Date: " + publishingDate);

        // Extract the abstract
        String abstractText = document.select("div.abstractSection.abstractInFull").text();
        Log.d(TAG, "Abstract: " + abstractText);

        // Extract the body/content
        StringBuilder bodyText = new StringBuilder();
        Elements bodyElements = document.select("div.article__body > p");
        for (Element paragraph : bodyElements) {
            bodyText.append(paragraph.text()).append("\n\n");
        }
        Log.d(TAG, "Body Text: " + bodyText.toString());

        // Calculate reading time using Flesch-Kincaid scale
        int estimatedReadingTime = estimateReadingTime(bodyText.toString());
        Log.d(TAG, "Estimated Reading Time: " + estimatedReadingTime + " minutes");

        // Save to Firebase
        DatabaseReference articleRef = userRef.child("articles").push(); // Create a new article entry

        Map<String, Object> articleData = new HashMap<>();
        articleData.put("title", articleTitle);
        articleData.put("authors", authors);
        articleData.put("publishingDate", publishingDate);
        articleData.put("abstract", abstractText);
        articleData.put("body", bodyText.toString());
        articleData.put("estimatedReadingTime", estimatedReadingTime);
        articleData.put("progress", 0); // Initial progress set to 0%

        articleRef.setValue(articleData)
                .addOnSuccessListener(aVoid -> runOnUiThread(() -> {
                    textViewResult.setText("Article saved successfully!");
                    Log.d(TAG, "Article saved successfully!");
                }))
                .addOnFailureListener(e -> runOnUiThread(() -> {
                    Toast.makeText(ImportActivity.this, "Failed to save article", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Failed to save article", e);
                }));
    }

    private int estimateReadingTime(String text) {
        int totalWords = countWords(text);
        int totalSentences = countSentences(text);
        int totalSyllables = countSyllables(text);

        double fkgl = 0.39 * ((double) totalWords / totalSentences) + 11.8 * ((double) totalSyllables / totalWords) - 15.59;
        Log.d(TAG, "Flesch-Kincaid Grade Level: " + fkgl);

        // Average adult reading speed is about 200 WPM
        double averageWPM = 200;

        // Adjust reading speed based on FKGL; higher grade levels may reduce reading speed
        double adjustedWPM = averageWPM;
        if (fkgl > 12) {
            adjustedWPM *= Math.pow(0.9, fkgl - 12);
        }
        Log.d(TAG, "Adjusted WPM based on FKGL: " + adjustedWPM);

        int readingTimeMinutes = (int) Math.ceil(totalWords / adjustedWPM);
        Log.d(TAG, "Total Words: " + totalWords);
        Log.d(TAG, "Estimated Reading Time: " + readingTimeMinutes + " minutes");

        return Math.max(readingTimeMinutes, 1);
    }

    private int countWords(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    private int countSentences(String text) {
        String[] sentences = text.split("[.!?]+");
        return sentences.length;
    }

    private int countSyllables(String text) {
        int syllableCount = 0;
        String[] words = text.toLowerCase().split("\\s+");
        for (String word : words) {
            syllableCount += countSyllablesInWord(word);
        }
        return syllableCount;
    }

    private int countSyllablesInWord(String word) {
        int count = 0;
        boolean isPrevVowel = false;
        word = word.toLowerCase();
        String vowels = "aeiouy";

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            boolean isVowel = vowels.indexOf(c) >= 0;

            if (isVowel && !isPrevVowel) {
                count++;
            }
            isPrevVowel = isVowel;
        }

        // Remove silent 'e' at the end
        if (word.endsWith("e") && !word.endsWith("le")) {
            count--;
        }

        // Adjust count to be at least 1
        if (count <= 0) {
            count = 1;
        }
        return count;
    }
}
