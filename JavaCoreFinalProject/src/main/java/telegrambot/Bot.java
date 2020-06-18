package telegrambot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private static final String BOT_USER_NAME = "BodyaMovBot";
    private static final String BOT_TOKEN = "1136048423:AAEE9KZyjScSpj8Mj8C0NSbgUfGSDH7PrXQ";

    public void onUpdateReceived(Update update) {
        try {
            execute(sendMessage(update.getMessage().getChatId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }

    public SendMessage sendMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> rowJava = new ArrayList<>();
        List<InlineKeyboardButton> rowHtmlCss = new ArrayList<>();
        List<InlineKeyboardButton> rowReactJs = new ArrayList<>();
        List<InlineKeyboardButton> rowNodeJs = new ArrayList<>();
        List<InlineKeyboardButton> rowStop = new ArrayList<>();

        rowJava.add(new InlineKeyboardButton().setText("Java").setCallbackData("Java!!!"));
        rowHtmlCss.add(new InlineKeyboardButton().setText("HTML+CSS").setCallbackData("HTML+CSS!!!"));
        rowReactJs.add(new InlineKeyboardButton().setText("ReactJS").setCallbackData("ReactJS!!!"));
        rowNodeJs.add(new InlineKeyboardButton().setText("NodeJS").setCallbackData("NodeJS!!!"));
        rowStop.add(new InlineKeyboardButton().setText("STOP").setCallbackData("STOP!!!"));

        rowList.add(rowJava);
        rowList.add(rowHtmlCss);
        rowList.add(rowReactJs);
        rowList.add(rowNodeJs);
        rowList.add(rowStop);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return new SendMessage().setChatId(chatId).setText("Выбери раздел для просмотра:").setReplyMarkup(inlineKeyboardMarkup);

    }
}
