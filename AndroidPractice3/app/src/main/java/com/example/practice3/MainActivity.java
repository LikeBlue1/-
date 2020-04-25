package com.example.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        设置SimpleAdapter按钮响应
        btn_1 = findViewById(R.id.SimpleAdapter);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, A_SimpleAdapter.class);
                startActivity(intent);
            }
        });

//        设置AlertDialog按钮响应
        btn_2 = findViewById(R.id.AlertDialog);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, A_AlertDialog.class);
                startActivity(intent);
            }
        });

//        设置XML按钮响应
        btn_3 = findViewById(R.id.XML);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, A_XML.class);
                startActivity(intent);
            }
        });

//        设置AcitionMode按钮响应
        btn_4 = findViewById(R.id.ActionMode);
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, A_ActionMode.class);
                startActivity(intent);
            }
        });
    }

}
