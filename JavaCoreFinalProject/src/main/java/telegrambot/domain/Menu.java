package telegrambot.domain;

import lombok.Data;

@Data
public class Menu {

    private int id;
    private String name;
    private String callbackData;
}
