# DslListenerBuilder

## 使用导学

![](art.gif)

### 一、简述
一款自动生成DSL ListnerBuilder模板代码的IDEA插件，支持IDEA、AndroidStudio以及JetBrains全家桶

### 二、支持的功能

支持一键自动生成DSL ListenerBuilder模板代码

### 三、开发中用到的技术点
* 1、Kotlin基础开发知识
* 2、Kotlin扩展函数
* 3、Kotlin的lambda表达式
* 4、Swing UI组件开发知识
* 5、Intellij Plugin开发基本知识
* 6、IntelliJ Plugin 常用开发API(Editor、WriteCommandAction、PsiDocumentManager、Document等API的使用)
* 7、Velocity模板基本语法(#if,#foreach,#set等)
* 8、Velocity模板引擎API的基本使用

### 四、开发教程


### 五、使用步骤

**第一步:** 首先按照IDEA一般插件安装流程安装好DslListenerBuilder插件。

**第二步:** 然后打开具体某个类文件，将光标定位在具体代码生成的位置，

**第三步:** 使用快捷键调出Generate中的面板，选择其中的“Listener Builder”, 然后就会弹出一个面板，可以点击add按钮添加一个或多个回调函数的lamba, 也可以从面板中选择任一一条不需要的Item进行删除。

**第四步:** 最后点击OK就可以在指定光标位置生成需要的代码。

### 六、版本release note

#### V1.0.0

* 插件基本功能: 支持一键自动生成DSL ListenerBuilder模板代码