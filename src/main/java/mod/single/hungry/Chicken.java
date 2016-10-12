package mod.single.hungry;

import java.security.PrivateKey;

/**
 * Created by Administrator on 2016/10/12.
 */
public class Chicken {
    /**
     * 单例模式是为了保证内存中只有一个对象，通常用于线程池  JDBC连接池
     * 饿汉式单例模式
     * 构造方法私有化，防止其他类初始化对象
     * 下面定义了static变量 chicken,可以保证其他类直接通过Chicken.getChicken()方法获取
     *  本设计模式的精华在于  chicken  和 getChicken 被设置为static  无参构造方法被私有化
     *  开发中一般采用饿汉模式而不用懒汉模式，原因是多线程安全问题
     */

    private Chicken() {

    }

    private static Chicken chicken = new Chicken();

    public static Chicken getChicken() {
        return chicken;
    }

    public void pp() {
        System.out.println("鸡鸡已经下锅");
    }
}
