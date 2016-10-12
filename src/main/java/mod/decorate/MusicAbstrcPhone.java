package mod.decorate;

/**
 * Created by Administrator on 2016/10/12.
 */
public class MusicAbstrcPhone extends PhoneAbstrc {
    public MusicAbstrcPhone(Phone   phone){
        super(phone);
    }

    @Override
    public void call() {
        super.call();
        System.out.println("手机可以播彩铃");
    }
}
