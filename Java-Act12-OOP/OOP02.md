# 继承

    extends 用于继承, 对现有类的一种扩展。
    class A extends B A 是子类/派生类,  B 父类 / 基类

    继承了些什么？  只要不是 private 修饰都可以被继承， 构造不能被继承。
    类只能单继承

    直接继承
    间接继承

    java 类如果没有用 extends 去显式的继承一个类，则这个类继承了 java.lang.Object 类
    Object 类是所有类的 父类 / 基类

    final 最终的， 被 final 修饰的类不能被继承
                 被 final 修饰的方法不能重写
                 被 final 修饰的变量不能重新赋值，称为 常量. 在声明时就赋值
                 引用数据类型可以修改字段(不是final)的值
        可以被继承的。

重写(override 覆盖)：继承关系中，子类中有和父类方法同名同参同返回值类型的方法。子类重写了的父类的方法：
- 访问权限要相同或变大
- 返回值类型要相同，是同一个类型的. 可以存在继承关系

     注解 @Override 标识重写
## instanceof关键字


instanceof 是否属于某一个类型, 返回 boolean 值
```java
Student stu = new Student();
        if (stu instanceof Student) {
           System.out.println("属于");
       }
```
##instanceof关键字
字段、方法 访问修饰符（权限限定符） 三个关键字四种权限：
- public 公共/公开的，任何地方都能访问
- protected 受保护的， 同包以及不同包 子类 中能访问
- (不写)  package-access/package-private 包访问权限， 同包中访问
- private 私有的, 只能在当前类中访问
