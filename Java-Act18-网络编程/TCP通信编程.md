Java为客户端提供了Socket类，为服务器端提供了ServerSocket类

两端通信时步骤：
1. 服务端程序，需要事先启动，等待客户端的连接。
2. 客户端主动连接服务器端，连接成功才能通信。服务端不可以主动连接客户端。
  
在 Java 中，提供了两个类用于实现 TCP 通信程序：
1. 客户端： java.net.Socket 类表示。创建 Socket 对象，向服务端发出连接请求，服务端响应请求，两者建立连接开始通信。
2. 服务端： java.net.ServerSocket 类表示。创建 ServerSocket 对象，相当于开启一个服务，并等待客户端的连接。

## Socket 类
Socket 类 ：该类实现客户端套接字，套接字指的是两台设备之间通讯的端点。
```java
 public class Socket implements java.io.Closeable
```
### 构造方法
```java
/创建流套接字并将其连接到指定主机上的指定端口号,如果指定的主机是null ，则相当于指定地址为
InetAddress.getByName (null) 。 换句话说，它相当于指定回送接口的地址
public Socket(String host, int port)
```

### 成员方法
- `public InputStream getInputStream() `： 返回此套接字的输入流。
如果此 Scoket 具有相关联的通道，则生成的 InputStream 的所有操作也关联该通道。
关闭生成的 InputStream 也将关闭相关的 Socket 。
- public OutputStream getOutputStream() ： 返回此套接字的输出流。
如果此 Scoket 具有相关联的通道，则生成的 OutputStream 的所有操作也关联该通
道。关闭生成的 OutputStream 也将关闭相关的 Socket 。
- public void close() ：关闭此套接字。一旦一个 socket 被关闭，它不可再使用。关
闭此 socket 也将关闭相关的 InputStream 和 OutputStream 。
- public void shutdownOutput() ： 禁用此套接字的输出流。任何先前写出的数据将被发送，随后终止输出流。

## ServerSocket 类
ServerSocket 类：这个类实现了服务器套接字，该对象等待通过网络的请求。
```java
 public class ServerSocket implements java.io.Closeable
```

### 构造方法
常用的构造方法：
```java
//使用该构造方法在创建ServerSocket对象时，就可以将其绑定到一个指定的端口号上，参数port就是端口号。
public ServerSocket(int port);
```
### 成员方法
public Socket accept() ：侦听并接受连接，返回一个新的 Socket 对象，用于和客户端实现通信。该方法会一直阻塞直到建立连接。

![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/a3c43445-9648-4295-b276-de68103497a8)

图解:
1. 【服务端】启动,创建 ServerSocket 对象，等待连接。
2. 【客户端】启动,创建 Socket 对象，请求连接。
3. 【服务端】接收连接,调用 accept 方法，并返回一个 Socket 对象。
4. 【客户端】 Socket 对象，获取 OutputStream ，向服务端写出数据。
5. 【服务端】 Scoket 对象，获取 InputStream ，读取客户端发送的数据。
客户端向服务端发送数据成功
6. 【服务端】 Socket 对象，获取 OutputStream ，向客户端回写数据。
7. 【客户端】 Scoket 对象，获取 InputStream ，解析回写数据。
8. 【客户端】释放资源，断开连接

## 服务端代码：
```java
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerTCP {
  public static void main(String[] args) throws IOException {
    System.out.println("服务端启动 , 等待连接 .... ");
    // 1.创建 ServerSocket对象，绑定端口，开始等待连接
    ServerSocket ss = new ServerSocket(8888);
    // 2.接收连接 accept 方法, 返回 socket 对象.
    Socket server = ss.accept();
    // 3.通过socket 获取输入流
    InputStream is = server.getInputStream();
    // 4.一次性读取数据
    // 4.1 创建字节数组
    byte[] b = new byte[1024];
    // 4.2 据读取到字节数组中.
    int len = is.read(b);
    // 4.3 解析数组,打印字符串信息
    String msg = new String(b, 0, len);
    System.out.println(msg);
    // =================回写数据=======================
    // 5. 通过 socket 获取输出流
    OutputStream out = server.getOutputStream();
    // 6. 回写数据
    out.write("收到信息了".getBytes());
    // 7.关闭资源.
    out.close();
    is.close();
    server.close();
  }
}
```

## 客户端代码 45
```java
mport java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTCP {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端 发送数据");
        // 1.创建 Socket ( ip , port ) , 确定连接到哪里.
        Socket client = new Socket("localhost", 8888);
        // 2.通过Scoket,获取输出流对象
        OutputStream os = client.getOutputStream();
        // 3.写出数据.
        os.write("我是客户端发的信息".getBytes());
        // ==============解析回写=========================
        // 4. 通过Scoket,获取 输入流对象
        InputStream in = client.getInputStream();
        // 5. 读取数据数据
        byte[] b = new byte[100];
        int len = in.read(b);
        System.out.println(new String(b, 0, len));
        // 6. 关闭资源 .
        in.close();
        os.close();
        client.close();
    }
}
```

