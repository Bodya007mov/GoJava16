package fruitshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Fruit> getSpoiledFruits(Date date) {
        List<Fruit> list = new ArrayList<>();
        for (Fruit fruit : storage) {
            Date iteratorDate = fruit.getDate();
            long iteratorShelfLife = fruit.getShelfLife() * 86400000; //24h * 60min * 60sec * 1000millis
            if (iteratorShelfLife <= date.getTime() - iteratorDate.getTime()) {
                list.add(fruit);
            }
        }
        return list;
    }

    public List<Fruit> getAvailableFruits(Date date) {
        List<Fruit> list = new ArrayList<>();
        for (Fruit fruit : storage) {
            Date iteratorDate = fruit.getDate();
            long iteratorShelfLife = fruit.getShelfLife() * 86400000; //24h * 60min * 60sec * 1000millis
            if (iteratorShelfLife > date.getTime() - iteratorDate.getTime()) {
                list.add(fruit);
            }
        }
        return list;
    }

    public List<Fruit> getSpoiledFruits(Date date, FruitType type) {
        List<Fruit> list = new ArrayList<>();
        for (Fruit fruit : storage) {
            Date iteratorDate = fruit.getDate();
            long iteratorShelfLife = fruit.getShelfLife() * 86400000; //24h * 60min * 60sec * 1000millis
            if ((iteratorShelfLife <= date.getTime() - iteratorDate.getTime()) && fruit.getType() == type) {
                list.add(fruit);
            }
        }
        return list;
    }

    public List<Fruit> getAvailableFruits(Date date, FruitType type) {
        List<Fruit> list = new ArrayList<>();
        for (Fruit fruit : storage) {
            Date iteratorDate = fruit.getDate();
            long iteratorShelfLife = fruit.getShelfLife() * 86400000; //24h * 60min * 60sec * 1000millis
            if ((iteratorShelfLife > date.getTime() - iteratorDate.getTime()) && fruit.getType() == type) {
                list.add(fruit);
            }
        }
        return list;
    }

    public List<Fruit> getAddedFruits(Date date) {
        List<Fruit> list = new ArrayList<>();
        for (Fruit fruit : storage) {
            Date iteratorDate = fruit.getDate();
            if (iteratorDate.compareTo(date) == 0) {
                list.add(fruit);
            }
        }
        return list;
    }

    public List<Fruit> getAddedFruits(Date date, FruitType type) {
        List<Fruit> list = new ArrayList<>();
        for (Fruit fruit : storage) {
            Date iteratorDate = fruit.getDate();
            if (iteratorDate.compareTo(date) == 0 && fruit.getType() == type) {
                list.add(fruit);
            }
        }
        return list;
    }
}
