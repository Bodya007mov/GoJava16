package flowers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = Rose.class),
        @JsonSubTypes.Type(value = Chamomile.class),
        @JsonSubTypes.Type(value = Tulip.class)
})

public abstract class Flower {

    public abstract int getPrice();

}
