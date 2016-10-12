package mod.single.hungry;
/**
 * Created by Administrator on 2016/10/12.
 */
public class HuangryTest {
    public static void main(String[] args) {
        /**
         * 这样初始化会报错，因为Chicken中无参构造方法已经被私有化
         */
        /**
         Chicken chicken1 = new Chicken();
         chicken1.pp();
         */
        Chicken chicken1 = Chicken.getChicken();
        Chicken chicken2 = Chicken.getChicken();
        //chicken1 和chicken 对象引用地址完全相同，打印TRUE
        System.out.println(chicken1==chicken2);

    }
}
