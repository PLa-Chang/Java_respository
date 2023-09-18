package com.enumeration;

/**
 * 单例模式
 * 单实例，在项目中只有一个实例。
 * 步骤：
 *  1. 构造方法私有(private)
 *  2. 提供获取唯一实例的方式
 * 单例模式有以下四种：
 * - 饿汉
 * - 懒汉
 * - 枚举
 * - 静态嵌套类
 */
// 饿汉单例模式
public class Singleton {
    private static final Singleton SINGLETON = new Singleton();
    // 构造私有
    private Singleton(){ }
    public static Singleton getInstance() {
        // 提供唯一获取实例的方式
        return SINGLETON;
    }
}
// 懒汉单例模式
class LazySingleton{
    private static LazySingleton singleton;
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        if (singleton == null){
            singleton = new LazySingleton();
        }
        return singleton;
    }

}
// 枚举
enum EnumSingleton{
    SINGLETON;
}
// 静态嵌套类
class OutClass{
    private OutClass(){
        if (StaticNestedClass.outClass != null){
            throw new RuntimeException("已有实例");
        }
    }
    private static class StaticNestedClass{
        private static final OutClass outClass = new OutClass();
    }
    public static OutClass getInstance(){
        return StaticNestedClass.outClass;
    }
}
class Test2{
    public static void main(String[] args) {
        OutClass sing1 = OutClass.getInstance();
        OutClass sing2 = OutClass.getInstance();
        System.out.println(sing1);  //com.enumeration.Singleton@3b07d329
        System.out.println(sing2);  //com.enumeration.Singleton@3b07d329
        System.out.println(sing1 == sing2); // true
    }
}

