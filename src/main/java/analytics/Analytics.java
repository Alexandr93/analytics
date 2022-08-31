package analytics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Analytics {
    private List<String> result = new ArrayList<>();
    public  List<String> getAnalytics(String filePath){
        List<Line> lineList = getLineList(filePath);
        for (int i = 0; i < lineList.size(); i++) {
            if(lineList.get(i).getMessageType().equals("D")){
                List<Question> listQuestions = lineList.subList(0, i).stream().filter(line -> line.getMessageType().equals("C")).map(line -> (Question)line).toList();
                int minutesCount =0;
                int containsCount = 0;
                CheckContains checkContains = new CheckContains();
                for(Question q: listQuestions){
                    int buff = checkContains.checkContains((Query) lineList.get(i), q);
                    if(buff>0){
                        minutesCount+=buff;
                        containsCount++;
                    }
                }

                if(containsCount==0){
                    result.add("-");

                }
                if(containsCount>=1)
                {

                    result.add((Integer.toString(minutesCount/containsCount)));


                }


            }



        }
        return result;
    }


    public List<Line> getLineList(String filePath){
        ParseLine parseLine = new ParseLine();
        List<String> lines;
        try{
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Line> lineList = lines.stream().map(parseLine::parse).toList();
        return lineList;
    }
}
