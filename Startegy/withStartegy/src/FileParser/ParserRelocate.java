package FileParser;

import java.io.File;
import java.text.ParseException;
import java.util.Optional;

public class ParserRelocate {
    private ParserStrategy parserStrategy;

    public void setParserStrategy(ParserStrategy parserStrategy){
        this.parserStrategy=parserStrategy;
    }
    private boolean validateFile(File file){
        return parserStrategy.validateFile(file);
    }
    public Optional<Object> parseFile(File file) throws ParseException {
        if(parserStrategy==null) {
            throw new RuntimeException("Startegy not selected please select and pass");
        }
        if(validateFile(file)){
            return Optional.of(parserStrategy.parseFile(file));
        }
        return Optional.empty();
    }
}
