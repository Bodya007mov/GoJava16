package flowers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public abstract class FlowerLoader {

    public static Flower[] load(File file) {
        Flower[] flowers = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            flowers = objectMapper.readValue(file, Flower[].class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return flowers;
    }
}
