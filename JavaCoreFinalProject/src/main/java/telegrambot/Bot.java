package telegrambot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.BotSession;
import telegrambot.domain.Menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static telegrambot.util.Constants.*;

public class Bot extends TelegramLongPollingBot {

    public static BotSession botSession;
    List<Menu> languagesButtonsList, topicButtonsList, otherLanguagesButtonsList, questionButtonsList, structureQuestionsList,
            primitivesQuestionsList, OOPQuestionsList, classesAndMethodsQuestionsList, collectionsQuestionsList, multiThreadingQuestionsList;
    private int questionCounter;

    public Bot() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.languagesButtonsList = objectMapper.readValue(new File(LANGUAGES_PATH), new TypeReference<List<Menu>>(){});
            this.topicButtonsList = objectMapper.readValue(new File(TOPIC_PATH), new TypeReference<List<Menu>>(){});
            this.otherLanguagesButtonsList = objectMapper.readValue(new File(OTHER_LANGUAGES_PATH), new TypeReference<List<Menu>>(){});
            this.questionButtonsList = objectMapper.readValue(new File(QUESTION_BUTTONS_PATH), new TypeReference<List<Menu>>(){});
            this.structureQuestionsList = objectMapper.readValue(new File(STRUCTURE_QUESTIONS_PATH), new TypeReference<List<Menu>>(){});
            this.primitivesQuestionsList = objectMapper.readValue(new File(PRIMITIVES_QUESTIONS_PATH), new TypeReference<List<Menu>>(){});
            this.OOPQuestionsList = objectMapper.readValue(new File(OOP_QUESTIONS_PATH), new TypeReference<List<Menu>>(){});
            this.classesAndMethodsQuestionsList = objectMapper.readValue(new File(CLASSES_AND_METHODS_QUESTIONS_PATH), new TypeReference<List<Menu>>(){});
            this.collectionsQuestionsList = objectMapper.readValue(new File(COLLECTIONS_QUESTIONS_PATH), new TypeReference<List<Menu>>(){});
            this.multiThreadingQuestionsList = objectMapper.readValue(new File(MULTITHREADING_QUESTIONS_PATH), new TypeReference<List<Menu>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            if(update.getMessage().getText().equals("/start")) {
                sendMessage(languagesButtonsList, CHOOSE_LANGUAGE, update.getMessage().getChatId());
            }
        } else if(update.hasCallbackQuery()) {
            switch (update.getCallbackQuery().getData()) {
                case "Java":
                    sendMessage(topicButtonsList, CHOOSE_TOPIC, update.getCallbackQuery().getMessage().getChatId());
                    System.out.println(update.getCallbackQuery().getId());
                    break;
                case "STOP":
                    /*
                    botSession.stop();
                    botSession.start();
                    */

                    /*
                    botSession.stop();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    botSession.start();
                    */
                    botSession.stop();
                    System.out.println(botSession.isRunning());
                    for (int i = 0; i < 10000; i++) {
                        i = i + 1;
                        i = i - 1;
                    }
                    botSession.start();
                    System.out.println(botSession.isRunning());
                    break;
                case "HTML+CSS":
                case "ReactJS":
                case "NodeJS":
                case "Python":
                    sendMessage(otherLanguagesButtonsList, CHOOSE_ACTION, update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "Устройство Java":
                    sendQuestion(questionButtonsList, structureQuestionsList, update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "Примитивы":
                    sendQuestion(questionButtonsList, primitivesQuestionsList, update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "ООП":
                    sendQuestion(questionButtonsList, OOPQuestionsList, update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "Классы и методы":
                    sendQuestion(questionButtonsList, classesAndMethodsQuestionsList, update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "Коллекции":
                    sendQuestion(questionButtonsList, collectionsQuestionsList, update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "Многопоточность":
                    sendQuestion(questionButtonsList, multiThreadingQuestionsList, update.getCallbackQuery().getMessage().getChatId());
                    break;
                case "?":
                    sendMessage(questionButtonsList, QUESTION_MARK_ANSWER, update.getCallbackQuery().getMessage().getChatId());
                    break;
            }
            if((update.getCallbackQuery().getMessage().getText().equals(CHOOSE_TOPIC) || update.getCallbackQuery().getMessage().getText().equals(CHOOSE_ACTION))
                    && update.getCallbackQuery().getData().equals("Назад")) {
                sendMessage(languagesButtonsList, CHOOSE_LANGUAGE, update.getCallbackQuery().getMessage().getChatId());
            } else if (update.getCallbackQuery().getData().equals("Назад")) {
                sendMessage(topicButtonsList, CHOOSE_TOPIC, update.getCallbackQuery().getMessage().getChatId());
            } else if(update.getCallbackQuery().getMessage().getText().equals(structureQuestionsList.get(questionCounter).getName()) && update.getCallbackQuery().getData().equals(questionButtonsList.get(4).getCallbackData())) {
                sendMessage(topicButtonsList, CHOOSE_TOPIC, update.getCallbackQuery().getMessage().getChatId());
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
            botSession = telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(List<Menu> buttons, String text, Long chatId) {
        try {
            execute(new SendMessage().setChatId(chatId).setText(text).setReplyMarkup(initializeKeyboard(buttons)));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendQuestion(List<Menu> buttons, List<Menu> text, Long chatId) {
        try {
            execute(new SendMessage().setChatId(chatId).setText(text.get(questionCounter).getName()).setReplyMarkup(initializeKeyboard(buttons)));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardMarkup initializeKeyboard(List<Menu> buttons) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        buttons.forEach(element -> {
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(new InlineKeyboardButton().setText(element.getName()).setCallbackData(element.getCallbackData()));
            rowList.add(row);
        });
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
