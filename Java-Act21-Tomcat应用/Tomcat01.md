# 1.Tomcat服务启动常见问题
1. 无法启动（闪退：cmd命令窗口出现一下，就消失）
- 主要原因：没有配置JAVA_HOME环境变量。 JAVA_HOME 环境变量 中配置的是JDK的安装目录，不包含bin目录，不是tomcat的安装目录。
- 闪退的原因查看：可以在 startup.bat 文件末尾书写 pause 命令。让运行的窗口暂停。
2. 端口被占用导致启动失败
- 如果启动的时候，发生异常问题，这时有可能是端口被占用。Tomcat服务器在启动的时候默认占用本地的8080端口，如果这个端口被占用，启动的时候就会报错。
- 报错内容可以通过查询 tomcat 目录下的 logs 目录中 Catalina.当前系统年月日.log 文件查看，如下图：
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/c63fb476-c833-410a-87f0-0eac8ffd12c8)

 解决方式：
1. 结束当前占用此端口的进程（之前讲过，使用 netstat -ano 命令，然后找到进程并结束掉）
2. 修改 tomcat 端口
  - 到 tomcat 目录下的 conf 目录（ tomcat 的配置文件都在此文件夹下），找到 server.xml 文件，打开。
> server.xml:服务器端口配置、服务器自身配置文件

![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/86495735-a96b-4e40-b9fa-2222aecf51dd)

- 将端口号修改为其他数值，如 8888 。修改完毕后保存，并重新启动 tomcat 后生效。
- 浏览器访问：http://localhost:8888

3. 启动窗口乱码问题
在 tomcat/conf 目录下，找到 logging.properties 并打开，修改如下：
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/9933a918-f0bf-4958-ad32-16dde0ab6197)

# 2.tomcat目录介绍
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/c8e87e60-d188-40f1-ae6c-1098aa92b6bd)

# 3. 使用tomcat发布项目

## 第一种方式：在webapps下发布
将需要发布web项目或打包好的 war 包复制到 tomcat 的 webapps 目录下。然后启动 tomcat 就可以通过浏览器访问了。
如： test 项目中有一个 hello.txt

## 第二种方式：使用虚拟路径发布
有两种方式：
1. 配置 conf/server.xml
2. 配置独立 xml 文件
配置server.xml，添加context标签
在` tomcat/conf/server.xml `中找到` <Host>` 节点，添加 `<context> `标签，如图所示：
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/65375973-f36e-49d8-95ab-889fe1df1386)

> 注意，如果 server.xml 配置出错会导致 tomcat 无法启动

```xml
<!-- path是浏览器访问地址， docBase是磁盘地址 -->
<Context docBase="D:\temp" reloadable="true" path="/files" />
```

然后在 tomcat/conf/web.xml 文件中找到 servlet 节点，修改如下：
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/daa83814-dd2d-49a1-8d28-b244e3a967cf)
重启 tomcat 服务后，浏览器访问： http://localhost:8080/files

## 第三种方式：配置独立xml文件
- 在 tomcat/conf 目录下新建一个 Catalina 目录（如果已经存在无需创建）
- 在 Catalina 目录下创建 localhost 目录
- 在 localhost 中创建 xml 配置文件，名称为：abc.xml（注：这个名称是浏览器访问路径）
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/d9ff4aa2-3651-4527-8d56-c25210c98664)

xml 的内容为：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context docBase="D:\temp" reloadable="true"/>
```
保存后即可访问。
> 这种方式无需重启服务器自动加载和卸载项目
