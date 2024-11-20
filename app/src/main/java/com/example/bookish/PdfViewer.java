package com.example.bookish;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PdfViewer extends AppCompatActivity {

    public static String url;

    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        // ++++++++++++++++++++++
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        // ++++++++++++++++++++
        webView = findViewById(R.id.webView);

        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());






    }
}