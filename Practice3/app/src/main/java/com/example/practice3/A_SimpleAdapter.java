package com.example.practice3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class A_SimpleAdapter extends AppCompatActivity {


    //图片文字数据
    private String[] names = new String[]{"Lion" ,"Tiger" ,"Monkey","Dog","Cat","Elephant"};

    private  int[] imageIds = new int[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpleadapter);


//        创建List集合
        ArrayList<HashMap<String,String>> listItems = new ArrayList<>();
        for(int i = 0;i<names.length;i++){
            HashMap<String,String> listItem = new HashMap<>();
            listItem.put("gettextView",names[i]);
            listItem.put("getimage",String.valueOf(imageIds[i]));
            listItems.add(listItem);

        }
//        创建一个simpleAdapter，形成键-值关系对

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.simpleadapter_item,
                new String[]{"gettextView","getimage"},
                new int[]{R.id.gettextView,R.id.getimageView});
        ListView list =(ListView) findViewById(R.id.SimpleAdapterList);
        list.setAdapter(simpleAdapter);

        //listView_items点击事件——消息弹窗
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), names[position], Toast.LENGTH_LONG).show();     //Toast.LENGTH_LONG（3.5秒）
            }
        });
    }




}

