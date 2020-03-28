# 布局样例说明文档
实验考察对于**线性布局**、**约束布局**、**表格布局**的了解与使用</br>
该程序主要用**线性布局**、**约束布局**、**表格布局**这三种布局来展示这三个布局的用法与相应的实例</br>
## 开始界面
这是整个项目的开始界面，采用线性布局、一个文本视图（标题）、三个按钮（**线性布局**、**约束布局**、**表格布局**），通过这三个按钮分别跳转到使用这三种布局构建的界面</br>
![StartScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice2/picture/StartScreen.PNG)
## 线性布局
按下“线性布局”按钮，跳转到线性布局构建的界面中。构建实例的注意点：</br>
1. 文本视图的边框：调用**Practice2\app\src\main\res\drawable**下的**textview_border.xml**文件
2. 实例中一共有四行内容，那么分别用四个**LinearLayout**来包装这四行内容
3. 看起来看长的那段文本视图，可以采用权重的方式实现——`android:layout_weight="1"`
4. 第三行内容的文本视图的大小，那么采用分权的方式实现——第三行中的每个文本视图都设置`android:layout_weight="1"`</br>
![LinearLayoutScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice2/picture/LinearLayout.PNG)
## 约束（相对）布局
按下“约束布局”按钮，跳转到约束布局构建的界面中。构建实例的注意点：</br>
1. 第一行的文本视图的位置确定最为简单，就是左、中、右
2. 每个文本视图都需要进行id的赋值，以及用`android:gravity="center"`，使得文本相对于文本视图居中显示
3. 最难的一步应该就是在第二行那三个文本视图（GREEN、BLUE、INDIGO）。我的做法是：先确定最中间的BLUE——采用`android:layout_centerInParent="true"`，之后就可以以BLUE为中心，然后左右分别就是GREEN与INDIGO了</br>
![ConstrainLayoutScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice2/picture/ConstraintLayout.PNG)
## 表格布局
按下“表格布局”按钮，跳转到表格布局构建的界面中。构建实例的注意点：</br>
1. 第二列（对应于代码中的1）需要进行填充分布，所以采用在TableLayout中写入`android:stretchColumns="1"`
2. 分界线，不晓得为何用view组件会报空指针错误，所以我采用了Textview组件，不写内容，设置的属性为：
```
    <TextView
        android:layout_height="1dp"
        android:background="@color/colorDark"
        />
```
![TableLayoutScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice2/picture/TableLayout.PNG)
