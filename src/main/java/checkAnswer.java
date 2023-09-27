import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class checkAnswer {
    public void check() throws IOException {
        String filePath="D:\\IdeaProjects\\PeerWork\\src\\main\\resources\\Exercises.txt";
        String filePath1="D:\\IdeaProjects\\PeerWork\\src\\main\\resources\\Answer.txt";

        String[] useranswer=readLinesToArray(filePath);
        String[] realanswer=readLinesToArray(filePath1);
        String[] result=new String[useranswer.length];
        int i=0;
        for(String line:useranswer){
            String[] ss=line.split(" ");
            String answer=ss[ss.length-1];//用户写的
            int index=realanswer[i].indexOf("、");
            String realAnswer=realanswer[i].substring(index+1);
            if(realAnswer.equals(answer)){
                result[i]="✓";
            }else{
                result[i]="×";
            }
            i++;
        }
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        i=0;
        while ((line = reader.readLine()) != null) {
            lines.add(line + result[i]);
            i++;
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String updatedLine : lines) {
            writer.write(updatedLine);
            writer.newLine();
        }
        writer.close();

    }

    private static String[] readLinesToArray(String filePath) throws IOException {
        ArrayList<String> linesList = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                linesList.add(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return linesList.toArray(new String[0]);
    }
}
