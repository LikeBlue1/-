# 期中实验说明文档</br>
## 实验要求
下载NotePad源码并作如下需求：</br>
1.NoteList中显示条目增加时间戳显示</br>
2.添加笔记查询功能（根据标题查询）</br>
## 时间戳显示
![righttime](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/righttime.png)</br>
完成这个需求需要:</br>
1.在**noteslist_item**布局文件中加入TextView组件(用于放置时间)与原本的TextView组件形成LinearLayout线性布局</br>
```
 <TextView
        android:id="@+id/timetext"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:paddingLeft="10dp" />
```
2.在**NotesList.Java**文件中，将</br>
```
private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            };
```
更改为：</br>
```
private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,
    };
```
在 **dataColumns** 与 **viewIDs** 数组中添加对应的键-值关系：</br> 
```
String[] dataColumns = {NotePad.Notes.COLUMN_NAME_TITLE, NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE};
```
```
int[] viewIDs = {android.R.id.text1, R.id.timetext};
```
这样做的目的是为了改变从数据库中提取出来的数据增加一项修改时间。</br>
3.需要注意的是，如果只是做这样的更改，可能会遇到如下显示：</br>
![wrongtime](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/wrongtime.png)</br>
查阅的资料显示，我们原本在编辑文本时传入数据库的修改时间的格式不对，显示的是1970年至今的毫秒数，所以需要在**NoteEditor**中修改updateNote方法：</br>
```
 //  values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, System.currentTimeMillis());
       values.put(
       NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,
       android.text.format.DateFormat.format("yyyyMMMdd,HH:mm",System.currentTimeMillis()).toString());
```
## 添加笔记查询
首先，搜索功能肯定需要在界面中体现一个通道，所以我们先在界面中添加一个搜索图标，先实现与用户的交互：</br>
![searchicon](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/searchicon.png)</br>
实现这个需要在**list_options_menu.xml**的菜单文件中添加item组件：</br>
```
<!-- 主界面顶部菜单选项，添加了名为search的item搜索组件-->
    <item
        android:id="@+id/menu_search"
        android:showAsAction="always"
        android:title="Search"
        android:icon="@drawable/searchicon"/>
```
那么接下来需要思考的是这个搜索按钮按下后，我需要得到怎样的响应。通过回忆第三次实验的学习，我想到弹出一个自定义对话框，用来获取用户需要搜索的标题内容，这个对话框应该有取消与搜索两个按钮。</br>
创建**Dialog.java**文件绑定**dialog_item.xml**布局文件。</br>
**dialog_item.xml**布局文件:</br>
```
<?xml version="1.0" encoding="utf-8"?>
<!--搜索框布局，采用嵌套线性布局模式，取消与搜索按钮按1：1权重分配-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:gravity="center"
    android:layout_height="match_parent">
    <EditText
        android:id="@+id/Search_editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="输入搜索标题"
        android:ems="10"
        />
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="center">

        <Button
            android:id="@+id/cancel"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="取消"/>

        <Button
            android:id="@+id/search"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="搜索"/>
    </LinearLayout>
</LinearLayout>
```
**Dialog.java**文件：</br>
```
package com.example.android.notepad;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dialog extends DialogFragment {
    //定义声明取消与搜索按钮组件
    private Button cancel,search;
    //声明搜索编辑框组件
    private EditText SearchEditText;
    //声明String类型的搜索内容
    private String titleText=new String();
    //声明数据库映射内容，也是通过cursor提取的内容
    private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0

    };
    //创建对话框Dialog的生命周期，重写onCreateDialog方法
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        //绑定布局dialog_item界面
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_item,null);

        //定义内容编辑框的映射，通过匿名内部类复写编辑前、编辑时、编辑后三个方法
        SearchEditText=(EditText)view.findViewById(R.id.Search_editText);
        SearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int G, int GG, int GGG) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int G, int GG, int GGG) {
                titleText=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //定义搜索按钮的组件映射，捕获处理响应机制
        search=(Button)view.findViewById(R.id.search);
        //重写onClink方法，加入判断方法，如果输入的内容为空，则显示Toast：“输入不能为空”
        //如果输入的内容不为空，根据cursor返回的值判断查无该相关内容，若无Toast:"未找到该标题"，若有，则通过intent传入NoteSearch
        //值得一提的是，这里的查找方式是模糊查询，也就是说只需要输入标题的一个字母就能找到与之相关的多个标题
        //若需要精确查找，只需将代码更改为  String[] selectionArgs = { titleText }即可
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleText.length()>0){
                    String selection = NotePad.Notes.COLUMN_NAME_TITLE + " Like ? ";
                    String[] selectionArgs = { "%"+titleText+"%" };
                    Cursor cursor = getActivity().managedQuery(
                            getActivity().getIntent().getData(),
                            PROJECTION,
                            selection,
                            selectionArgs,
                            NotePad.Notes.DEFAULT_SORT_ORDER
                    );
                    if (cursor.getCount()>0){
                       startActivity(NoteSearch.newIntent(getContext(),titleText));
                        dismiss();
                    }else {
                        Toast.makeText(getContext(),"抱歉，未找到相关标题",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(),"输入不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //声明cancel组件的映射
        cancel=(Button) view.findViewById(R.id.cancel);
        //点击按钮的响应事件处理，即点击消除
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //对象链，将对话框的上下文内容显示出来
        return new AlertDialog.Builder(getContext()).setView(view).show();
    }
}
```
实现的效果是：点击主界面的搜索图标会弹出对话框，通过获取EditText的内容做出简单判定，并提示相关信息：1.输入内容不能为空 2.输入标题找不到对应内容。</br>
![searchdialog](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/searchdilog.png)
![searchcantempty](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/textcantempty.png)
![textwrong](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/textwrong.png)</br>
原本的设想是我只要点击搜索按钮就直接打开对应标题的记事内容，但是考虑到用户的搜索可能并不准确，需要模糊查找，需要多个条目来装通过模糊查找得到的内容，于是想到了需要一个类似**NotesList**主界面的ListView来装，所以创建**NoteSearch**java文件与**search_item.xml**布局文件：</br>
**search_item.xml**:</br>
```
<?xml version="1.0" encoding="utf-8"?>
<!-- 搜索结果的listview列表查看布局-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <ListView
        android:id="@android:id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
    </ListView>
</LinearLayout>
```
**NoteSearch**java文件:</br>
```
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
```
值得一提的是，这里面使用的模糊查找方法是将搜索条件变为{ "%"+titleText+"%" }，如果需要精确查找，只需要将搜索条件变为{ titleText }即可。</br>
最终需求完成的实验效果：</br>
![search](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/search.png)
![searchresult](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/searchresult.png)
![opentext](https://github.com/LikeBlue1/-/blob/master/NotePad-master/NotePad-master/Picture/opentext.png)
## 实验完成




    
    
