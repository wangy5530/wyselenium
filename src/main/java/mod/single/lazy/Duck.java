package mod.single.lazy;
/**
 * Created by Administrator on 2016/10/12.
 */
public class Duck {
    /**
     * 单例模式是为了保证内存中只有一个对象，通常用于线程池  JDBC连接池
     * 懒汉式单例模式
     * 构造方法私有化，防止其他类初始化对象
     * 下面定义了static变量 duck,可以保证其他类直接通过duck.getduck()方法获取
     *  本设计模式的精华在于  duck  和 getduck 被设置为static  无参构造方法被私有化 延迟加载
     *  和饿汉式单例模式区别在于 duck随类加载的时候不会被初始化，而是在第一次调用gerDuck()方法的时候被初始化
     *  通过判断duck是否为空箂确定是否new duck;
     */

    private Duck() {

    }

    private static Duck duck = null;

    public synchronized static Duck getDuck() {
        /**
         * 懒汉式单例模式在多线程时候存在安全问题
         * 比如 线程A  B
         * A判断duck等于null的时候 初始化duck的时候线程停止
         * B进入后判断duck=null，又要初始化一个duck
         * 这样就会导致两个对象被创建
         * 可以添加多线程同步关键字synchronized箂解决线程安全问题
         * jdk  runtime.class  就是饿汉式应用
         */
        if(duck==null){
            duck=new Duck();
        }
        return  duck;
    }

    public void pp() {
        System.out.println("鸭鸭已经下锅");
    }
}
