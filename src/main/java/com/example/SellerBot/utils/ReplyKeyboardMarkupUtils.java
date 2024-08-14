package com.example.SellerBot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sh1chiro on 27.07.2024.
 * <p>
 * When I wrote this code, only god and
 * I knew how it worked.
 * Now, only god knows it!
 *
 * @author Sh1chiro
 */

public class ReplyKeyboardMarkupUtils {
    public static ReplyKeyboardMarkup getBackToMainKeyboard(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Головне меню"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup getMainKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Розпочати тестування"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup getFirstAnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Answer1"));
        firstRow.add(new KeyboardButton("Answer2"));
        firstRow.add(new KeyboardButton("Answer3"));
        firstRow.add(new KeyboardButton("Головне меню"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup getSecondAnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Answer1"));
        firstRow.add(new KeyboardButton("Answer2"));
        firstRow.add(new KeyboardButton("Answer3"));
        firstRow.add(new KeyboardButton("Answer4"));
        firstRow.add(new KeyboardButton("Головне меню"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
}
