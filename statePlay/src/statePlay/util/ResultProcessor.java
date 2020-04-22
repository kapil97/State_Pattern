package statePlay.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultProcessor implements ResultProcessorI {
    String outputFilename;
    public ResultProcessor(){}
    public ResultProcessor(String outputFilenameIn){
        outputFilename=outputFilenameIn;
    }
   static List<String> outputList =new ArrayList<>();
    @Override
    public void addToResultList(String output) {
        outputList.add(output);
    }
    @Override
    public void printList(){
        System.out.println(outputList);
    }

    @Override
    public void writeToFile() {
        try {
            File resultFile=new File(outputFilename);
            if (resultFile.exists()){
                FileWriter fileWriter=new FileWriter(outputFilename,true);
                for (String data : outputList) {
                    fileWriter.write(data);
                    fileWriter.write(System.getProperty("line.separator"));
                }

                fileWriter.close();
            }
            else{
                resultFile.createNewFile();
                System.out.println("File Created:" + resultFile.getName());
                writeToFile();
            }
        }
        catch ( IOException e){
            System.out.println("Error occured");
            e.printStackTrace();
        }
        finally {
            System.out.println("Process Complete");
        }
    }
}
