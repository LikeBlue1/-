# 布局样例说明文档
实验考察对于**线性布局**、**约束布局**、**表格布局**的了解与使用</br>
该程序主要用**线性布局**、**约束布局**、**表格布局**这三种布局来展示这三个布局的用法与相应的实例</br>
## 开始界面
这是整个项目的开始界面，采用线性布局、一个文本视图（标题）、三个按钮（**线性布局**、**约束布局**、**表格布局**），通过这三个按钮分别跳转到使用这三种布局构建的界面。Buttom的应用让三个实验布局连成一个程序。</br>
```
```
![StartScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice2/picture/StartScreen.PNG)
## 线性布局
按下“线性布局”按钮，跳转到线性布局构建的界面中。</br>
1. 实例中一共有四行内容，那么分别用四个**LinearLayout**来包装这四行内容
2. 线性布局的特殊控件就是权重**layout_weight**,在实验中，我如何使用权重使我的同一行内的字段所占长度不尽相同呢？这个实验中我先令其他三个字段的大小确定后，对需要变长的字段权重设为1即**layout_weight=1**，这样子第四个字段就能占满同一行内的剩余空间了.在权重的使用中要注意，权重分割的是**剩余空间**而不是全部空间。比如令1蓝：2黄：2绿实际效果却是2蓝：1黄：1绿。</br>
![LinearLayoutScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice2/picture/LinearLayout.PNG)
## 约束布局
按下“约束布局”按钮，跳转到约束布局构建的界面中。</br>
1. 首先这个实验布局呈现出来的应该是横屏，应该先满足这一点。
2. 约束布局是非常常用的一种布局，在UI组件设计中，有着非常方便的快捷方式。**Clear All Constraints**以及**Infer Constraints**的使用使得约束布局快速完成。</br>
![ConstrainLayoutScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice2/picture/ConstraintLayout.PNG)
## 表格布局
按下“表格布局”按钮，跳转到表格布局构建的界面中。</br>
1. 由于默认列数从第一列即`android:stretchColumns="0"`，为了更好的编写代码，我们b表格第二列（对应于代码中的1）需要进行填充分布，所以采用在TableLayout中写入`android:stretchColumns="1"`
2.注意表格布局，列数的确定是根据每一行的列数的MAX即最大值产生的。比如说第二行总共有4列，其他行只有2列或者3列，那么这个表格就会呈现出4列。</br>
![TableLayoutScreen](https://github.com/LikeBlue1/-/blob/master/AndroidPractice2/picture/TableLayout.PNG)