文件上传
步骤：
1. 【客户端】输入流，从硬盘读取文件数据到程序中。
2. 【客户端】输出流，写出文件数据到服务端。
3. 【服务端】输入流，读取文件数据到服务端程序。
4. 【服务端】输出流，写出文件数据到服务器硬盘中。
5. 【服务端】获取输出流，回写数据。
6. 【客户端】获取输入流，解析回写数据。
服务端代码：

```java
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileUploadServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器 启动..... ");
        // 1. 创建服务端ServerSocket
        ServerSocket serverSocket = new ServerSocket(9999);
        // 2. 循环接收,建立连接
        Socket accept = serverSocket.accept();
        //3.1 获取输入流对象
        //3.2 创建输出流对象, 保存到本地 .
        try (BufferedInputStream bis = new
            BufferedInputStream(accept.getInputStream());
            FileOutputStream fis = new FileOutputStream("D:\\temp\\" +
            System.currentTimeMillis() + ".jpg");
            BufferedOutputStream bos = new BufferedOutputStream(fis)) {
            // 3.3 读写数据
            byte[] b = new byte[1024 * 8];
            int len;
            while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
            }
            // 4.=======信息回写===========================
            System.out.println("back ........");
            OutputStream out = accept.getOutputStream();
            out.write("上传成功".getBytes());
            out.close();
            //================================
            //5. 关闭 资源
            bos.close();
            bis.close();
            accept.close();
            System.out.println("文件上传已保存");
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
}

客户端代码：
```java
import java.io.*;
import java.net.Socket;
public class FileUploadClient {
    public static void main(String[] args) throws IOException {
        // 1.创建流对象
        // 1.1 创建输入流,读取本地文件
        BufferedInputStream bis = new BufferedInputStream(new
        FileInputStream("d:\\temp\\zixiafaerie.jpg"));
        // 1.2 创建输出流,写到服务端
        Socket socket = new Socket("localhost", 9999);
        BufferedOutputStream bos = new
        BufferedOutputStream(socket.getOutputStream());
        //2.写出数据.
        byte[] b = new byte[1024 * 8 ];
        int len ;
        while (( len = bis.read(b))!=-1) {
            bos.write(b, 0, len);
        }
        // 关闭输出流,通知服务端,写出数据完毕
        socket.shutdownOutput();
        System.out.println("文件发送完毕");
        // 3. =====解析回写============
        InputStream in = socket.getInputStream();
        byte[] back = new byte[20];
        in.read(back);
        System.out.println(new String(back));
        in.close();
        // ============================
        // 4.释放资源
        socket.close();
        bis.close();
    }
}
```
### 优化：
- 服务器只能处理一个客户端请求，接收完一个图片之后，服务器就关闭了
  - 循环
- 第二次上传文件的时候，会把第一次的文件给覆盖
    - 重命名文件 UUID
- 使用循环虽然可以让服务器处理多个客户端请求。但是还是无法同时跟多个客户端进行通信

## 创建线程

```java
// 线程任务类
public class ThreadSocket implements Runnable {
    private Socket acceptSocket;
    public ThreadSocket(Socket accept) {
        this.acceptSocket = accept;
    }
    @Override
    public void run() {
      BufferedOutputStream bos = null;
      try {
          //网络中的流,从客户端读取数据的
          BufferedInputStream bis = new
          BufferedInputStream(acceptSocket.getInputStream());
          //本地的IO流,把数据写到本地中,实现永久化存储
          bos = new BufferedOutputStream(new FileOutputStream("D:\\temp\\"+ UUID.randomUUID().toString() + ".jpg"));
          int b;
          while((b = bis.read()) !=-1){
          bos.write(b);
      }
      BufferedWriter bw = new BufferedWriter(new
      OutputStreamWriter(acceptSocket.getOutputStream()));
      bw.write("上传成功");
      bw.newLine();
      bw.flush();
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          if(bos != null){
              try {
                  bos.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (acceptSocket != null){
          try {
                acceptSocket.close();
              } catch (IOException e) {
                e.printStackTrace();
              }
          }
      }
    }
}
// 服务器代码
public class ServerDemo {
    public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket(10000);
    while (true) {
        Socket accept = ss.accept();
        ThreadSocket ts = new ThreadSocket(accept);
        new Thread(ts).start();
    }
      //ss.close();
    }
}
```
- 使用多线程虽然可以让服务器同时处理多个客户端请求。但是资源消耗太大。
  - 线程池
 
```java
public static void main(String[] args) throws IOException {
  ServerSocket ss = new ServerSocket(10000);
  ThreadPoolExecutor pool = new ThreadPoolExecutor(
  3,//核心线程数量
  10, //线程池的总数量
  60, //临时线程空闲时间
  TimeUnit.SECONDS, //临时线程空闲时间的单位
  new ArrayBlockingQueue<>(5),//阻塞队列
  Executors.defaultThreadFactory(),//创建线程的方式
  new ThreadPoolExecutor.AbortPolicy()//任务拒绝策略
  );
  while (true) {
    Socket accept = ss.accept();
    ThreadSocket ts = new ThreadSocket(accept);
    //new Thread(ts).start();
    pool.submit(ts);
  }
  //ss.close();
}
```
