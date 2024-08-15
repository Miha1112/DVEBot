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
        firstRow.add(new KeyboardButton("Дуже подобається"));
        firstRow.add(new KeyboardButton("Мабуть, подобається"));
        firstRow.add(new KeyboardButton("Робота мені байдужа"));
        firstRow.add(new KeyboardButton("Мабуть, не подобається"));
        firstRow.add(new KeyboardButton("Дуже не подобається"));

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
        firstRow.add(new KeyboardButton("Так"));
        firstRow.add(new KeyboardButton("Ні"));
        firstRow.add(new KeyboardButton("Не знаю"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get3AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("0"));
        firstRow.add(new KeyboardButton("1"));
        firstRow.add(new KeyboardButton("2"));
        firstRow.add(new KeyboardButton("3"));
        firstRow.add(new KeyboardButton("4"));
        firstRow.add(new KeyboardButton("5"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get4AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Пропустити"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get5AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Так"));
        firstRow.add(new KeyboardButton("Ні"));
        firstRow.add(new KeyboardButton("Не знаю"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get6AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Більшість членів нашого колективу – добрі, симпатичні люди"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("У нашому колективі є усілякі люди."));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Більшість членів нашого колективу – люди малоприємні"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get7AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Ні, звичайно"));
        firstRow.add(new KeyboardButton("Скоріше ні, ніж так"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Не знаю, не замислювався над цим"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Скоріше так, ніж ні"));
        thirdRow.add(new KeyboardButton("Так, звичайно"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get8AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("1"));
        firstRow.add(new KeyboardButton("2"));
        firstRow.add(new KeyboardButton("3"));
        firstRow.add(new KeyboardButton("4"));
        firstRow.add(new KeyboardButton("5"));
        firstRow.add(new KeyboardButton("6"));
        firstRow.add(new KeyboardButton("7"));
        firstRow.add(new KeyboardButton("8"));
        firstRow.add(new KeyboardButton("9"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get9AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Так"));
        firstRow.add(new KeyboardButton("Мабуть, так"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Не знаю, чи не замислювався над цим"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Мабуть, ні"));
        thirdRow.add(new KeyboardButton("Ні"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get10AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Ні, не міг би"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Не знаю, не замислювався над цим"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Так, міг би"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get11AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Так, звичайно"));
        firstRow.add(new KeyboardButton("Швидше ні, ніж так"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Не знаю, не замислювався над цим"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Скоріше так, ніж ні"));
        thirdRow.add(new KeyboardButton("Ні, звичайно"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get12AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Повністю задоволений"));
        firstRow.add(new KeyboardButton("Мабуть, задоволений"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Важко сказати"));
        secondRow.add(new KeyboardButton("Мабуть, незадоволений"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Цілком незадоволений"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get13AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("На мою думку, наша робота організована дуже добре"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Загалом, непогано, хоча є можливість покращення"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Важко сказати"));
        KeyboardRow fourthRow = new KeyboardRow();
        fourthRow.add(new KeyboardButton("Робота організована незадовільно, багато часу витрачається марно"));
        fourthRow.add(new KeyboardButton("На мою думку, робота організована дуже погано"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);
        keyboard.add(fourthRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get14AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Безумовно, так"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Загалом, непогано, хоча є можливість покращення"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Мабуть, так"));
        thirdRow.add(new KeyboardButton("Мабуть, ні"));
        thirdRow.add(new KeyboardButton("Безумовно, ні"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get15AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Чоловік"));
        firstRow.add(new KeyboardButton("Жінка"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get16AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Пропустити"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get17AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Незакінчена середня"));
        firstRow.add(new KeyboardButton("Середня освіта"));
        firstRow.add(new KeyboardButton("Середнє спеціальне"));
        firstRow.add(new KeyboardButton("Незакінчена вища"));
        firstRow.add(new KeyboardButton("Вища освіта"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get18AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Пропустити"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get19AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("DVE"));
        firstRow.add(new KeyboardButton("Konstar"));
        firstRow.add(new KeyboardButton("Bagland"));
        firstRow.add(new KeyboardButton("Have A Rest"));
        firstRow.add(new KeyboardButton("Home Rest"));
        firstRow.add(new KeyboardButton("Born"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
}
