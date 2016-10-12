package mod.factory.more;

/**
 * Created by Administrator on 2016/10/12.
 */
public class MenFactory implements PeopleFactory {
    public  People getPeople(){
        return new Men();
    }
}
