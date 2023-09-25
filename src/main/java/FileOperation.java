import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {
    private static int i=1;
    private static int j=1;
    public void writeFormulaIntoTxt(String fraction){
        //String filepath="D:\\IdeaProjects\\PeerWork\\src\\main\\resources\\Exercises.txt";
        String filepath=".\\src\\main\\resources\\Exercises.txt";
        try {
            FileWriter fw=new FileWriter(filepath,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(i+++"、");
            bw.write(fraction);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAnswerIntoTxt(String answer){
        //String filepath="D:\\IdeaProjects\\PeerWork\\src\\main\\resources\\Exercises.txt";
        String filepath=".\\src\\main\\resources\\Answer.txt";
        try {
            FileWriter fw=new FileWriter(filepath,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(j+++"、");
            bw.write(answer);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
