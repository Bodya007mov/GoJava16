package flowers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public abstract class FlowersSaver {

    public static void save(File file, Flower[] flowers) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, flowers);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
