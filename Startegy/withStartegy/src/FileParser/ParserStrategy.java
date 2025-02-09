package FileParser;

import java.io.File;
import java.text.ParseException;

public interface ParserStrategy {

    Object parseFile(File file) throws ParseException;

    boolean validateFile(File file);
}
