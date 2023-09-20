## 1.网络编程入门

#### 1.1	网络通信协议

> **网络通信协议：**通过计算机网络协议可以使多台计算机实现连接，位于同一个网络中的计算机在进行连接和通信时需要遵守的一定规则。
>
> **Tcp/IP协议：**传输控制协议/网际互连协议，是Internet最基本、最广泛的协议，它定义了计算机如何连入因特网，以及数据如何在它们之间传输的标准。它的内部包含了一系列的用于处理数据通信的协议，并采用了4层的分层模型。

#### 1.2	协议的分类

java.net包中包含的类和接口，它们提供低层次的通信细节，我们可以直接使用这些类和接口，而不用考虑通信的细节。

`Java.net`包中提供了两种常见的网络协议的支持：

- UDP：用户数据包协议，**是无连接的通信协议，**所以不保证数据传输的完整性；传输重要数据时不建议使用。
  - 特点：数据被限制在64kb以内，超出这个范围就不能发送了。
  - 数据报（Datagram）：网络传输的基本单位
- TCP：传输控制协议，**面向连接，**它提供了两台计算机之间可靠无差错的数据传输。
  - 在TCP连接中必须要明确客户端与服务器端，由客户端向服务器端发送请求，每次连接的创建都必须经过”三次握手“
  - 三次握手：可保证连接的可靠
    - 第一次握手：客户端向服务器端发送连接请求，等待服务器端确认。
    - 第二次握手：服务器端向客户端回送一个响应，通知客户端收到了连接请求
    - 第三次握手：客户端再次向服务器端发送确认信息，确认连接。
  - 断开连接：四次挥手
    - 客户端向服务器发出取消连接的请求
    - 服务器向客户端返回一个响应，表示收到客户端的取消请求
    - 服务器向客户端发出确认取消信息
    - 客户端再次发出确认信息【连接取消】

#### 1.3网络编程的三要素

- **协议：**计算机网络必须遵守的规则。
- IP**地址：**分类如下所示
  - IPv4：32位的二进制，例如：192.168.12.34，其中每一位都是0——255之间的十进制，最多可表示42亿个。
  - IPv6：八组十六进制数，表示：ABCD:EF01:2345:6789:ABCD:EF01:2345:6789,解决网络地址资源数量不足问题。
  - 常用命令：查看本机ip地址：`ipconfig`
  - 检查网络是否连通：ping  空格  ip地址，eg：ping  220.212.56.231
  - 特殊的IP地址：本机IP地址：127.0.0.1、localhost
- **端口号：**
  - 网络的通信本质上就是两个进程（应用程序）的通信。如果说IP地址可以唯一标识网络中的一台设备，那么端口号就可以唯一标识设备中的进程了。
  - 端口号：用两个字节表示的整数，它的取值范围是0—65535，**注意：1024之前的端口号我们不能使用，因为已经被系统分配给已知的网络软件了，端口号不能重复。**
  - 常用的端口号：
    1. 80端口------>网络端口，例如www.baidu.com:80默认就是80端口
    2. 数据库：MySQL默认就是3306端口              Oracle：1521
    3. Tomcat服务器：8080

利用协议+IP地址+端口号  三元组合，就可以标识网络中的进程了。

- 特殊的**IP**地址
  - 127.0.0.1 / localhost 都表示本机

- 区别：
	1. localhost 等于 127.0.0.1 ，不过 localhost 是域名， 127.0.0.1 是 IP 地址。

	2. localhost 和 127.0.0.1 不需要联网，都是本机访问

	3. 本机 IP 需要联网，本机 IP 是本机或外部访问， 本机 IP 就是本机对外放开访问的 IP 地址，这个网址就是与物理网卡绑定的 IP 地址

	4. localhost 是一个域名，在过去它指向 127.0.0.1 这个IP地址。在操作系统支持 ipv6后，它同时还指向ipv6 的地址 ::

## 2. TCP通信程序

### 2.1	概述

