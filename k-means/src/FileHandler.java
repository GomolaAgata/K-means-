import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    public static List<Iris> getData(String filePath) throws IOException {
        List<Iris> irises = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while(( line= reader.readLine())!=null){
           String[] strAttributes= line.split(",");
           double[] attributes = Arrays.stream(strAttributes, 0, strAttributes.length-1)
                   .mapToDouble(Double::parseDouble).toArray();
           String answer = strAttributes[strAttributes.length-1];
           Iris iris = new Iris(attributes);
           iris.setAnswer(answer);
           irises.add(iris);
        }
        return irises;
    }
}
