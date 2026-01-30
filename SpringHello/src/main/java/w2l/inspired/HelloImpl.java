package w2l.inspired;

public class HelloImpl implements Hello{
    private String str;

    public HelloImpl(String str) {
        this.str = str;
    }

    @Override
    public void sayHi() {
        System.out.println(str);
    }

    public void setStr(String str) {
        this.str = str;
    }
}
