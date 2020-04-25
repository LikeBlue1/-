＃实验三说明文档
实验检查关于**简单适配器**，**自定义预设**，** XML定义菜单**的了解和使用</br>
##开始界面
应用启动开始提供特定菜单，即设定了关于小实验的转换按钮</br>
(https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E4%B8%BB%E7%95%8C%E9%9D%A2.png)
##简单适配器
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
(https://github.com/LikeBlue1/-/blob/master/AndroidPractice3/%E7%AE%80%E5%8D%95%E9%80%82%E9%85%8D%E5%99%A8.png)
##自定义对话框
