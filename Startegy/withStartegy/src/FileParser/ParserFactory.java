package FileParser;

import java.io.File;

public class ParserFactory {

    public ParserStrategy getStartegy(File file){
        String fileName=file.getName();
        if(fileName.endsWith(".csv")){
            return new CsvParser();
        }else if(fileName.endsWith(".json")){
            return new JsonParser();
        }
        return null;
    }
}
