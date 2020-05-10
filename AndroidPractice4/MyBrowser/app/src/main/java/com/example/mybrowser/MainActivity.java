package com.example.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(    Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Uri uriData = intent.getData();
        URL url = null;
        //getURL
        try{
            url = new URL(uriData.getScheme(), uriData.getHost(), uriData.getPath());
        }catch (Exception e) {
            e.printStackTrace();
        }
        //将URL传入浏览器调用
        openBrowser(url);
    }

    private void openBrowser(URL url) {
        WebView webView = findViewById(R.id.webView);
        //加载Web资源
        webView.loadUrl(url.toString());
        //默认使用第三方或系统默认浏览器打开网页的行为
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true打开WebView，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

}
