package telegrambot.domain;

import lombok.Data;

@Data
public class Question {

    private int id;
    private String question;
    private String answer;
    private String callbackData;
}
