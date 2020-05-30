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
