package com.example.readsbs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.readsbs.R;
import com.example.readsbs.repository.ArticleRepository;
import java.util.concurrent.Executors;

public class ImportArticleActivity extends AppCompatActivity {

    private EditText urlInput;
    private Button importButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_article);

        urlInput = findViewById(R.id.url_input);
        importButton = findViewById(R.id.import_button);

        importButton.setOnClickListener(v -> {
            String url = urlInput.getText().toString();
            if (!url.isEmpty()) {
                importArticle(url);
            } else {
                Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void importArticle(String url) {
        Executors.newSingleThreadExecutor().execute(() -> {
            boolean success = ArticleRepository.importArticle(url, getApplicationContext());
            runOnUiThread(() -> {
                if (success) {
                    Toast.makeText(this, "Article imported successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to import article", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
