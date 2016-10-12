package mod.factory.more;

/**
 * 多工厂模式，每个对象都有自己的工厂类
 * Created by Administrator on 2016/10/12.
 */
public class MoreTest {
    public static void main(String[] args) {
        People men = new MenFactory().getPeople();
        People womean = new WomeanFactory().getPeople();
        womean.print();
        men.print();
    }
}
