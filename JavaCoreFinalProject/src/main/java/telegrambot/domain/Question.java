package telegrambot.domain;

import lombok.Data;

@Data
public class Question {

    private int id;
    private int heart;
    private int muscle;
    private String question;
    private String answer;
    private String callbackData;
}
