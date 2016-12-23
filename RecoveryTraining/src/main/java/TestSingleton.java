/**
 * Created by jason on 16-11-3.
 */
public class TestSingleton {
    private final static TestSingleton instance = new TestSingleton();

    private TestSingleton(){
        System.out.println("i am the constructor of testsigleton!");
    }
    public static  TestSingleton getInstance(){
        return instance;
    }
}
