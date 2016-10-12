package mod.factory.simple;

/**
 * 简单工厂模式
 * 优点：客户端不再创建对象而是把责任丢给了工厂，客户端只负责工厂调用，从而明确各类职责（单一原则）
 * 缺点：由于工厂类负责所有对象创建，当子类不断增多的时候需要修改工厂创建代码（违反开闭原则）
 * 当 参数srs不为dog 或者pig 时候会返回空对象
 * 获取对象的方法？为啥是静态方法
 */
public class Factory {
    public static Animale getAnimal(String srs) {
        if (srs.equals("dog")) {
            return new Dog();
        } else if (srs.equals("pig")) {
            return new Pig();
        }
        return  null;
    }
}
