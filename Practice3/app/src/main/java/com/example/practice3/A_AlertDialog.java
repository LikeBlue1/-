package com.example.practice3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class A_AlertDialog extends AppCompatActivity {
    private Button btn_alertdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog);

        btn_alertdialog = findViewById(R.id.alertdialog);

        btn_alertdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();                                 //点击弹出自定义对话框
            }
        });
    }
//    创建自定义对话框
        public void createDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();      //获取布局解析

//        设置对话框布局操作
            builder.setView(inflater.inflate(R.layout.alertdialog_item, null))
                    //添加操作项目
                    .setPositiveButton(R.string.sign_in, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //实现登录账户功能（不去实现），设为空
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.create();
            builder.show();

        }


    }
