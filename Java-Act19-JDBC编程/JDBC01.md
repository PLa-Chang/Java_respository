# Statement、PreparedStatement、CallableStatement的区别？
- 相同点：
  - 都是执行SQL语句的接口
  - CallableStatement 继承 PreparedStatement，PreparedStatement 继承Statement
- 不同的：
  - PreparedStatement是**预编译SQL语句**，效率高于Statement
  - PreparedStatement可以**防止SQL注入**，安全性高于Statement
  - PreparedStatement支持** ? **占位符操作，相对于Statement更加灵活
  - CallableStatement适用于**执行存储过程**
# execute、executeQuery、executeUpdate的区别是什么？
- Statement中的`execute(String query)`方法用来执行任意的sql语句
  - 如果执行的是`select`语句，这个方法就返回`true`。如果执行的是update，insert，delete返回false。
  - 通过`getResultSet()`方法来获取ResultSet，通过getUpdateCount()方法来获取更新的记录条数。
- Statement中的`executeQuery(String query)`接口用来执行select查询，并且返回ResultSet。
- Statement中的`executeUpdate(String query)`方法来执行insert、update、delete、DDL语句。返回值是int类型，如果执行的是insert、update、delete语句的话，返回影响的条数，如果是DDL语句就返回0.
- 只有不确定是什么语句的时候才使用`execute()`方法，否则应该使用`executeQuery()或者executeUpdate()`方法。

# java.util.Date和java.sql.Date有什么区别？
java.sql.Date适用于与数据库交互时只需要保存日期部分的情况，而java.util.Date则适用于通用的日期和时间处理。具体区别：
1. java.util.Date保存了日期和时间的毫秒数，而java.sql.Date只保存日期部分，不
保存时间部分。
2. java.util.Date是一个通用的日期和时间类，可以用于存储任意日期和时间。而
java.sql.Date是java.util.Date的一个子类，专门用于与数据库交互时存储日期。
3. java.sql.Date可以直接与数据库中的日期类型进行交互，例如在使用JDBC时，
可以直接将java.sql.Date对象作为参数传递给SQL查询语句。而java.util.Date则
需要通过格式化或转换来与数据库进行交互。

# ResultSet、Statement、Connection关闭的顺序是什么？
先关闭ResultSet，再关闭Statement，最后关闭Connection。
为了保证能关闭，应使用try-catch-finally写jdbc程序
