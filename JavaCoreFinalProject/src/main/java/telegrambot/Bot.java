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
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        buttons.forEach(element -> {
            List<InlineKeyboardButton> row = Collections.singletonList(new InlineKeyboardButton().setText(element.getName()).setCallbackData(element.getCallbackData()));
            rowList.add(row);
        });
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage(text, inlineKeyboardMarkup, chatId);
    }

    private void getButtons(String buttonsPath, String questionsPath, int id, boolean isQuestion, Long chatId) throws IOException {
        List<Menu> buttons = objectMapper.readValue(new File(buttonsPath), new TypeReference<List<Menu>>() {});
        List<Question> questions = objectMapper.readValue(new File(questionsPath), new TypeReference<List<Question>>() {});
        if(id > questions.size()) {
            return;
        }
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        buttons.forEach(element -> {
            List<InlineKeyboardButton> row = Collections.singletonList(new InlineKeyboardButton().setText(element.getName()).setCallbackData(questions.get(id - 1).getCallbackData() + element.getCallbackData()));
            rowList.add(row);
        });
        inlineKeyboardMarkup.setKeyboard(rowList);
        questions.forEach(element -> {
            if(element.getId() == id) {
                if(isQuestion) {
                    sendMessage(element.getQuestion(), inlineKeyboardMarkup, chatId);
                } else {
                    sendMessage(element.getAnswer(), inlineKeyboardMarkup, chatId);
                }
            }
        });
    }

    private void getTopicsForLanguage(String language, Long chatId) throws IOException {
        String path = String.format(TOPIC_PATH, language);
        getButtons(path, CHOOSE_TOPIC, chatId);
        log.info("Got topics for {} language", language);
    }

    private void getQuestionsForTopic(String language, String topic, Long chatId) throws IOException {
        switch (topic) {
            case "random":
                //
                break;
            case "favorite":
                //
                break;
            case "difficult":
                //
                break;
            default:
                String buttonsPath = String.format("src/main/resources/%s-question-buttons.json", language);
                String questionsPath = String.format("src/main/resources/%s-%s-questions.json", language, topic);
                getButtons(buttonsPath, questionsPath, 1,true, chatId);
                break;
        }
        log.info("Got question for {} topic of {} language", topic, language);
    }

    private void getQuestions(String language, String topic, String messageId, String button, Long chatId) throws IOException {
        int id = Integer.parseInt(messageId);
        String buttonsPath = String.format("src/main/resources/%s-question-buttons.json", language);
        switch (button) {
            case "heart":
                //
                break;
            case "muscle":
                //
                break;
            case "answer":
                String answerPath = String.format("src/main/resources/%s-%s-questions.json", language, topic);
                getButtons(buttonsPath, answerPath, id, false, chatId);
                log.info("Got answer #{} for {} topic of {} language", id, topic, language);
                break;
            case "next":
                String questionsPath = String.format("src/main/resources/%s-%s-questions.json", language, topic);
                getButtons(buttonsPath, questionsPath, id + 1, true, chatId);
                log.info("Got question #{} for {} topic of {} language", id, topic, language);
                break;
            case "questionmark":
                //
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
