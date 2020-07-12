package telegrambot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import telegrambot.domain.Menu;
import telegrambot.domain.Question;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static telegrambot.util.Constants.*;

@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final List<Question> randomQuestions = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            if(update.getMessage().getText().equals("/start")) {
                getButtons(LANGUAGES_PATH, CHOOSE_LANGUAGE, update.getMessage().getChatId());
                log.info("Bot started");
            }
        }
        else if(update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            String[] str = data.split("-");
            switch (str[0]) {
                case "language":
                    getTopicsForLanguage(str[1], chatId);
                    break;
                case "topic":
                    getQuestionsForTopic(str[1], str[2], chatId);
                    break;
                case "question":
                    getQuestions(str[1], str[2], str[3], str[4], chatId);
                    break;
            }
            if(data.contains("back")) {
                back(str[1], chatId);
            }
        }
    }

    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void connect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String text, InlineKeyboardMarkup keyboard, Long chatId) {
        try {
            execute(new SendMessage().setChatId(chatId).setText(text).setReplyMarkup(keyboard));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void getButtons(String path, String text, Long chatId) throws IOException {
        List<Menu> buttons = objectMapper.readValue(new File(path), new TypeReference<List<Menu>>() {});
        InlineKeyboardMarkup inlineKeyboardMarkup = createKeyboard(buttons);
        sendMessage(text, inlineKeyboardMarkup, chatId);
    }

    private void getButtonsAndQuestion(String buttonsPath, List<Question> questions, int id, Long chatId) throws IOException {
        List<Menu> buttons = objectMapper.readValue(new File(buttonsPath), new TypeReference<List<Menu>>() {});
        if(id > questions.size()) {
            return;
        }
        Question target = getTargetQuestion(questions, id);
        buttons.forEach(button -> button.setCallbackData(target.getCallbackData() + button.getCallbackData()));
        InlineKeyboardMarkup inlineKeyboardMarkup = createKeyboard(buttons);
        sendMessage(target.getQuestion(), inlineKeyboardMarkup, chatId);
    }

    private void getButtonsAndAnswer(String buttonsPath, List<Question> questions, int id, Long chatId) throws IOException {
        List<Menu> buttons = objectMapper.readValue(new File(buttonsPath), new TypeReference<List<Menu>>() {});
        if(id > questions.size()) {
            return;
        }
        Question target = getTargetQuestion(questions, id);
        buttons.forEach(button -> button.setCallbackData(target.getCallbackData() + button.getCallbackData()));
        InlineKeyboardMarkup inlineKeyboardMarkup = createKeyboard(buttons);
        sendMessage(target.getAnswer(), inlineKeyboardMarkup, chatId);
    }

    private void getButtonsAndQuestionMarkAnswer(String buttonsPath, List<Question> questions, int id, Long chatId) throws IOException {
        List<Menu> buttons = objectMapper.readValue(new File(buttonsPath), new TypeReference<List<Menu>>() {});
        if(id > questions.size()) {
            return;
        }
        Question target = getTargetQuestion(questions, id);
        buttons.forEach(button -> button.setCallbackData(target.getCallbackData() + button.getCallbackData()));
        InlineKeyboardMarkup inlineKeyboardMarkup = createKeyboard(buttons);
        sendMessage(QUESTION_MARK_ANSWER, inlineKeyboardMarkup, chatId);
    }

    private InlineKeyboardMarkup createKeyboard(List<Menu> buttons) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        buttons.forEach(element -> {
            List<InlineKeyboardButton> row = Collections.singletonList(new InlineKeyboardButton().setText(element.getName()).setCallbackData(element.getCallbackData()));
            rowList.add(row);
        });
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private Question getTargetQuestion(List<Question> questions, int id) {
        return questions.stream()
                .filter(question -> question.getId() == id).findFirst()
                .orElseThrow(RuntimeException::new);
    }

    private void setMuscle(String questionsPath, int id) throws IOException {
        List<Question> questions = objectMapper.readValue(new File(questionsPath), new TypeReference<List<Question>>() {});
        Question target = getTargetQuestion(questions, id);
        target.setMuscle(target.getMuscle() + 1);
        objectMapper.writeValue(new File(questionsPath), questions);
    }

    private void setHeart(String questionsPath, int id) throws IOException {
        List<Question> questions = objectMapper.readValue(new File(questionsPath), new TypeReference<List<Question>>() {});
        Question target = getTargetQuestion(questions, id);
        target.setHeart(target.getHeart() + 1);
        objectMapper.writeValue(new File(questionsPath), questions);
    }

    private void getRandomQuestions(String language) throws IOException {
        File[] files = new File("src/main/resources").listFiles((path, name) -> name.contains(language) && name.contains("questions"));
        if (files != null) {
            File file = files[(int)(Math.random() * files.length)];
            randomQuestions.clear();
            randomQuestions.addAll(objectMapper.readValue(file, new TypeReference<List<Question>>() {}));
        }
    }

    private void getTopicsForLanguage(String language, Long chatId) throws IOException {
        String path = String.format(TOPIC_PATH, language);
        getButtons(path, CHOOSE_TOPIC, chatId);
        log.info("Got topics for {} language", language);
    }

    private void getQuestionsForTopic(String language, String topic, Long chatId) throws IOException {
        switch (topic) {
            case "random":
                getRandomQuestions(language);
                getButtonsAndQuestion(RANDOM_QUESTION_BUTTONS_PATH, randomQuestions, (int)(Math.random() * randomQuestions.size() + 1), chatId);
                break;
            case "favorite":
                //
                break;
            case "difficult":
                //
                break;
            default:
                String questionsPath = String.format(QUESTIONS_PATH, language, topic);
                List<Question> questions = objectMapper.readValue(new File(questionsPath), new TypeReference<List<Question>>() {});
                getButtonsAndQuestion(QUESTION_BUTTONS_PATH, questions, 1, chatId);
                break;
        }
        log.info("Got question for {} topic of {} language", topic, language);
    }

    private void getQuestions(String language, String topic, String messageId, String button, Long chatId) throws IOException {
        int id = Integer.parseInt(messageId);
        String questionsPath = String.format(QUESTIONS_PATH, language, topic);
        List<Question> questions = objectMapper.readValue(new File(questionsPath), new TypeReference<List<Question>>() {});
        switch (button) {
            case "heart":
                setHeart(questionsPath, id);
                break;
            case "muscle":
                setMuscle(questionsPath, id);
                break;
            case "answer":
                getButtonsAndAnswer(ANSWER_BUTTONS_PATH, questions, id, chatId);
                log.info("Got answer #{} for {} topic of {} language", id, topic, language);
                break;
            case "next":
                getButtonsAndQuestion(QUESTION_BUTTONS_PATH, questions, id + 1, chatId);
                log.info("Got question #{} for {} topic of {} language", id, topic, language);
                break;
            case "questionmark":
                getButtonsAndQuestionMarkAnswer(QUESTION_BUTTONS_PATH, questions, id, chatId);
                break;
            case "randomanswer":
                getButtonsAndAnswer(RANDOM_ANSWER_BUTTONS_PATH, randomQuestions, id, chatId);
                log.info("Got answer #{} for random topic of {} language", id, language);
                break;
            case "randomnext":
                getRandomQuestions(language);
                getButtonsAndQuestion(RANDOM_QUESTION_BUTTONS_PATH, randomQuestions, (int)(Math.random() * randomQuestions.size() + 1), chatId);
                log.info("Got question #{} for random topic of {} language", id, language);
                break;
            case "randomquestionmark":
                getButtonsAndQuestionMarkAnswer(RANDOM_QUESTION_BUTTONS_PATH, randomQuestions, id, chatId);
                break;
        }
    }

    private void back(String data, Long chatId) throws IOException {
        if (data.equals("languages")) {
            getButtons(LANGUAGES_PATH, CHOOSE_LANGUAGE, chatId);
        } else {
            String path = String.format(TOPIC_PATH, data);
            getButtons(path, CHOOSE_TOPIC, chatId);
        }
        log.info("Came back to {}", data);
    }
}
