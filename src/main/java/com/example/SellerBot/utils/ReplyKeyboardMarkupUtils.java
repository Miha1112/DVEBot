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

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("У мене виникли проблеми з технікою"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
      //  keyboard.add(secondRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup getProblemKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Проблеми з впн"));

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Проблеми з ноутбуком"));

        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Проблеми з принтером"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);
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

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Мабуть, подобається"));
        KeyboardRow thirdRow = new KeyboardRow();

        thirdRow.add(new KeyboardButton("Робота мені байдужа"));
        KeyboardRow fourthRow = new KeyboardRow();

        fourthRow.add(new KeyboardButton("Мабуть, не подобається"));
        KeyboardRow fidRow = new KeyboardRow();

        fidRow.add(new KeyboardButton("Дуже не подобається"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);
        keyboard.add(fourthRow);
        keyboard.add(fidRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup getSecondAnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        KeyboardRow fourthRow = new KeyboardRow();
        KeyboardRow fidRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Так"));
        secondRow.add(new KeyboardButton("Ні"));
        thirdRow.add(new KeyboardButton("Не знаю"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);
        keyboard.add(fourthRow);
        keyboard.add(fidRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get3AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        KeyboardRow fourthRow = new KeyboardRow();
        KeyboardRow fidRow = new KeyboardRow();
        KeyboardRow sixRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("0"));
        secondRow.add(new KeyboardButton("1"));
        thirdRow.add(new KeyboardButton("2"));
        fourthRow.add(new KeyboardButton("3"));
        fidRow.add(new KeyboardButton("4"));
        sixRow.add(new KeyboardButton("5"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);
        keyboard.add(fourthRow);
        keyboard.add(fidRow);
        keyboard.add(sixRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get4AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        KeyboardRow fourthRow = new KeyboardRow();
        KeyboardRow fidRow = new KeyboardRow();
        KeyboardRow sixRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Далі"));
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
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Так"));
        secondRow.add(new KeyboardButton("Ні"));
        thirdRow.add(new KeyboardButton("Не знаю"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

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
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Незакінчена середня"));
        firstRow.add(new KeyboardButton("Середня освіта"));
        secondRow.add(new KeyboardButton("Середнє спеціальне"));
        thirdRow.add(new KeyboardButton("Незакінчена вища"));
        thirdRow.add(new KeyboardButton("Вища освіта"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup get18AnswerKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Далі"));
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

        KeyboardRow fourthRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        KeyboardRow sixdRow = new KeyboardRow();
        KeyboardRow seventhdRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("DVE"));
        secondRow.add(new KeyboardButton("Konstar"));
        thirdRow.add(new KeyboardButton("Bagland"));
        fourthRow.add(new KeyboardButton("Have A Rest"));
        sixdRow.add(new KeyboardButton("Home Rest"));
        seventhdRow.add(new KeyboardButton("Born"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);
        keyboard.add(fourthRow);
        keyboard.add(sixdRow);
        keyboard.add(seventhdRow);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
}