| TCP通信                                                      |
| ------------------------------------------------------------ |
| ![网络编程-客户端和服务器端的通信](https://github.com/qwewfc/Java_respository/assets/86483506/76db3e6b-a385-4de3-9043-babcb76c1c9d)|

#### 2.1.2	服务器端必须明确两件事：

1. 多个客户端同时向服务器端进行交互，服务器端必须明确和哪个客户端进行交互。
   - 在服务器端有一个方法：叫`accept`，可以获取到请求的客户端对象。
2. 多个客户端同时向服务器端进行交互，就需要使用多个`IO流`对象
   - **服务器端是没有IO流的，服务器端可以获取到请求的客户端对象Socket，使用每个客户端Socket中提供的IO流和客户端进行交互。**
     - 服务器端使用客户端的字节`输入流`读取客户端发送的数据。
     - 服务器端使用客户端的字节`输出流`给客户端回写数据
   - 简单记：服务器端使用客户端的流和客户端进行交互。eg：我请你吃饭，但是没钱，找你借10元，请你吃饭。

#### 2.1.3两端通信时步骤

1. 服务器端程序，需要事先开启，等待客户端的连接。
2. 客户端主动连接服务器端，连接成功才能通信。服务器端不可以主动连接客户端。

**在Java中，提供两个类用于实现TCP通信程序：**

1. 客户端：`java.net.Socket`类表示。创建Socket对象，向服务器端发出连接请求，服务器响应请求，两者建立连接开始通信。
2. 服务器端：`java.net.ServerSocket`类表示。创建ServverSocket对象，相当于开启一个服务，等待客户端的连接。

### 2.2	Socket类

`public Socket(String host,int port)`到指定主机上的指定端口号。

如果指定的主机是`null` ，则相当于将地址指定为[`InetAddress.getByName` `(null)` 。 换句话说，它等同于指定环回接口的地址。

> 小贴士：回送地址（127.x.x.x)是本机回送地址，主要用于网络软件测试以及本机进程间的通信，无论什么程序，一旦使用回送地址发送数据，立即返回，不进行任何网络传输。

### 2.3	Java代码测试

- 客户端向服务器端发送数据：

```java
package com.yyjs;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/*
* TCP通信的客户端：向服务器发送连接请求，给服务器发送数据，读取服务器回写的数据。
* 表示客户端的类：
*       Java.net.Socket:此类实现客户端套接字。套接字是两台机器间通信的端点。
*           套接字：包含了IP地址和端口号的网络单位
* 构造方法： Socket(String host, int port) 创建一个流套接字并将其连接到指定主机上的指定端口号。
            参数：host - 服务器的主机名，服务器的IP地址 。
                 port - 服务器的端口号。
  成员方法：public InputStream getInputStream() 返回此套接字的输入流。
*         public OutputStream getOutputStream() 返回此套接字的输出流。
*         public void close() throws IOException 关闭此套接字。
* 实现步骤：
*       1.创建一个客户端对象Socket，构造方法绑定服务器的IP地址和端口号
*       2.使用Socket对象中的方法getOutputStream()获取网络字节输出流OutputStream对象
*       3.使用网络字节输出流OutputStream对象中的方法write，给服务器发送数据
*       4.使用Socket对象中的方法getInputStream()获取网络字节输入流InputStream对象
*       5.啊hi用网络字节输入流的InputStream对象中的方法read，读取服务器回写的数据
*       6.释放资源（Socket）
* 注意：
*       1.客户端和服务器进行交互，必须使用Socket中提供的网络流，不能使用自己创建的流对象
*       2.当我们创建客户端对象Socket的时候，就会去请求服务器和服务器经过三次握手简历连接通路
*              这时如果服务器没有启动，那么就会抛出异常
*              如果服务器已经启动，那么就可以进行交互了。 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
//    1.创建一个客户端对象Socket，构造方法绑定服务器的IP地址和端口号
        Socket socket = new Socket("127.0.0.1", 8888);
        //    2.使用Socket对象中的方法getOutputStream()获取网络字节输出流OutputStream对象
        OutputStream os = socket.getOutputStream();
        //3.使用网络字节输出流OutputStream对象中的方法write，给服务器发送数据
        os.write("你好服务器".getBytes());
        //4.使用Socket对象中的方法getInputStream()获取网络字节输入流InputStream对象
        InputStream is = socket.getInputStream();
        //5.啊hi用网络字节输入流的InputStream对象中的方法read，读取服务器回写的数据
        byte[] bytes = new byte[1024];
        int len = is.read();
        System.out.println(new String(bytes,0,len));
        //6.释放资源（Socket）
        os.close();
    }
}
```

- 服务器端向客户端响应数据：


```java
package com.yyjs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
* TCP通信的服务器端：接受客户端的请求，读取客户端发送的数据，给客户端回写数据
* 表示服务器的类：
*       java.net.ServerSocket:此类实现服务器套接字。
* 构造方法：
*       ServerSocket(int port) 创建绑定到特定端口的服务器套接字。
* 服务器端必须明确一件事，就是知道是哪个客户端请求的服务器。
* 所以使用accept方法获取到请求的客户端对象Socket
* 成员方法：
*       Socket accept() 侦听并接受到此套接字的连接。
* 服务器的实现步骤：
*       1.创建服务器ServerSocket对象和系统要指定的端口号
*       2.使用ServerSocket对象中的方法accept，获取到请求的客户端对象Socket
*       3.使用Socket对象中的方法getInputStream()获取网络字节输入流InputStream对象
*       4.使用网络字节输入流InputStream对象中的方法read，读取客户端发送的数据
*       5.使用Socket到现在中的方法getOutputStream获取网络字节输出流OutputStream对象
*       6.是哦也能够网络字节输出流OutputStream对象中的方法write，给客户端回写数据
*       7.释放资源（Socket，ServerSocket）*/
public class TCPServer {
    public static void main(String[] args) throws IOException {
//        1.创建服务器ServerSocket对象和系统要指定的端口号
        ServerSocket sc = new ServerSocket(8888);
//        2.使用ServerSocket对象中的方法accept，获取到请求的客户端对象Socket
        Socket socket = sc.accept();
//        3.使用Socket对象中的方法getInputStream()获取网络字节输入流InputStream对象
        InputStream is = socket.getInputStream();
//        4.使用网络字节输入流InputStream对象中的方法read，读取客户端发送的数据
        byte[] bytes = new byte[1024];
        int len = is.read();
        System.out.println(new String(bytes,0,len));
//        5.使用Socket到现在中的方法getOutputStream获取网络字节输出流OutputStream对象
        OutputStream os = socket.getOutputStream();
//        6.是哦也能够网络字节输出流OutputStream对象中的方法write，给客户端回写数据
        os.write("收到谢谢".getBytes());
//        7.释放资源（Socket，ServerSocket）
        socket.close();
        sc.close();
    }
}
```
