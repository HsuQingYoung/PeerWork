import java.io.IOException;

public class TestCheckAnswer {//测试校对答案
    public static void main(String[] args) {
        checkAnswer ca=new checkAnswer();
        try {
            ca.check();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
