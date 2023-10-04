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

# SQL中的事务有哪些特性？
事务有四个特性：ACID四个特性
- 原子性（Atomicity）：不可分割的最小操作单位，要么同时成功，要么同时失败。
- 一致性（Consistency）：数据操作前后数据总量不变。
- 隔离性（Isolation）：多个事务之间，相互独立。
- 持久性（Durability）：当事务提交或者回滚后，数据库会持久化的保存数据

# SQL事务并发会产生什么问题？
- **脏读（Dirty read）**: 当一个事务正在访问数据并且对数据进行了修改，而这种修改还没有提交到数据库中，这时另外一个事务也访问了这个数据，然后使用了这个数据。因为这个数据是还没有提交的数据，那么另外一个事务读到的这个数据是“脏数据”，依据“脏数据”所做的操作可能是不正确的。
- **不可重复读（Unrepeatable read）**: 指在一个事务内多次读同一数据。在这个事务还没有结束时，另一个事务修改了该数据。那么，在第一个事务中的两次读数据之间，由于第二个事务的修改导致第一个事务两次读取的数据可能不太一样。这就发生了在一个事务内两次读到的数据是不一样的情况，因此称为不可重复读。
- **丢失修改（Lost to modify）**: 指在一个事务读取一个数据时，另外一个事务也访问了该数据，那么在第一个事务中修改了这个数据后，第二个事务也修改了这个数据。这样第一个事务内的修改结果就被丢失，因此称为丢失修改。 例如：事务 1 读取某表中的数据 A=20，事务 2 也读取 A=20，事务 1 修改 A=A-1，事务 2 也修改 A=A-1，最终结果 A=19，事务 1 的修改被丢失。
- **幻读（Phantom read）:** 幻读与不可重复读类似。它发生在一个事务（T1）读取了几行数据，接着另一个并发事务（T2）插入了一些数据时。在随后的查询中，第一个事务（T1）就会发现多了一些原本不存在的记录，就好像发生了幻觉一样，所以称为幻读。

# SQL事务有哪些隔离级别？
- read uncommitted:读未提交
  - 最低的隔离级别，允许读取尚未提交的数据变更，可能**会导致脏读、幻读或不可重复读**。
= read committed：读已提交级别【Oracle默认】
  - 允许读取并发事务已经提交的数据，可以阻止脏读，但是**幻读或不可重复读仍有可能发生**。
- repeatable read：可重复读【MySQL默认】
  - 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但**幻读仍有可能发生**。
- serializable：可串行化
  - 最高的隔离级别，完全服从 ACID 的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以**防止脏读、不可重复读以及幻读**。
 
在MySQL中无法看到幻读的效果。但我们可以将事务隔离级别设置到最高，以挡住幻读的发生。

在 MySQL 中只有使用了 Innodb 数据库引擎的数据库或表才支持事务。
