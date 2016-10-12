package mod.decorate;

/**
 * 装饰模式
 * 1.被装饰抽象的事物是谁（phone接口）
 * 2.具体的事物(phoneDome实现phone接口的实现类)
 * 3.对事物进行装饰的抽象类（接口呢？）
 * 4.对事物进去装饰的具体类
 */
public class DecorateTest {
    public static void main(String[] args) {
        //手机可以打电话
        Phone p = new PhoneBase();
        p.call();
        System.out.println("**********************");
        //手机可以改颜色，手机可以打电话
        PhoneAbstrc phone = new ColorAbstrcPhone(p);
        phone.call();;
        System.out.println("**********************");
        //手机可以打电话，手机可以听音乐
        phone = new MusicAbstrcPhone(p);
        phone.call();
        System.out.println("***********************");
        //手机可以听音乐，手机可以打电话，手机可以改颜色
        phone   = new MusicAbstrcPhone(new ColorAbstrcPhone(p));
        phone.call();
    }
}
