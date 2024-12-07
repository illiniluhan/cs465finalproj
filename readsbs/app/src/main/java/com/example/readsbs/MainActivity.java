package com.example.readsbs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText path;
    private Button submit_btn;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private String uid;
    private String username;

    private LinearLayout queueLayout;
    private LinearLayout articlesContainer;  // Container for individual article items
    private LinearLayout actionsContainer;   // Container holding cancel/import buttons
    private Button importButton;
    private Button cancelButton;
    private TextView queueTitle;

    private ArrayList<String> articleQueue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser fbuser = FirebaseAuth.getInstance().getCurrentUser();
        uid = fbuser != null ? fbuser.getUid() : null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        if (username != null && !username.isEmpty()) {
            Toast.makeText(MainActivity.this, username + " Logged in", Toast.LENGTH_SHORT).show();
        }

        submit_btn = findViewById(R.id.submit_path);
        path = findViewById(R.id.import_path);
        queueLayout = findViewById(R.id.queue_layout);
        articlesContainer = findViewById(R.id.articles_container);
        actionsContainer = findViewById(R.id.actions_container);
        importButton = findViewById(R.id.btn_import_article);
        cancelButton = findViewById(R.id.btn_cancel_article);
        queueTitle = findViewById(R.id.queue_title);

        queueLayout.setVisibility(View.GONE);

        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_import) {
                // Remain on import screen
            } else if (checkedId == R.id.navigation_read) {
                performClickAnimation(findViewById(R.id.navigation_read), BookActivity.class);
            } else if (checkedId == R.id.navigation_analysis) {
                performClickAnimation(findViewById(R.id.navigation_analysis), AnalysisActivity.class);
            }
        });
        ((RadioButton) findViewById(R.id.navigation_import)).setChecked(true);

        submit_btn.setOnClickListener(v -> {
            submit_to_parse();
        });

        importButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, articleQueue.size() + " articles imported to library!", Toast.LENGTH_SHORT).show();
            clearQueue();
        });

        cancelButton.setOnClickListener(v -> {
            clearQueue();
        });
    }

    private void submit_to_parse() {
        String parse_path = path.getText().toString().trim();

        if (parse_path.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter a valid link.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidUrl(parse_path)) {
            showInvalidLinkDialog();
        } else {
            addArticleToQueue();
        }
    }

    private boolean isValidUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }

        try {
            Uri uri = Uri.parse(url);
            String scheme = uri.getScheme();
            String host = uri.getHost();

            if (scheme != null) {
                return (scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https"))
                        && host != null
                        && host.toLowerCase().endsWith(".com");
            } else {
                return url.toLowerCase().endsWith(".com");
            }
        } catch (Exception e) {
            return false;
        }
    }

    private void showInvalidLinkDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Invalid Link")
                .setMessage("The link you entered is not valid. Would you like to retry?")
                .setPositiveButton("Retry", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    path.setText("");
                })
                .show();
    }

    private void addArticleToQueue() {
        int articleNumber = articleQueue.size() + 1;
        String articleName = "Article " + articleNumber;
        articleQueue.add(articleName);

        // Show the queue layout if hidden
        if (queueLayout.getVisibility() == View.GONE) {
            queueLayout.setVisibility(View.VISIBLE);
        }

        // Update the UI to show the new article
        TextView articleView = new TextView(this);
        articleView.setText(articleName);
        articleView.setTextSize(18f);
        articleView.setTextColor(getResources().getColor(android.R.color.black));
        articleView.setPadding(0, 8, 0, 8);
        articlesContainer.addView(articleView);

        // Clear the input field
        path.setText("");
    }

    private void clearQueue() {
        articleQueue.clear();
        articlesContainer.removeAllViews();
        queueLayout.setVisibility(View.GONE);
    }

    private void performClickAnimation(View view, Class<?> destinationActivity) {
        view.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(100)
                .withEndAction(() -> view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .withEndAction(() -> {
                            startActivity(new Intent(MainActivity.this, destinationActivity));
                        })
                        .start())
                .start();
    }
}
