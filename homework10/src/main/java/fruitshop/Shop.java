package fruitshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Shop {

    private List<Fruit> storage;

    public List<Fruit> getStorage() {
        return storage;
    }

    public void addFruits(String path) {
        if (storage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<Fruit> supply= objectMapper.readValue(new File(path), new TypeReference<List<Fruit>>(){});
                storage.addAll(supply);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            load(path);
        }
    }

    public void save(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(path), storage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String path) {
        if(storage != null) {
            storage.clear();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            storage = objectMapper.readValue(new File(path), new TypeReference<List<Fruit>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
