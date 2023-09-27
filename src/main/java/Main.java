public class Main {
    public static void main(String[] args) {
//-n 10 -r 10
        int range=Integer.valueOf(args[1]);//生成题目的个数
        int count=Integer.valueOf(args[3]);//控制题目中数值的范围
        BuildFormula bf=new BuildFormula(range,count);
        FileOperation fo=new FileOperation();
        for(int i=0;i<count;i++){
            bf.generate();
        }


    }
}
