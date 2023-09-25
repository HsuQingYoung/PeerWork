public class TestMain {
    public static void main(String[] args) {
//-n 10 -r 10
        int range=10;
        int count=10;
        BuildFormula bf=new BuildFormula(range);
        Fraction []t=new Fraction[count];
        FileOperation fo=new FileOperation();
        for(int i=0;i<count;i++){
            t[i]=bf.generate();
            fo.writeAnswerIntoTxt(t[i].toString());
        }


    }
}
