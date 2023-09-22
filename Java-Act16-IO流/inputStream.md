
# 读取字节
用到的一些点：

- `FileInputStream`：文件输入流，用于读取文件
    - `FileNotFoundException`：文件不存在异常。当文件不存在或不可读时，会抛出此异常
    - `read()`：读取一个字节，返回值为int类型，如果读取到文件末尾，返回-1
    - `read(byte[] b)`：读取b.length个字节，返回值为实际读取的字节数，如果读取到文件末尾，返回-1
    - `new String(byte[] b)`：将字节数组转换为字符串
    - `String(byte[] b, int offset, int length)`：将字节数组的一部分转换为字符串
- 文字乱码
    - 读取文件时，使用`FileInputStream的read()`方法读取字节，然后使用`new String(byte[] b)`方法将字节数组转换为字符串，如果文件编码格式与系统默认编码格式不一致，会导致乱码
        - 解决方法：使用`new String(byte[] b, int offset, int length, Charset charset)`方法，指定编码格式
    - 读取文件时，未将完整的字节数组转换为字符串，导致乱码
        - 解决方法：尽可能多的读取字节，直到读取到文件末尾
- `readAllBytes()`：读取文件的所有字节，返回值为字节数组
- 所有的流，都需要关闭，否则会导致资源泄露。关闭流的方法：
    1. `close()`：关闭流
    2. `try-with-resources`：自动关闭流。在try后面的括号中，创建流对象，try代码块结束后，会自动关闭流对象
        - 所有的流都实现了`AutoCloseable`接口，该接口中定义了`close()`方法，因此所有的流都可以使用`try-with-resources`语句

# 代码演示
```java
package com.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamDemo01 {
    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("d:/input-file.txt");
            // 返回可读取的字节数
            System.out.println(inputStream.available());
            // 从输入流中读取下一个字节
//            int read = inputStream.read();
//            System.out.println(read);

            // 跳过指定的字节数
            inputStream.skip(3);
            // 从输入流中读取数据，并将数据存储在缓冲区数组bytes中，返回实际读取的字节数
            byte[] bytes = new byte[14];
            int read = inputStream.read(bytes);
            System.out.println(read);
            // 将byte数组转换为字符串形式输出
            System.out.print(new String(bytes));

            // 返回可读取的字节数
            System.out.println(inputStream.available());
            System.out.println("==========================");
            // 读取文件中的每一个字符，条件是可读取的字节数大于0
            while (inputStream.available() > 0) {
                System.out.print((char) inputStream.read());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```
## 产生中文乱码的原因及其解决方法：
```java
package com.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 产生中文乱码的原因：
 *      1. 一个中文字符占用两个字节或多个字节
 *      2. 一个字节存储一个英文字母，一个中文字符只存储一半，所以产生了额乱码
 *      3. 编码不一致，产生乱码：编码不一致，就是编码和解码的时候使用的字符集不一致
 *
 *  Character.defaultCharset(): 获取当前系统的默认编码
 */
public class InputStreamDemo02 {
    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("d:/input-file.txt");

            byte[] bytes = new byte[1024];
            int size;
            while ((size = inputStream.read(bytes)) > 0){
                // 可以设置字符编码，默认是UTF-8
//                String s = new String(bytes, 0, size, Charset.forName("utf-8"));
                String s = new String(bytes, 0, size);
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                // 关闭流
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```
## 关闭流的方式：
```java
package com.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreamDemo03 {
    public static void main(String[] args) {
        // 关闭流的第二种方式：try-with-resources：自动关闭流。如下所示：
        try (FileInputStream fileInputStream = new FileInputStream("d:/input-file.txt")) {
            byte[] bytes = fileInputStream.readAllBytes();
            System.out.println(new String(bytes));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

```
