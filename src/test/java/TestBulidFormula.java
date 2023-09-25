public class TestBulidFormula {
    public static void main(String[] args) {
        BuildFormula bf=new BuildFormula(10);
        Fraction[] a=new Fraction[100];
        for(int i=0;i<100;i++){
            a[i]=bf.generate();
        }
        for(Fraction f:a){
            System.out.println(f.toString());
        }

    }
}
