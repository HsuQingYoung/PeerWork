import java.util.Random;

public class BuildFormula {
    int range;//数值的范围

    public BuildFormula(int range) {
        this.range = range;
    }

    public Fraction generate(){//生成一条公式1 + 1 + 1
        Random random=new Random();
        int numOfFraction=random.nextInt(3)+2;
        int numOfOperator=numOfFraction-1;
        boolean bracketsGenerate=random.nextBoolean();//是否随机生成括号
        //boolean bracketsGenerate=true;
        StringBuilder sb=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        Fraction result=null;
        for(int i=1;i<=numOfFraction;i++){
            if(i!=numOfFraction){
                int denominator=random.nextInt(10)+1;//分母
                int numerator=random.nextInt(denominator*range)+1;//分子
                Fraction f=new Fraction(numerator,denominator);
                sb.append(f.toString()+" ");
                int operator=random.nextInt(4);
                switch (operator) {
                    case 0:
                        sb.append("+ ");
                        //result.add(f);
                        break;
                    case 1:
                        sb.append("- ");
                        //result.sub(f);
                        break;
                    case 2:
                        sb.append("* ");
                        //result.mul(f);
                        break;
                    case 3:
                        sb.append("/ ");
                        //result.div(f);
                        break;
                }
            }else{
                int denominator=random.nextInt(10)+1;//分母
                int numerator=random.nextInt(denominator*range)+1;//分子
                Fraction f=new Fraction(numerator,denominator);
                sb.append(f.toString());
            }

        }
        if(numOfFraction!=2&&bracketsGenerate){//符合生成括号的条件
            String[] s=sb.toString().split(" ");
            int leftindex=2*(random.nextInt(numOfFraction-1));//4个操作时 0 1 2
            int rightindex=0;
            if(numOfFraction==3){
                switch (leftindex){
                    case 0:
                        rightindex=2;
                        break;
                    case 2:
                        rightindex=4;
                        break;
                }
            }else if(numOfFraction==4){
                switch(leftindex){
                    case 0:
                        rightindex=2*(random.nextInt(2)+1);
                        break;
                    case 2:
                        rightindex=2*(random.nextInt(2)+2);
                        break;
                    case 4:
                        rightindex=6;
                        break;
                }
            }
            GetResultFromArray g=new GetResultFromArray();
            if(s[leftindex+1].equals('*')||s[leftindex+1].equals('/')){
                result=g.calculateStringArray(s);
            }else{
                String[] news=addBrackets(s,leftindex,rightindex);
                s=news;
                result=g.calculateStringArray(news);
            }
            for (String str : s) {
                sb2.append(str+" ");
            }
            sb=sb2;
        }else{
            GetResultFromArray g=new GetResultFromArray();
            String[] s=sb.toString().split(" ");
            result=g.calculateStringArray(s);
            for (String str : s) {
                sb2.append(str+" ");
            }
            sb=sb2;
        }
        sb.append("=");
        FileOperation fo=new FileOperation();
        //fo.writeFormulaIntoTxt(sb.toString());
        fo.writeFormulaIntoTxt(sb.toString());
        return result;
    }
    /*private void addBrackets(String[] s, int leftindex, int rightindex) {
        if (leftindex >= 0 && leftindex < s.length && rightindex >= 0 && rightindex < s.length) {
            s[leftindex] = "( " + s[leftindex];
            s[rightindex] = s[rightindex] + ") ";
        } else {
            System.err.println("leftindex 和 rightindex 必须在有效的数组索引范围内。");
        }
    }*/
    private String[] addBrackets(String[] s, int leftindex, int rightindex) {

        String[] news=new String[s.length+2];
        for(int i=0;i<leftindex;i++){
            news[i]=s[i];
        }
        news[leftindex]="(";
        for(int i=leftindex+1;i<rightindex+2;i++){
            news[i]=s[i-1];
        }
        news[rightindex+2]=")";
        for(int i=rightindex+3;i<s.length+2;i++){
            news[i]=s[i-2];
        }
        return news;

    }

    /*public Fraction checkResult(Fraction[] t){
        for(int i=0;i<t.length;i++){
            //for()
        }
    }*/
}
