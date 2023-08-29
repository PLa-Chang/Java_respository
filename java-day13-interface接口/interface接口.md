## 接口的基础语法

1、接口也是一种”引用数据类型“，编译之后还是一个.class的字节码文件。。

2、接口是完全抽象的（抽象类是半抽象的）。。

3、接口使用：interface定义的。。

4、接口支持多继承，一个接口可以继承多个接口。。

5、接口中只包含两部分内容：一是常量，，二是抽象方法（方法里面不能有方法体）。。

6、接口中的所有元素都是公开的（public修饰），所以在抽象方法定义时：public   abstract修饰符可以省略，默认就是public abstract。

7、常量中的 public static final 也是可以省略的。。

但是注意要初始化常量。

```java
public class test{
    public static void main(String args[])  {
        System.out.println(A.num);///3.1415926
    }
}
interface A{
    //常量:不能修改的
    public static final double pi = 3.1415926;
    double num = 3.1415926;// public static final 也是可以省略的。。
    //方法：
    public abstract int sub(int a,int b);//等同于下面的
    int sum(int a,int b);
}
```

8、接口的实现：用implements关键字（也可以将实现看作“继承”）

- 当一个非抽象的类实现接口时，必须将接口中的所有抽象方法全部实现（重写）。。
- 如果继承和实现同时出现时，先extends，后implements。。

```java
public class test{
    public static void main(String args[])  {
        B b = new word();
        A a = new word();
        System.out.println(b.sum(2,15));//面向接口编程
        System.out.println(a.sub(2,15));
    }
}
class Number{
}
interface A{
    double num = 3.1415926;
    int sub(int a,int b);
}
interface B{
    int sum(int a,int b);
}
class word extends Number implements A,B{
    public int sub(int a,int b){
        return a-b;
    }
    public int sum(int a,int b){
        return a+b;
    }
}
```

9、接口在开发中的作用：

​		面向接口编程，可降低程序的耦合度，提高扩展能力，符合ocp开发原则。。

​		接口的使用离不开多态机制，（接口+多态才可以降低耦合）

```java
public class TestTemp {
    public static void main(String args[])  {
        Total t = new Son1();
        Father f = new Father(t);
        f.print();  //this Son1
                    //this Son2
    }
}
interface Total{
    void T1();
    void T2();
}

/**
 * 实现接口Total
 */
class Son1 implements Total{
    // 重写接口中的方法
    @Override
    public void T1(){
        System.out.println("this Son1");
    };
    @Override
    public void T2(){
        System.out.println("this Son2");
    };
}
class Father{
    private Total total;
    private Father(){}
    public Father(Total total){
        this.total=total;
    }
    public void setTotal(Total total) {
        this.total = total;
    }
    public Total getTotal() {
        return total;
    }
    public void print(){
        total.T1();
        total.T2();
    }
}
```

10、JDK8之后 接口中允许声明 default 方法和 static方法。

- default方法表示默认方法，可以有方法体，而且实现类可以不重写此方法。
- 接口中的static 方法属于接口，只能通过**`接口.方法名`** 来调用

- JDK9 中可以将接口中的方法声明为 private 方法。

- ```java
  public interface USB {
      // 抽象方法
      void change();
      // static方法：只能用USB.print()调用
      static void print(){
          System.out.println("正在充电80%");
      }
      // default 方法
      default void test(){
          System.out.println("用于排序的测试……");
          //调用下面的private方法
          sort();
      }
      // 声明接口中的 private 方法
      private void sort(){
          System.out.println("排序");
      }
  }
  ```

## 总结 **接口 和 抽象 的区别：**

| 抽象类                                                       | 接口                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 子类只能继承一个抽象类                                       | 接口可以继承多个父接口                                       |
| 子类使用extends 继承抽象类                                   | 通过implements 实现多个接口                                  |
| 抽象类中可以有实例成员、静态成员、抽象方法。**抽象类中的成员不能使用default关键字** | 接口中是能有常量、抽象方法。JDK8.0及其之后的版本中可以有static方法和default方法。 |
| 抽象类中可以定义变量，也可以定义常量                         | 接口中只能定义常量（public static final修饰的变量）          |
| 子类在实现抽象方法时不允许缩小访问权限                       | 子类在实现抽象方法时必须指定public权限                       |
| 抽象方法中可以有构造方法                                     | 接口中不能定义构造方法                                       |
| 相同点：两者都不能实例化，都是引用类型。都可以包含抽象方法。 |                                                              |

函数式接口的拓展见一篇文章。