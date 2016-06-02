[TOC]

## Data Binding Library

[Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html)

Android 2.1 (API level 7+)



### 版本要求

Android Studio >= **1.3**

Gradle >= **1.5.0-alpha1**

### 配置环境

在app module里面，添加dataBinding节点到build.gradle 文件。

```xml
android {
    ....
    dataBinding {
        enabled = true
    }
}
```

### 创建数据对象

 User.java

```java
package com.tovi.mvvm;

public class User {
    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

```

### 配置Layout

1. 添加 layout 节点，使其作为 xml 布局文件 根节点。
2. 添加 data 标签，引入 数据 对象 User。
3. 并给相应的组件 按照 `@{}` 格式 赋值。

如下

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
   <data>
       <variable name="user" type="com.example.User"/>
   </data>
   <LinearLayout
       android:orientation="vertical"
       android:layout_width="match_parent"
       android:layout_height="match_parent">
       <TextView android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="@{user.firstName}"/>
       <TextView android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="@{user.lastName}"/>
   </LinearLayout>
</layout>
```

### 绑定数据

以上配置好后，它会自动的生成  `Binding`。

```
不知道是为啥，和 官网上 介绍的不一样。可能是因为版本不一样吧。

官网上：
activity:未知
xml:main_activity.xml
Binding:MainActivityBinding

本地实测：
activity:MainActivity.java
xml:activity_main.xml
Binding:ActivityMainBinding

发现一个问题，Binding 的 规则 和 xml 的 名字有一定关系。 xml: activity_main.xml Binding:ActivityMainBinding

为了验证是否正确，我把  activity_main.xml 重命名为 home_layout.xml, 结果 生成的 Binding 是 HomeLayoutBinding。

说明 Binding 的名字  是根据 xml的名称顺序 命名的。
```

项目中，生成的是 ActivityMainBinding

MainActivity.java 中，修改 onCreate 方法

```java
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    User user = new User("Test", 12);
    binding.setUser(user);
}
```

运行项目试试吧！

### 绑定事件

创建 MyHandlers.java 

```java
package com.tovi.mvvm;

import android.util.Log;
import android.view.View;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class MyHandlers {
    public static final String TAG = MyHandlers.class.getSimpleName();

    public void onClick(View view) {
        Log.e(TAG, "onClick");
    }
}

```

配置 activity_main.xml 布局文件

引入 MyHandlers 数据类型

```xml
<variable
    name="handlers"
    type="com.tovi.mvvm.MyHandlers" />
```

MyHandlers 和 View 进行绑定

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <variable
            name="handlers"
            type="com.tovi.mvvm.MyHandlers" />

        <variable
            name="user"
            type="com.tovi.mvvm.User" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:onClick="@{handlers.onClick}"
            android:text="@{user.name}" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{String.valueOf(user.age)}" />
    </LinearLayout>
</layout>

```

MainActivity.java 绑定 MyHandlers 数据

```java
ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
User user = new User("Test", 12);
MyHandlers handlers = new MyHandlers();
binding.setHandlers(handlers);
binding.setUser(user);
```

执行项目，试试吧！

