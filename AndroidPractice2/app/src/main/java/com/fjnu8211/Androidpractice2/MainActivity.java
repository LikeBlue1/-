package com.fjnu8211.Androidpractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_linearLayout;
    private Button btn_constraintLayout;
    private Button btn_tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置线性布局按钮
        btn_linearLayout = findViewById(R.id.linearLayout);
        btn_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinearLayout.class);
                startActivity(intent);
            }
        });

        //设置约束布局按钮
        btn_constraintLayout = findViewById(R.id.constraintLayout);
        btn_constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConstraintLayout.class);
                startActivity(intent);
            }
        });

        //设置表格布局按钮
        btn_tableLayout = findViewById(R.id.tableLayout);
        btn_tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TableLayout.class);
                startActivity(intent);
            }
        });
    }
}
