package FileParser;

import java.io.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParser implements ParserStrategy{

    @Override
    public Object parseFile(File file) throws ParseException {
        List<Map<String,String>> parsedData=new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(file))) {
            String line;
            String[] header=null;
            if((line=reader.readLine())!=null){
                header=line.split(",");
            }
            while((line=reader.readLine())!=null){
                String[] values=line.split(",");
                Map<String,String> row=new HashMap<>();
                for(int i=0;i<header.length;i++){
                    row.put(header[i],values[i]);
                }
                parsedData.add(row);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parsedData;
    }

    @Override
    public boolean validateFile(File file) {
        if(!file.exists() || !file.isFile()){
            return false;
        }
        if(!file.getName().endsWith(".csv")){
            return false;
        }
        try{
            if(Files.size(file.toPath())==0){
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
