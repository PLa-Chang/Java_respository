
在Java 16中引入了record类，它是一种特殊的类，主要用于表示数据传输对象（DTO）或不可变数据实体。record类的目标是简化POJO（Plain Old Java Object）的创建，它自动生成一些通用方法，如`equals()`、`hashCode()`、`toString()`等，从而减少了样板代码的编写。以下是一个典型的record类的示例：

```
public record Person(String name, int age) {
}
```

在上面的示例中，我们创建了一个名为`Person`的record类，它具有两个成员字段：`name`和`age`。此外，record类会自动生成以下方法：

1. `equals(Object obj)`：用于比较两个对象是否相等，比较的是record类的字段值。
2. `hashCode()`：生成一个哈希码，通常用于存储对象在哈希表中的位置。
3. `toString()`：生成一个字符串表示，通常返回类名和字段的字符串表示。

record类的成员字段是不可变的，这意味着一旦创建对象，它们的值不能更改。你可以使用record类的构造方法来创建对象，但一旦创建后，字段的值就不能再次修改。

以下是如何使用record类的示例：

```java
public class RecordExample {
    public static void main(String[] args) {
        // 创建record对象
        Person person = new Person("Alice", 30);

        // 访问字段
        String name = person.name();
        int age = person.age();

        // 输出对象的字符串表示
        System.out.println(person); // 输出：Person[name=Alice, age=30]

        // 使用equals比较对象
        Person anotherPerson = new Person("Bob", 30);
        boolean isEqual = person.equals(anotherPerson); // 返回false

        // 使用hashCode
        int hashCode = person.hashCode();

        // record对象是不可变的，不能更改字段的值
        // person.name = "Charlie"; // 这会引发编译错误
    }
}
```

需要注意的是，record类的字段是`final`的，因此它们不能被修改。这使得record类非常适合表示不可变的数据。记录类型的引入大大简化了Java中的数据传输对象（DTO）的创建，减少了样板代码的编写，提高了代码的可读性和可维护性。


```java
/**
 * record 关键字声明类，
 * https://docs.oracle.com/javase/specs/jls/se17/html/jls-8.html#jls-8.10
 * 相当于声明了一个类只有一个有参构造的类，这个中有私有字段
 * 继承 java.lang.Record
 *  同时还有获取字段值得方法（方法名是字段名/形参名），
 *   没有 setter 方法.record 声明的对象一旦创建，内部字段的值不能变
 *   重写 toString  equals  hashCode 方法
 *
 *  public final class Person extends java.lang.Record{
 *
 *      private String name;
 *
 *      private int age;
 *
 *      public String name(){
 *          return this.n;
 *      }
 *
 *  }
 */
public record Person (String name, int age) {

    public static int a;

    public void say(){
        System.out.println("say");
    }
}

```





