package com.example.practice3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class A_XML extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        textView = findViewById(R.id.xmltextView);
    }

    /* 创建操作菜单 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.top_setting, menu);
        return true;
    }

    /* 设置选中操作项对应事件 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.font_small:
                textView.setTextSize(10 * 2);
                break;
            case R.id.font_middle:
                textView.setTextSize(15 * 2);
                break;
            case R.id.font_big:
                textView.setTextSize(20 * 2);
                break;
            case R.id.normal:
                Toast.makeText(A_XML.this, "这是普通菜单项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.color_red:
                textView.setTextColor(Color.RED);
                break;
            case R.id.color_black:
                textView.setTextColor(Color.BLACK);
                break;
        }
        return true;
    }
}
