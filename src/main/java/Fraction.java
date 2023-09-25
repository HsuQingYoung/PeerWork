public class Fraction {//分数
    int numerator;//分子
    int denominator;//分母
    boolean isNegative=false;//该分数是否为负

    public void createFraction(int numerator, int denominator){
        if(denominator==0){
            throw new RuntimeException("分母不能为0");
        }
        int isNegative=(numerator*denominator<0?1:0);
        if(isNegative==1){
            this.isNegative=true;
        }
        numerator=Math.abs(numerator);
        denominator=Math.abs(denominator);

        int c=gcd(numerator,denominator);//最简分数
        this.numerator=numerator/c;
        this.denominator=denominator/c;

    }

    private int gcd(int a, int b) {
        if(a%b==0) {
            return b;
        }else {
            return gcd(b,a%b);
        }
    }

    public Fraction(int numerator, int denominator) {
        createFraction(numerator,denominator);
    }

    public Fraction(String fraction){
        int numerator;
        int denominator;
        fraction.trim();
        int indexOfPoite=fraction.indexOf("'");
        int indexOfLine=fraction.indexOf('/');

        if(indexOfPoite!=-1){
            int integer=Integer.valueOf(fraction.substring(0,indexOfPoite));
            denominator=Integer.valueOf(fraction.substring(indexOfLine+1));
            numerator=integer*denominator+Integer.valueOf(fraction.substring(indexOfPoite+1,indexOfLine));
        }else if(indexOfLine!=-1){
            denominator=Integer.valueOf(fraction.substring(indexOfLine+1));
            numerator=Integer.valueOf(fraction.substring(0,indexOfLine));
        }else{
            numerator=Integer.valueOf(fraction);
            denominator=1;
        }
        createFraction(numerator,denominator);

    }
    public Fraction add(Fraction fraction){
        return new Fraction(this.numerator*fraction.denominator+this.denominator*fraction.numerator,this.denominator*fraction.denominator);
    }

    public Fraction sub(Fraction fraction){
        return new Fraction(this.numerator*fraction.denominator-this.denominator*fraction.numerator,this.denominator*fraction.denominator);
    }

    public Fraction mul(Fraction fraction){
        return new Fraction(fraction.numerator*this.numerator,fraction.denominator*this.denominator);
    }

    public Fraction div(Fraction fraction){
        return new Fraction(this.numerator*fraction.denominator,this.denominator*fraction.numerator);
    }

    public String toString() {
        if(isNegative==true){
            if(denominator==1) {
                return "-"+String.valueOf(numerator);
            }else
            if(numerator>denominator) {
                return "-"+String.format("%d'%d/%d", numerator/denominator,numerator%denominator,denominator);
            }else {
                return "-"+String.format("%d/%d", numerator,denominator);
            }
        }else{
            if(denominator==1) {
                return String.valueOf(numerator);
            }else
            if(numerator>denominator) {
                return String.format("%d'%d/%d", numerator/denominator,numerator%denominator,denominator);
            }else {
                return String.format("%d/%d", numerator,denominator);
            }
        }

    }


}
