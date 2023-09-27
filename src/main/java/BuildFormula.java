import java.util.Random;

/*
* 根据命令行参数生成公式以及结果并写入文件
* */
public class BuildFormula {
    int range;//数值的范围
    int count;//生成公式的次数

    public BuildFormula(int range, int count) {
        this.range = range;
        this.count = count;
    }

    public void generate(){//生成一条公式1 + 1 + 1
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        Fraction result=null;

        String[] formula=new String[count];
        String[] answer=new String[count];
        for(int j=0;j<count;j++){
            int numOfFraction=random.nextInt(3)+2;
            boolean bracketsGenerate=random.nextBoolean();//是否随机生成括号
            sb.setLength(0);
            result=null;
            int tag=0;
            int operator;
            for(int i=1;i<=numOfFraction;i++){//根据分数的个数生成公式和符号
                if(i!=numOfFraction){
                    int denominator=random.nextInt(10)+1;//分母
                    int numerator=random.nextInt(denominator*range)+1;//分子
                    Fraction f=new Fraction(numerator,denominator);
                    sb.append(f.toString()+" ");
                    if(tag==0){
                        operator=random.nextInt(4);
                    }else{
                        operator=random.nextInt(2)+2;
                    }

                    if(operator==0||operator==1){
                        tag=1;
                    }
                    switch (operator) {
                        case 0:
                            sb.append("÷ ");
                            break;
                        case 1:
                            sb.append("- ");
                            break;
                        case 2:
                            sb.append("* ");
                            break;
                        case 3:
                            sb.append("+ ");
                            break;
                    }
                }else{
                    int denominator=random.nextInt(10)+1;//分母
                    int numerator=random.nextInt(denominator*range)+1;//分子
                    Fraction f=new Fraction(numerator,denominator);
                    sb.append(f.toString());
                }

            }
            String[] s=sb.toString().split(" ");
            s=swapOrder(s);

            if(numOfFraction!=2&&bracketsGenerate){//符合生成括号的条件
                //String[] s=sb.toString().split(" ");
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
                String[] news=null;

                news=addBrackets(s,leftindex,rightindex);
                result=g.calculateStringArray(news);

                sb.setLength(0);
                for (String str : news) {
                    sb.append(str+" ");
                }
            }else{
                GetResultFromArray g=new GetResultFromArray();
                //s=sb.toString().split(" ");

                result=g.calculateStringArray(s);
            }
            sb.append("= ");
            formula[j]=sb.toString();
            if(result==null){
                answer[j]="不可计算";
            }else{
                answer[j]=result.toString();
            }

        }
        FileOperation fo=new FileOperation();
        fo.writeFormulaIntoTxt(formula);
        fo.writeAnswerIntoTxt(answer);

    }

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

    public String[] swapOrder(String[] s){
        int index=-1;
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals("-")) {
                index=i;
                break;
            }
        }
        if(index!=-1&&s[index]=="-"){
            String s1=s[index-1];//要满足s1>=s2
            String s2=s[index+1];
            Fraction f1=new Fraction(s1);
            Fraction f2=new Fraction(s2);
            if(f1.numerator*f2.denominator<f1.denominator*f2.numerator){
                String t=s[index-1];
                s[index-1]=s[index+1];
                s[index+1]=t;
            }
        }

        return s;
    }
}
