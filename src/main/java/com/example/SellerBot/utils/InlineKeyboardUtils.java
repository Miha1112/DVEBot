package com.example.SellerBot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

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

public class InlineKeyboardUtils {
    public static InlineKeyboardMarkup getPaymentMethods() {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("\uD83E\uDD5D Киви");
        button1.setCallbackData("balance-up-button1");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("\uD83D\uDFE2 Сбербанк");
        button2.setCallbackData("balance-up-button2");

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("\uD83D\uDD31 Тинькофф");
        button3.setCallbackData("balance-up-button3");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button2);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(button3);

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(rows);

        return keyboardMarkup;
    }

    public static InlineKeyboardMarkup getApproveButtons(String approveData, String abortData){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("✅ Подтвердить");
        button1.setCallbackData(approveData);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("❌ Отменить");
        button2.setCallbackData(abortData);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button2);

        rows.add(row1);
        rows.add(row2);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(rows);

        return keyboardMarkup;
    }

    public static InlineKeyboardMarkup getUpBalanceButton(){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Пополнить");
        button1.setCallbackData("up-balance");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);

        rows.add(row1);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(rows);

        return keyboardMarkup;
    }

    public static InlineKeyboardMarkup getPromoButton(){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Ввести промокод");
        button1.setCallbackData("promo");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);

        rows.add(row1);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(rows);

        return keyboardMarkup;
    }
}
