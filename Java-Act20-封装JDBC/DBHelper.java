package com.JDBC;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义JDBC工具类
 */
public class DBHelper {
    // 数据库连接信息
    private String url;
    private String user;
    private String password;
    // 操作对象
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    // 是否自动提交事务
    private Boolean autoCommit = true;

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 有参构造
     * @param url   数据库连接
     * @param user 用户名
     * @param password 密码
     */
    public DBHelper(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * 获取连接
     * @return 返回连接
     * @throws SQLException sql 异常
     */
    public Connection getConnection() throws SQLException {
        if (conn == null ||conn.isClosed()){
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    /**
     * 开启事务
     * @throws SQLException sql 异常
     */
    public void beginTransaction() throws SQLException {
        getConnection();
        // 开启事务,设置自动提交为false，如果连接已经关闭并且自动提交为true,那么就需要重新获取连接
        if (conn != null && !conn.isClosed() && autoCommit) {
            conn.setAutoCommit(false);
            autoCommit = false;
        }
    }

    /**
     * 提交事务
     * @throws SQLException sql 异常
     */
    public void committed() throws SQLException {
        if (conn != null && !conn.isClosed() && autoCommit == false) {
            conn.commit();
            autoCommit = true;
        }
    }

    /**
     * 回滚事务
     * @throws SQLException sql 异常
     */
    public void rollback() throws SQLException {
        if (conn != null && !conn.isClosed() && !autoCommit) {
            conn.rollback();
            autoCommit = false;
        }
    }

    /**
     * 更新数据
     * @param sql sql 语句
     * @param params 参数
     * @return 返回更新的行数
     * @throws SQLException sql 异常
     */
    public int update(String sql, Object... params) throws SQLException {
        // 获取连接
        getConnection();

        // 获取数据库操作对象
        ps = conn.prepareStatement(sql);

        // 设置值
        setParameters(params);

        // 执行sql 语句
        int updateRows = ps.executeUpdate();
        return updateRows;
    }

    /**
     * 插入数据
     * @param sql sql 语句
     * @param params 参数
     * @return 返回自增长的值
     * @throws SQLException sql 异常
     */
    public int insert(String sql, Object... params) throws SQLException {
        // 获取连接
        getConnection();

        // 获取数据库操作对象
        ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // 设置值
        setParameters(params);

        // 执行sql 语句
        ps.executeUpdate();
        // 获取自增长值
        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    /**
     * 查询单条数据
     * @param sql sql 语句
     * @param tClass 返回的数据类型
     * @param params 参数
     * @return 返回查询的数据
     * @param <T> 返回的数据类型
     * @throws SQLException sql 异常
     * @throws NoSuchMethodException 没有对应的方法
     */
    public <T> T selectById(String sql, Class<T> tClass, Object... params) throws SQLException, NoSuchMethodException {
        // 获取连接
        getConnection();

        ps = conn.prepareStatement(sql);

        // 设置值
        setParameters(params);

        rs = ps.executeQuery();
        // 解析结果
        if (rs.next()) {
            return mapper(rs, tClass);
        }
        return null;
    }

    /**
     * 解析结果集
     * @param rs 结果集
     * @param tClass 返回的数据类型
     * @return 返回解析后的数据
     * @param <T> 返回的数据类型
     * @throws NoSuchMethodException  没有对应的方法
     */
    private <T> T mapper(ResultSet rs, Class<T> tClass) throws NoSuchMethodException {
        try {
            // 创建对象
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            T t = constructor.newInstance();

            // 开始解析结果集
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                // 获取列名
                String columnName = metaData.getColumnLabel(i).toLowerCase();

                // 判断是否由多个单词组成,
                if (columnName.contains("_")) {
                    // 有多个单词组成，转换为驼峰命名
                    columnName = toCamelCase(columnName);
                }

                try {
                    // 获取属性描述器,PropertyDescriptor 用于获取属性的描述信息,比如属性的类型,属性的名字等等,
                    // 传递两个参数,第一个参数是属性的名字,第二个参数是属性所在的类
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, tClass);

                    // 获取写方法
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    // 设置可访问
                    writeMethod.setAccessible(true);

                    // 设置值
                    Object value = rs.getObject(i);
                    // 调用写方法，invoke(对象，值),相当于对象.setXXX(值),这里的对象是t,值是value,所以相当于t.setXXX(value)
                    // 这里的columnName是驼峰命名,所以相当于t.setColumnName(value)
                    writeMethod.invoke(t,value);
                } catch (IntrospectionException e) {
                    // 没有对应的属性
                    System.err.println("没有对应的属性：" + columnName + "，跳过");
                }
            }
            // 返回对象
            return t;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询多条数据
     * @param sql sql 语句
     * @param tClass 返回的数据类型
     * @param params 参数
     * @return  返回查询的数据
     * @param <T> 返回的数据类型
     * @throws SQLException sql 异常
     * @throws NoSuchMethodException 没有对应的方法
     */
    public <T>List<T> selectList(String sql, Class<T> tClass, Object... params) throws SQLException, NoSuchMethodException {
        getConnection();
        // 创建 prepareStatement
        ps = conn.prepareStatement(sql);

        // 设置值
        setParameters(params);

        // 执行操作
        rs = ps.executeQuery();
        List<T> list = new ArrayList<>();
        //  解析结果
        while (rs.next()) {
            T t = mapper(rs, tClass);
            list.add(t);
        }
        return list;
    }

    /**
     * 查询多条数据
     * @param params 参数
     * @throws SQLException  sql 异常
     */
    private void setParameters( Object... params) throws SQLException {
        if (params != null && params.length > 0){
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1,params[i]);
            }
        }
    }

    /**
     * 关闭连接、释放资源
     */
    public void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            rs = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ps = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            conn = null;
        }
    }

    /**
     * 将下划线命名转换为驼峰命名
     * @param name 下划线命名
     * @return 驼峰命名
     */
    public String toCamelCase(String name){
        String[] strings = name.split("_");
        StringBuilder builder = new StringBuilder(strings[0]);
        // 从第二个单词开始
        for (int i = 1; i < strings.length; i++) {
            String string = strings[i];
            builder.append(string.substring(0,1).toUpperCase()).append(string.substring(1).toLowerCase());
        }
        return builder.toString();
    }
}
