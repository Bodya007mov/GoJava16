package telegrambot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import telegrambot.domain.Menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static telegrambot.util.Constants.*;

public class Bot extends TelegramLongPollingBot {

    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            if(update.getMessage().getText().equals("/start")) {
                sendMessage(LANGUAGES_PATH, CHOOSE_LANGUAGE, update.getMessage().getChatId());
            }
        }
        else if(update.hasCallbackQuery()) {
            String[] str = update.getCallbackQuery().getData().split("-");
            switch (str[0]) {
                case "language":
                    getTopicsForLanguage(str[1], update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "topic":
                    getQuestionsForTopic(str[1], str[2], update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "question":
                    getMessage(str[1], str[2], str[3], str[4], update.getCallbackQuery().getMessage().getChatId());
                    break;
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

    private void sendMessage(String path, String text, Long chatId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Menu> buttons = objectMapper.readValue(new File(path), new TypeReference<List<Menu>>() {});
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        buttons.forEach(element -> {
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(new InlineKeyboardButton().setText(element.getName()).setCallbackData(element.getCallbackData()));
            rowList.add(row);
        });
        inlineKeyboardMarkup.setKeyboard(rowList);
        try {
            execute(new SendMessage().setChatId(chatId).setText(text).setReplyMarkup(inlineKeyboardMarkup));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String buttonsPath, String questionsPath, int id, Long chatId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Menu> buttons = objectMapper.readValue(new File(buttonsPath), new TypeReference<List<Menu>>() {});
        List<Menu> questions = objectMapper.readValue(new File(questionsPath), new TypeReference<List<Menu>>() {});
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        buttons.forEach(element -> {
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(new InlineKeyboardButton().setText(element.getName()).setCallbackData(questions.get(id - 1).getCallbackData() + element.getCallbackData()));
            rowList.add(row);
        });
        inlineKeyboardMarkup.setKeyboard(rowList);
        questions.forEach(element -> {
            if(element.getId() == id) {
                try {
                    execute(new SendMessage().setChatId(chatId).setText(element.getName()).setReplyMarkup(inlineKeyboardMarkup));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getTopicsForLanguage(String language, Long chatId) throws IOException {
        String path = String.format("src/main/resources/%s-topics.json", language);
        sendMessage(path, CHOOSE_TOPIC, chatId);
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
            case "back":
                sendMessage(LANGUAGES_PATH, CHOOSE_LANGUAGE, chatId);
                break;
            default:
                String buttonsPath = String.format("src/main/resources/%s-question-buttons.json", language);
                String questionsPath = String.format("src/main/resources/%s-%s-questions.json", language, topic);
                sendMessage(buttonsPath, questionsPath, 1, chatId);
                break;
        }
    }

    private void getMessage(String language, String topic, String messageId, String button, Long chatId) throws IOException {
        int id = Integer.parseInt(messageId);
        switch (button) {
            case "heart":
                //
                break;
            case "muscle":
                //
                break;
            case "answer":
                String buttonsPath = String.format("src/main/resources/%s-question-buttons.json", language);
                String answerPath = String.format("src/main/resources/%s-%s-answers.json", language, topic);
                sendMessage(buttonsPath, answerPath, id, chatId);
                break;
            case "next":
                //
                break;
            case "back":
                sendMessage(TOPIC_PATH, CHOOSE_TOPIC, chatId);
                break;
            case "questionmark":
                //
                break;
        }
    }
}
