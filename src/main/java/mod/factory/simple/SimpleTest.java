package mod.factory.simple;

/**
 * cat 在factory没有对应代码创建所以报错
 */
public class SimpleTest {
    public static void main(String[] args) {
        Animale dog = Factory.getAnimal("dog");
        Animale pig = Factory.getAnimal("pig");
        //Animale cat = Factory.getAnimal("cat");
        dog.ps();
        pig.ps();
       // cat.ps();
    }


}
