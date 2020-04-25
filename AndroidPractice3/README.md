# 实验三说明文档 
实验检查关于**简单适配器**，**自定义预设**,**XML菜单**，**上下文菜单**的了解与使用,都是关系到了使用ListView这个组件</br>
## 开始界面
应用启动开始提供特定菜单，即设定了关于小实验的转换按钮</br>
![StartScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E4%B8%BB%E7%95%8C%E9%9D%A2.png)
## 简单适配器
主要是使用HashMap及List集合将值存放，后通过简单适配器形成键-值对应关系，从而一一填入事先写好的ListView布局文件</br>
```
ArrayList<HashMap<String,String>> listItems = new ArrayList<>();
        for(int i = 0;i<names.length;i++){
            HashMap<String,String> listItem = new HashMap<>();
            listItem.put("gettextView",names[i]);
            listItem.put("getimage",String.valueOf(imageIds[i]));
            listItems.add(listItem);
}
```
```
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.simpleadapter_item,
                new String[]{"gettextView","getimage"},
                new int[]{R.id.gettextView,R.id.getimageView});
        ListView list =(ListView) findViewById(R.id.SimpleAdapterList);
        list.setAdapter(simpleAdapter);
```
![SimpleAdapter](https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E7%AE%80%E5%8D%95%E9%80%82%E9%85%8D%E5%99%A8.png)
## 自定义对话框
线性布局创建出自定义对话框</br>
![AlertDialog](https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E8%87%AA%E5%AE%9A%E4%B9%89%E5%AF%B9%E8%AF%9D%E6%A1%86.png)
## XML菜单栏
XML菜单实验跟老师视频教学讲到的实现操作略显不同，在老师的讲述中是将菜单栏上的组件写在布局文件中直接方法调用，而在我完成的实验中，我将菜单栏中需要用到组件元素放在了menu文件夹中，分开调用，减少耦合性</br>
```<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:title="字体大小">
        <menu>
            <item
                android:id="@+id/font_small"
                android:title="小号"/>
            <item
                android:id="@+id/font_middle"
                android:title="中号"/>
            <item
                android:id="@+id/font_big"
                android:title="大号"/>
        </menu>
    </item>

    <item
        android:id="@+id/normal"
        android:title="普通菜单项"
        />

    <item android:title="字体颜色">
        <menu>
            <item
                android:id="@+id/color_black"
                android:title="黑色"/>
            <item
                android:id="@+id/color_red"
                android:title="红色"/>
        </menu>
    </item>
</menu>
```
剩下的操作基本就是老师上课提到的，就不再赘述，看效果截图</br>
![textsize]（https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E5%AD%97%E4%BD%93%E5%A4%A7%E5%B0%8F.png）
![common](https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E6%99%AE%E9%80%9A%E8%8F%9C%E5%8D%95%E9%A1%B9.png)
![textcoloe](https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E5%AD%97%E4%BD%93%E9%A2%9C%E8%89%B2.png)
## 上下文菜单操作
这个部分我认为对我来说比较难，所以我借鉴了老师以及同学的经验与教训，最终完成。</br>
关键在于老师提供的代码片段的基础上编写</br>
![ActionMode](https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E4%B8%8A%E4%B8%8B%E6%96%87%E8%8F%9C%E5%8D%95.png)
