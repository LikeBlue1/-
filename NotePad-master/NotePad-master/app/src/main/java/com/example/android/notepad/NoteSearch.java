package com.example.android.notepad;

import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

//这个activity其实就是NotesList的翻版，就是除去了其他元素组件，单单只显示通过搜索查到的内容：标题、日期，以listview的方式
//绑定的布局文件是search_item，简单的listview
public class NoteSearch extends ListActivity {
    private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,

    };
    private static String titleText;

    public static Intent newIntent(Context packageContext, String titletext){
        titleText=titletext;
        Intent intent=new Intent(packageContext,NoteSearch.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item);
        Intent intent = getIntent();
        if (intent.getData() == null) {
            intent.setData(NotePad.Notes.CONTENT_URI);
        }
        //搜索语句，搜索结果存放在cursor中，使用SimpleCursorAdapter进行数据的装填
        String selection = NotePad.Notes.COLUMN_NAME_TITLE + " Like ? ";
        String[] selectionArgs = { "%"+titleText+"%" };
        Cursor cursor = managedQuery(
                getIntent().getData(),
                PROJECTION,
                selection,
                selectionArgs,
                NotePad.Notes.DEFAULT_SORT_ORDER
        );
        String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,  NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE };
        int[] viewIDs = { android.R.id.text1 , R.id.timetext };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.noteslist_item,
                cursor,
                dataColumns,
                viewIDs
        );
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // 从传入的URI和行ID构造新的URI
        Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);
        // 从传入的意图获取操作
        String action = getIntent().getAction();
        // 处理对数据的请求
        if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {
            // Sets the result to return to the component that called this Activity. The
            // result contains the new URI
            setResult(RESULT_OK, new Intent().setData(uri));
        } else {
            //隐式intent，其效果是调用NoteEdit
            startActivity(new Intent(Intent.ACTION_EDIT, uri));
        }
    }
}
