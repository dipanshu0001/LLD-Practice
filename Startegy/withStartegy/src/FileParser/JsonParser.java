package FileParser;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public class JsonParser implements ParserStrategy{
    @Override
    public Object parseFile(File file) throws ParseException {
        try(InputStream inputStream=new FileInputStream(file)) {
            // Read and parse the JSON file into a generic Object
            JSONTokener tokener=new JSONTokener(inputStream);
            return new JSONObject(tokener);
        } catch (IOException e) {
            throw new ParseException("Error parsing the JSON file: " + e.getMessage(),0);
        }catch (org.json.JSONException exception){
            throw new ParseException("Invalid JSon Format: "+ exception.getMessage(),0);
        }
    }

    @Override
    public boolean validateFile(File file) {
        return true;
    }
}
