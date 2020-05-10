# 实验四说明文档
实验考察对于intent隐式资源调用的使用。</br>
第一个应用：MyAppcation简单的使用edittext和button，构建一个简单的获得想要前往的网址的应用。</br>
![getUrl](https://github.com/LikeBlue1/-/blob/master/AndroidPractice4/picture/getUrl.png)
关键代码:</br>
```
  Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlHead+urlText.getText().toString()));

                startActivity(intent);
```
但是当我按下访问按钮时，并没有调用我已经装好在模拟器里的MyBrowser应用，直接调用的是模拟器系统默认的浏览器。</br>
最后还是没有找到解决方案。</br>
![outoview](https://github.com/LikeBlue1/-/blob/master/AndroidPractice4/picture/outoview.png)
