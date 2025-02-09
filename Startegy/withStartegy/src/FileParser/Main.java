package FileParser;

import javax.swing.text.html.Option;
import java.io.File;
import java.text.ParseException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws ParseException {
        String dataPath="D:/LLD-Code_level/Startegy/withStartegy/src/FileParser/demo.csv";
        String jsonPath="D:/LLD-Code_level/Startegy/withStartegy/src/FileParser/demo.json";
        File file =new File(dataPath);

        ParserFactory parserFactory=new ParserFactory();
        ParserRelocate parserRelocate =new ParserRelocate();
        ParserStrategy strategy= parserFactory.getStartegy(file);
        parserRelocate.setParserStrategy(strategy);
        Optional<Object> data=parserRelocate.parseFile(file);
//        System.out.println(data.isEmpty()?"":data.get());

        //parsing json now changing object selection at runtime
        file=new File(jsonPath);
        ParserStrategy strategy1=parserFactory.getStartegy(file);
        parserRelocate.setParserStrategy(strategy1);
        Optional<Object> jsonDAta=parserRelocate.parseFile(file);
        System.out.println(jsonDAta.isEmpty()?"":jsonDAta.get());

    }

}
