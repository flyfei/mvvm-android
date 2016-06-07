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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[include](#include)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[表达式语法](#表达式语法)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[避免空指针](#避免空指针)<br>
        [更新UI](#)
                [BaseObservable](#BaseObservable)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ObservableField](#ObservableField)
                [BindingAdapter](#BindingAdapter)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        

## Google Data Binding Library

Time: 2016-06-02



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
* xmlns 只能存在一个节点（**layout**）。

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



#### include

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:bind="http://schemas.android.com/apk/res-auto">

...

<variable
          name="user"
          type="com.tovi.mvvm.User" />

...

<include
         bind:user="@{user}"
         layout="@layout/layout_userinfo" />
...
```

注：必须有 `xmlns:bind="http://schemas.android.com/apk/res-auto"`,否则，bind 会报错。

**layout_userinfo.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <variable
            name="user"
            type="com.tovi.mvvm.User" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <TextView android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Include View"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{user.name}" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{String.valueOf(user.age)}" />
    </LinearLayout>
</layout>

```

如果 需要在 include 里面传递多个参数可以类似这样

```xml
<include
    layout="@layout/layout_userinfo"
    bind:handlers="@{handlers}"
    bind:user="@{user}" />
```

或

```xml
<include
    layout="@layout/layout_userinfo"
	handlers="@{handlers}"
    bind:user="@{user}" />
```

##### 延伸

试着把 bind 去掉，这样就不用 `xmlns:bind="http://schemas.android.com/apk/res-auto"`了，

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
  
...

  <include
            layout="@layout/layout_userinfo"
            handlers="@{handlers}"
            user="@{user}" />
```

这样也可以进行数据绑定，事件绑定。费解 为啥还要有 **bind** ！



#### 表达式语法

支持语法

```
数学 + - / * %
字符串连接 +
逻辑 && ||
二进制 & | ^
一元运算 + - ! ~
移位 >> >>> <<
比较 == > < >= <=
instanceof
分组 ()
Literals - character, String, numeric, null
Cast
方法 calls
方法调用
数据访问 []
三元运算 ?:
```

不支持语法

```
this
super
new
```



#### 避免空指针

如果View绑定的数据为空，则默认显示 默认值（null）

当然，可以根据自己的需要进行设置。添加 default 字段，并定义默认值

```xml
<TextView android:layout_width="wrap_content"
   android:layout_height="wrap_content"
   android:text="@{user.name, default=PLACEHOLDER}"/>
```





### 更新UI

#### BaseObservable

Movie.java

```java
package com.tovi.mvvm.updateui.baseObservable;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tovi.mvvm.BR;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class Movie extends BaseObservable {
    public String name;
    public int sizeOfWatching;


    public Movie(String name, int sizeOfWatching) {
        this.name = name;
        this.sizeOfWatching = sizeOfWatching;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getSizeOfWatching() {
        return sizeOfWatching;
    }

    public void setSizeOfWatching(int sizeOfWatching) {
        this.sizeOfWatching = sizeOfWatching;
        notifyPropertyChanged(BR.sizeOfWatching);
    }
}
```

BaseObservableActivity.java

```java
package com.tovi.mvvm.updateui.baseObservable;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tovi.mvvm.R;
import com.tovi.mvvm.databinding.ActivityBaseObservableBinding;

public class BaseObservableActivity extends AppCompatActivity implements View.OnClickListener {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBaseObservableBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_base_observable);
        movie = new Movie("魔兽", 0);
        binding.setMovie(movie);

        findViewById(R.id.update_ui).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        movie.setSizeOfWatching(movie.getSizeOfWatching() + 1);
    }
}
```

activity_base_observable.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="movie"
            type="com.tovi.mvvm.updateui.baseObservable.Movie" />
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
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="---------Observable Objects----------------------------"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{movie.name}"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{String.valueOf(movie.sizeOfWatching)}"/>
        <Button
            android:id="@+id/update_ui"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Update UI"
            android:onClick="onlClick"/>
    </LinearLayout>
</layout>
```



#### ObservableField

Car.java

```java
package com.tovi.mvvm.updateui.observableField;

import android.databinding.ObservableField;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class Car {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<Integer> number = new ObservableField<>();
}

```



ObservableFieldActivity.java

```java
package com.tovi.mvvm.updateui.observableField;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tovi.mvvm.R;
import com.tovi.mvvm.databinding.ActivityObservableFieldBinding;

public class ObservableFieldActivity extends AppCompatActivity implements View.OnClickListener {

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityObservableFieldBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_observable_field);
        car = new Car();
        car.name.set("Tesla");
        car.number.set(0);
        binding.setCar(car);

        findViewById(R.id.update_tesla).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        car.number.set(car.number.get() + 1);
    }
}
```

activity_observable_field.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <variable
            name="car"
            type="com.tovi.mvvm.updateui.observableField.Car" />
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
            android:text="ObservableField" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{car.name}" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{String.valueOf(car.number)}" />

        <Button
            android:id="@+id/update_tesla"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Update UI" />

    </LinearLayout>
</layout>
```



#### BindingAdapter

这里以 load img 为例

Phone.java

```java
package com.tovi.mvvm.loadimg;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class Phone {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

```

LoadImgActivity.java

```java
package com.tovi.mvvm.loadimg;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tovi.mvvm.R;
import com.tovi.mvvm.databinding.ActivityLoadImgBinding;

public class LoadImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoadImgBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_load_img);
        Phone phone = new Phone();
        phone.url = "http://ww3.sinaimg.cn/bmiddle/0065It2Vgw1f4ia9c5ig8j318g0xcard.jpg";
        binding.setPhone(phone);

    }
}
```

activity_load_img.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>

        <variable
            name="phone"
            type="com.tovi.mvvm.loadimg.Phone" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin">


        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:error="@{phone.url}"
            app:imageUrl="@{phone.url}" />

        <Button
            android:id="@+id/update_load_img"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Update UI" />

    </LinearLayout>
</layout>
```

BindingAdapters.java

```java
package com.tovi.mvvm.loadimg;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
@SuppressWarnings("unused")
public class BindingAdapters {

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView imageView, String imageUrl, String error) {
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
```

**注：**

* model 必须有 get 方法
* 官网上这样写的。@BindingAdapter({"bind:imageUrl", "bind:error"})，这样的话，会报⚠️ `Warning:Application namespace for attribute bind:imageUrl will be ignored.`




<br>

以上项目源码见 [Github google-dataBinding](https://github.com/flyfei/mvvm-android/tree/master/Android/google-dataBinding)



<br>

<br>

Data Binding 讲到这儿。结合 Mvvm ，见 [Github mvvm](https://github.com/flyfei/mvvm-android/tree/master/Android/mvvm)

