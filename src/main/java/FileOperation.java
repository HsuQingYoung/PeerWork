import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {
    private  int i=1;
    private  int j=1;
    public void writeFormulaIntoTxt(String[] formula){
        //String filepath="D:\\IdeaProjects\\PeerWork\\src\\main\\resources\\Exercises.txt";
        String filepath="D:\\IdeaProjects\\PeerWork\\src\\main\\resources\\Exercises.txt";
        try {
            FileWriter fw=new FileWriter(filepath,false);
            BufferedWriter bw=new BufferedWriter(fw);
            for(String line:formula){
                bw.write(i+++"、");
                bw.write(line);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAnswerIntoTxt(String[] answer){
        //String filepath="D:\\IdeaProjects\\PeerWork\\src\\main\\resources\\Exercises.txt";
        String filepath="D:\\IdeaProjects\\PeerWork\\src\\main\\resources\\Answer.txt";
        try {
            FileWriter fw=new FileWriter(filepath,false);
            BufferedWriter bw=new BufferedWriter(fw);
            for(String line:answer){
                bw.write(j+++"、");
                bw.write(line);
                bw.newLine();
            }
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
