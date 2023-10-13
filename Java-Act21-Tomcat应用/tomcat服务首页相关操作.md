启动 tomcat 服务后，我们在浏览器访问：http://localhost:8080或者http://127.0.0.1:8080，会出现如下页面：
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/4e347a70-7c19-4a55-800f-a0fd3eb4b152)

其中主要的操作有：
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/861bee6a-8efd-49e1-9a19-4dc0c66ab73d)
其中， Server Status 、 Manager App 、 Host Manager 需要设置用户名密码才能进入。
- 设置方法如下：
找到 tomcat/conf/tomcat-users.xml 文件:
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/0d9d5d72-e843-4088-b0af-6e5743f342b2)
在 <tomcat-users> 节点中添加如下代码：
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/185f7ae0-2697-418c-bc82-f0195018f521)


其中， manager-gui 是进入 Manager App 和 Server Status 的权限。
在 Manager App 界面可以看到当前 tomcat 服务上所有的应用相关信息。

![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/a43e3fed-35ca-4e7a-8df2-323c9016872b)
在这里可以部署项目
