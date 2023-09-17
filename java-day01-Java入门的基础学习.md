[TOC](本章内容)

##  一、基础准备

### 1.1 关于JVM 、 JDK以及 JRE

### 1.1.1 JVM

Java 虚拟机（JVM）是运行 Java 字节码的虚拟机。JVM 有针对不同系统的特定实现（Windows，Linux，macOS），目的是使用相同的字节码，它们都会给出相同的结果。字节码和不同系统的 JVM 实现是 Java 语言“**一次编译，随处可以运行**”的关键所在。

它本身并不被解释，但也可以通过将其指令集编译为硅 CPU 的指令集来实现。

Java 虚拟机对 Java 编程语言一无所知，只知道一种特定的二进制格式，即类文件格式。类文件包含 Java 虚拟机指令（或字节码）和符号表，以及其他辅助信息。

关于JVM规范参考官网：[JVM规范]([The Java® Virtual Machine Specification (oracle.com)](https://gitee.com/link?target=https%3A%2F%2Fdocs.oracle.com%2Fjavase%2Fspecs%2Fjvms%2Fse17%2Fhtml%2Findex.html))

### 1.1.2 JDK

JDK（Java Development Kit），它是功能齐全的 Java SDK，是提供给开发者使用的，能够创建和编译 Java 程序。他包含了 JRE，同时还包含了编译 java 源码的编译器 javac 以及一些其他工具比如 javadoc（文档注释工具）、jdb（调试器）、jconsole（基于 JMX 的可视化监控⼯具）、javap（反编译工具）等等。

Java开发环境搭建的步骤：

1. 下载JDK
2. 安装JDK
3. 配置环境变量

> 关于以上JDK环境的下载以及配置的详细步骤见：[Java——JDK的下载以及环境变量的配置（超详细过程）]([(11条消息) Java——JDK的下载以及环境变量的配置（超详细过程）*变量对话框下载*-今非昔比°的博客-CSDN博客](https://gitee.com/link?target=https%3A%2F%2Fblog.csdn.net%2FBest_Basic%2Farticle%2Fdetails%2F126955192%3Fspm%3D1001.2014.3001.5501))

小提示：**这里建议在文件夹选项中将文件扩展名显示，以便于查看文件扩展名**

JDK的结构图： 

![输入图片说明](image/JDK%E7%BB%93%E6%9E%84.png)

### 1.1.3 JRE

JRE（Java Runtime Environment） 是 Java 运行时环境。它是运行已编译 Java 程序所需的所有内容的集合，主要包括 Java 虚拟机（JVM）、Java 基础类库（Class Library）。

------

也就是说，JRE 是 Java 运行时环境，仅包含 Java 应用程序的运行时环境和必要的类库。而 JDK 则包含了 JRE，同时还包括了 javac、javadoc、jdb、jconsole、javap 等工具，可以用于 Java 应用程序的开发和调试。如果需要进行 Java 编程工作，比如编写和编译 Java 程序、使用 Java API 文档等，就需要安装 JDK。而对于某些需要使用 Java 特性的应用程序，如 JSP 转换为 Java Servlet、使用反射等，也需要 JDK 来编译和运行 Java 代码。因此，即使不打算进行 Java 应用程序的开发工作，也有可能需要安装 JDK。

![输入图片说明](image/jvm%E8%BF%90%E8%A1%8C%E8%BF%87%E7%A8%8B.png)

## 二、为什么说 Java 语言“编译与解释并存”？

将高级编程语言按照程序的执行方式分为两种：

- **编译型**：[编译型语言](https://gitee.com/link?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E7%B7%A8%E8%AD%AF%E8%AA%9E%E8%A8%80) 会通过[编译器](https://gitee.com/link?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E7%B7%A8%E8%AD%AF%E5%99%A8)将源代码一次性翻译成可被该平台执行的机器码。一般情况下，编译语言的执行速度比较快，开发效率比较低。常见的编译性语言有 C、C++、Go、Rust 等等。
- **解释型**：[解释型语言](https://gitee.com/link?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E7%9B%B4%E8%AD%AF%E8%AA%9E%E8%A8%80)会通过[解释器](https://gitee.com/link?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E7%9B%B4%E8%AD%AF%E5%99%A8)一句一句的将代码解释（interpret）为机器代码后再执行。解释型语言开发效率比较快，执行速度比较慢。常见的解释性语言有 Python、JavaScript、PHP 等等。

------

#### 2.1 Java的执行过程：

常见的编译型语言如C++，通常会把代码直接编译成CPU所能理解的机器码来运行。而Java为了实现“一次编译，处处运行”的特性，把编译的过程分成两部分，首先它会先由javac编译成通用的中间形式——字节码，然后再由解释器逐条将字节码解释为机器码来执行。所以在性能上，Java通常不如C++这类编译型语言。

为了优化Java的性能 ，JVM在解释器之外引入了即时（Just In Time）编译器：当程序运行时，解释器首先发挥作用，代码可以直接执行。随着时间推移，即时编译器逐渐发挥作用，把越来越多的代码编译优化成本地代码，来获取更高的执行效率。解释器这时可以作为编译运行的降级手段，在一些不可靠的编译优化出现问题时，再切换回解释执行，保证程序可以正常运行。

Java的执行过程[Java 即时编译器原理解析](https://gitee.com/link?target=https%3A%2F%2Ftech.meituan.com%2F2020%2F10%2F22%2Fjava-jit-practice-in-meituan.html)：

- Java的执行过程整体可以分为两个部分，第一步由**javac**将源码编译成字节码，在这个过程中会进行词法分析、语法分析、语义分析，编译原理中这部分的编译称为前端编译。接下来无需编译直接逐条将**字节码**解释执行，在解释执行的过程中，虚拟机同时对程序运行的信息进行收集，在这些信息的基础上，编译器会逐渐发挥作用，它会进行后端编译——把字节码编译成机器码，但不是所有的代码都会被编译，只有被JVM认定为的热点代码，才可能被编译。
- 怎么样才会被认为是热点代码呢？JVM中会设置一个阈值，当方法或者代码块的在一定时间内的调用次数超过这个阈值时就会被编译，存入codeCache中。当下次执行时，再遇到这段代码，就会从codeCache中读取机器码，直接执行，以此来提升程序运行的性能。整体的执行过程大致如下图所示：

![输入图片说明](image/%E8%BF%90%E8%A1%8C%E5%8E%9F%E7%90%86.png)

#### 2.2 所以为什么Java语言“编译与解释共存：

这是因为 Java 语言既具有**编译型语言**的特征，也具有**解释型语言**的特征。因为 Java 程序要经过先编译，后解释两个步骤，由 Java 编写的程序需要先经过编译步骤，生成字节码（`.class` 文件），这种字节码必须由 Java 解释器来解释执行。

------

## 三、保留关键字

Java该语言是用 Unicode 字符集编写的。

Java关键字是预先定义的具有特别意义的标识符，也被称为Java保留字，Java关键字不能用作变量名、方法名、类名、包名、参数。Java中定义了51个关键字，如下列所述：
|  |  |  |  |  | |
| --------- | ------------------ | ------------ | -------- | --------- | -------- |
| abstract | continue | for | new | assert | default |
| if | package | synchronized | boolean | do | **goto** |
| private | this | protected | throw | byte | throws |
| break | double | implements | else | import | public |
| case | enum | instanceof | return | transient | catch |
| extends | int | short | try | char | final |
| interface | class | finally | long | while | super |
| static | void | strictfp | volatile | **const** | float |
| switch | native | **_ (underscore)** |


> 注意：
>
> 1. `const`和`goto`是Java的保留字，但是目前未被使用，也不能作为标识符
> 2. `true`和`false`是Java的布尔字面量，表示真和假，虽然不是关键字，但也不能用作标识符、变量名
> 3. `null`是Java的空字面量，表示一个空引用，虽然不是关键字，但也不能作为标识符和变量名
> 4. 所有的Java关键字都是小写的。

## 四、Java的基本数据类型

基本数据类型一共有八种，如下图所示：

![输入图片说明](image/%E5%9F%BA%E6%9C%AC%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B.png)

基本数据类型的取值范围：

| 类型       | 大小            | 取值范围               | 默认值      | 实例       |
| ---------- | --------------- | ---------------------- | ----------- | ---------- |
| boolean    | 1字节，8位      | true、false            | false       | true       |
| byte       | 1字节，8位      | -128 — +127            | 0           | -100       |
| **short**  | **2字节，16位** | **-32768 — +32767**    | **0**       | **100**    |
| **char**   | **2字节，16位** | **0—65535**            | **'u0000'** | **'a'**    |
| int        | 4字节，32位     | -2^31 — 2^31-1         | 0           | 150        |
| float      | 4字节，32位     | -3.4E38 — 3.4E38       | 0.0f        | 3.14f      |
| **long**   | **8字节。64位** | **-2^63— +2^63-1**     | **0L**      | **10000**  |
| **double** | **8字节，64位** | **-1.7E308 — 1.7E308** | **0.0d**    | **2.4e3d** |

## 五、**引用数据类型**

> 引用数据类型包括类、接口、数组、枚举。字符串属于类，也属于引用数据类型。
>
> - 在Java中，引用类型的变量非常类似于C/C++的指针。引用类型指向一个对象，指向对象的变量是引用变量。这些变量在声明时被指定为一个特定的类型，比如 Employee、Puppy 等。变量一旦声明后，类型就不能被改变了。
> - 所有引用类型的默认值都是null。
> - 一个引用变量可以用来引用任何与之兼容的类型。
> - 例子：Site site = new Site("Runoob")。

## 六、基本数据类型间的转换

**整型、实型（常量）、字符型数据可以混合运算。运算中，不同类型的数据先转化为同一类型，然后进行运算。**

转换从低级到高级。

```java
低  ------------------------------------>  高

		byte,short,char—> int —> long—> float —> double 
```

数据类型转换必须满足如下规则：

- 不能对boolean类型进行类型转换。

- 不能把对象类型转换成不相关类的对象。

- 在把容量大的类型转换为容量小的类型时必须使用强制类型转换。

- 转换过程中可能导致溢出或损失精度，例如：

  ```java
  int i =128;   
  byte b = (byte)i;
  ```

  因为 byte 类型是 8 位，最大值为127，所以当 int 强制转换为 byte 类型时，值 128 时候就会导致溢出。

- 浮点数到整数的转换是通过舍弃小数得到，而不是四舍五入，例如：

  ```java
  (int)23.7 == 23;        
  (int)-45.89f == -45
  ```

### 隐含强制类型转换

- 整数的默认类型是 int。
- 小数默认是 double 类型浮点型，在定义 float 类型时必须在数字后面跟上 F 或者 f
