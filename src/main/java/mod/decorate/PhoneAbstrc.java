package mod.decorate;

/**
 * Created by Administrator on 2016/10/12.
 */
public abstract class PhoneAbstrc implements Phone {
    private Phone phone;

    public PhoneAbstrc(Phone phone){
        this.phone = phone;
    }

    public void call(){
        this.phone.call();
    }

}
