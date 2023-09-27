public class TestMain {
    public static void main(String[] args) {//测试生成公式和答案
//-n 10 -r 10
        int range=10;
        int count=10000;
        BuildFormula bf=new BuildFormula(range,count);
        bf.generate();
    }
}
