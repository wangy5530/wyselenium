package mod.decorate;

/**
 * Created by Administrator on 2016/10/12.
 */
public class ColorAbstrcPhone extends PhoneAbstrc {
    public ColorAbstrcPhone(Phone phone){
        super(phone);
    }

    @Override
    public void call() {
        System.out.println("手机可以改颜色");
        super.call();
    }
}
