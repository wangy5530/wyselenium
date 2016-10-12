package mod.single.lazy;

/**
 * Created by Administrator on 2016/10/12.
 */
public class LazyTest {
    public static void main(String[] args) {
        Duck duck1 = Duck.getDuck();
        Duck duck2 = Duck.getDuck();
        System.out.println(duck1==duck2);
    }
}
