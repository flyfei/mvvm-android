[Google Data Binding Library](#Google Data Binding Library)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[版本要求](#版本要求)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[配置环境](#配置环境)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[创建数据对象](#创建数据对象)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[配置Layout](#配置Layout)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[绑定数据](#绑定数据)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[绑定事件](#绑定事件)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[布局文件配置细节](#布局文件配置细节)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[variable](#variable)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[import](#import)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[显示集合中的数据](#显示集合中的数据)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[显示资源数据](#显示资源数据)<br>
        





## Google Data Binding Library

[Google Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html)

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
       <variable name="user" type="com.tovi.mvvm.User"/>
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
        android:orientation="vertical"
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

MainActivity.java 绑定 MyHandlers 实例化数据

```java
ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
User user = new User("Test", 12);
MyHandlers handlers = new MyHandlers();
binding.setHandlers(handlers);
binding.setUser(user);
```

执行项目，试试吧！

### 布局文件配置细节

经过测试，发现：

* 如果某对象里面的数据（方法或参数）只有实例化后才能访问到，则必须用 variable，并且 在 界面 进行 实例化数据绑定，这样才能访问到；反之，如果不用实例化可以访问到，直接 import 引入 ，访问数据就可以了。
* 如果需要 传值进来，则用 variable。

下面是 一些测试及其他细节介绍

#### variable

上述 MyHandlers 是这样 在布局里引入的

```xml
<variable
     name="handlers"
     type="com.tovi.mvvm.MyHandlers" />
```

也可以这样

用 import 引入 MyHandlers ，在 通过 variable 改名字

```xml
<import type="com.tovi.mvvm.MyHandlers" />
<variable
     name="handlers"
     type="MyHandlers" />
```

这样写是不是也可以通过呢，哈哈。

#### import

如果将 MyHandlers 里面的 TAG 显示到界面上，可以这样做

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <import type="com.tovi.mvvm.MyHandlers" />

        <variable
            name="user"
            type="com.tovi.mvvm.User" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{MyHandlers.TAG}" />
    </LinearLayout>
</layout>
```

改成这样的话，运行项目会 异常，说 找不到  setHandlers 方法。索性 他注掉。

```java
ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
User user = new User("Test", 12);
MyHandlers handlers = new MyHandlers();
// binding.setHandlers(handlers);
binding.setUser(user);
```

运行项目。是不是发现，这次没有 用 variable ，也可以访问到数据。



这样的话，在布局里面就可以访问 Android 提供的 方法了，比方说  View.GONE 等等

```xml
...
<data>
    <import type="android.view.View"/>
</data>
...

<TextView
   android:text="@{user.lastName}"
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"
   android:visibility="@{View.GONE}"/>

...
```



如果系统的类名 和自己的冲突了，咋办， 比方说自己有个 类 com.tovi.mvvm.View

这时，可以用 alias 字段 修改 名字为 MyView，这样，就不用 访问 View(com.tovi.mvvm.View)了，直接访问 MyView 就可以了。

```xml
<import type="android.view.View"/>
<import type="com.tovi.mvvm.View"
        alias="MyView"/>
```





#### 显示集合中的数据

如果要从用户列表动态的获取并展示用户信息，可以这样

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <import type="java.util.List" />

        <import type="com.tovi.mvvm.User" />

        <variable
            name="userList"
            type="List&lt;User>" />
        <variable
            name="index"
            type="int" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="@{userList[index].name}"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="@{String.valueOf(userList[index].age)}"/>
    </LinearLayout>

</layout>
```

ps:

* `List&lt;User>` 不能写成  `List<User>` ,否则会报异常

  ```
  Error:Execution failed for task ':app:dataBindingProcessLayoutsDebug'.
  > org.xml.sax.SAXParseException; systemId: file:xxx/Android/DataBinding/app/build/intermediates/res/merged/debug/layout/activity_list.xml; lineNumber: 12; columnNumber: 23; 与元素类型 "null" 相关联的 "type" 属性值不能包含 '<' 字符。
  ```

* age 是 int 类型，不能直接 给 TextView 赋值， 所以 需要经过 String.valueOf() 处理下




#### 显示资源数据

如果显示资源文件 string.xml 中的数据

string.xml

```xml
...

<string name="start_listActivity">跳转到ListActivity</string>

...
```



activit_main.xml

```xml
...

<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:onClick="@{handlers.toListActivity}"
    android:text="@{@string/start_listActivity}" />

...
```

