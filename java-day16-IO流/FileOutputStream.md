# 字节流FileOutputStream 写入
用到的点：

- FileOutputStream：文件输出流.文件不存在时会创建文件
    - FileNotFoundException: 文件所在的目录不存在
    - write(int b)：写入一个字节
    - write(byte[] b)：写入字节数组
    - write(byte[] b, int offset, int length)：写入字节数组的一部分
可以使用追加的形式写入文件，即在构造方法中传入true，表示追加
        - 如果文件不存在，会创建文件
        - 如果文件存在，会在文件末尾追加内容
    - flush()：刷新缓冲区，将缓冲区中的内容写入文件
    - close()：关闭流
- 复制文件的步骤：

    - 创建FileInputStream对象，用于读取被复制文件
    - 创建FileOutputStream对象，用于写入复制文件
    - 使用read()方法读取字节，使用write()方法写入字节
    - 关闭流
- 也可以使用transferTo()方法，将输入流中的内容直接写入输出流中，不需要自己写循环读取和写入的代码 
- 还可以使用Files类的copy()方法，将文件复制到指定的目录下

## 代码演示
```java
  try {
            FileOutputStream outputStream = new FileOutputStream("d:/outPut-file.txt");
            outputStream.write('a');
            outputStream.write('b');
            outputStream.write('c');
            outputStream.write("de\nfg\rh".getBytes());

            // 关闭流【只有关闭流后，内容才会被写入文件里面】
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
```
### 即在构造方法中传入true，表示追加:
```java
        try {
            // 调用FileOutputStream 的另一个构造方法，传入 true 表示可以在该文件的后面继续追加内容
            FileOutputStream outputStream = new FileOutputStream("d:/outPut-file.txt",true);
//            outputStream.write('a');
//            outputStream.write('b');
//            outputStream.write('c');
//            outputStream.write("de\nfg\rh".getBytes());

            // 例如：上面已经在文件outPut-file.txt 中添加了内容，那么我们还可以继续添加，而不会覆盖之前的内容
            outputStream.write("ijklmnopq".getBytes());


            // 关闭流【只有关闭流后，内容才会被写入文件里面
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
```

## 文件复制
```java
package com.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 实现文件的复制功能
 * 第一种方法：通过byte 数组一个一个的写入到outputStream中
 * 第二种方法：通过readAllBytes 方法
 * 第三种方法：通过transferTo 方法
 */
public class BufferedOutputStreamDemo03 {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("d:/input-file.txt");
            FileOutputStream outputStream = new FileOutputStream("d:/kaifamiao/input-file.txt");

            // 第一种方法：通过byte 数组一个一个的写入到outputStream中
//            byte[] bytes = new byte[8193];
//            int size = 0;
//            if ((size = inputStream.read(bytes)) != -1){
//                outputStream.write(bytes,0, size);
//            }

            // 第二种方法：通过readAllBytes 方法
//            byte[] bytes1 = inputStream.readAllBytes();
//            outputStream.write(bytes1);

            // 第三种方法：通过transferTo 方法
            long l = inputStream.transferTo(outputStream);
            System.out.println(l);

            // 关闭流【只有关闭流后，内容才会被写入文件里面
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

```
