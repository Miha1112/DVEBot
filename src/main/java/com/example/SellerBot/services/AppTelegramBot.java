package com.example.SellerBot.services;

import com.example.SellerBot.config.BotConfig;
import com.example.SellerBot.enums.UserStatus;
import com.example.SellerBot.models.User;
import com.example.SellerBot.utils.InlineKeyboardUtils;
import com.example.SellerBot.utils.ReplyKeyboardMarkupUtils;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.List;
import java.util.Random;


/**
 * Created by Sh1chiro on 27.07.2024.
 * <p>
 * When I wrote this code, only god and
 * I knew how it worked.
 * Now, only god knows it!
 *
 * @author Sh1chiro
 */

@Slf4j
@Component
public class AppTelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final UserService userService;
    private final ConfigService configService;
    private final Random random = new Random();
    private ReplyKeyboardMarkup keyboardMarkup;

    public AppTelegramBot(BotConfig config, UserService userService, ConfigService configService) {
        this.config = config;
        this.userService = userService;
        this.configService = configService;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        Long chatId;

        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            User user = userService.getByTelegramId(update.getMessage().getFrom().getId());
            log.info("Message received: {}", update.getMessage().getText());
            checkText(update, chatId, user);
        } else if (update.hasCallbackQuery()) {

        }
    }

    private void checkText(Update update, Long chatId, User user) {
        String text = update.getMessage().getText();
        if (user == null) {
            if (text.equals("/start"))
                registration(update);
        } else if (text.equals("Головне меню")) {
            sendKeyboardToChat(chatId, "Для продолжения выбери нужную команду на клавиатуре \uD83D\uDC47", ReplyKeyboardMarkupUtils.getMainKeyboard());
        } else if (text.equals("Розпочати тестування")) {
            sendKeyboardToChat(chatId, "Test is started", ReplyKeyboardMarkupUtils.getFirstAnswerKeyboard());
        } else if (text.equals("Answer1") || text.equals("Answer2") || text.equals("Answer3")) {
            user.setFirstAnswer(text);
            sendKeyboardToChat(chatId, "Next questions is: choose button again))))", ReplyKeyboardMarkupUtils.getSecondAnswerKeyboard());
        } else if (text.equals("Answer4")) {
            user.setSecondAnswer(text);
            sendKeyboardToChat(chatId, "Button 3 is clicked", ReplyKeyboardMarkupUtils.getBackToMainKeyboard());
        }else if (text.contains("send message to chat:")){
            String withoutPrefix = text.substring("send message to chat:".length()).trim();
            int spaceIndex = withoutPrefix.indexOf(' ');
            if (spaceIndex != -1) {
                String chatIdStr = withoutPrefix.substring(0, spaceIndex);
                String message = withoutPrefix.substring(spaceIndex + 1);

                try {
                    long chatIdToSend = Long.parseLong(chatIdStr);
                    sendMessageToChat(message,chatIdToSend);
                } catch (NumberFormatException e) {
                    log.info("Invalid chat ID format.");
                }
            } else {
                log.info("No message found.");
            }
        }
        userService.save(user);
    }


    private void registration(Update update) {
        User user = new User();
        user.setTelegramId(update.getMessage().getChatId());

        org.telegram.telegrambots.meta.api.objects.User telegramUser = update.getMessage().getFrom();
        String nickname = telegramUser.getUserName();
        if (nickname != null)
            user.setNickname(nickname);

        String name = telegramUser.getFirstName();
        if (telegramUser.getLastName() != null)
            name = name.concat(" " + telegramUser.getLastName());

        if (name != null)
            user.setName(name);

        userService.save(user);

        User registeredUser = userService.getByTelegramId(user.getTelegramId());
        if (registeredUser != null)
            sendKeyboardToChat(registeredUser.getTelegramId(), "Click on the button \uD83D\uDC47", ReplyKeyboardMarkupUtils.getMainKeyboard());

    }

    private void sendMessageToChat(String text, Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendKeyboardToChat(Long chatId, String text, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(text);
        response.setReplyMarkup(keyboardMarkup);

        try {
            execute(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendInlineMessage(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        try {
            if (chatId > 0)
                execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendInlinePhoto(String uniqueFileId, Long chatId, String caption, Long userId, double sum) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(uniqueFileId));
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(caption);
        sendPhoto.setReplyMarkup(InlineKeyboardUtils.getApproveButtons("approve-up-" + userId + "-" + sum, "abort-up-" + userId));

        try {
            execute(sendPhoto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendInlinePhotoForDownGold(String uniqueFileId, Long chatId, String caption, Long userId, double sum) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(uniqueFileId));
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(caption);
        sendPhoto.setReplyMarkup(InlineKeyboardUtils.getApproveButtons("approve-down-" + userId + "-" + sum, "abort-down-" + userId));

        try {
            execute(sendPhoto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteMessage(Long chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);

        try {
            execute(deleteMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendPhotoToChat(Long chatId, String path, String caption) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new File(path)));
        sendPhoto.setCaption(caption);

        try {
            execute(sendPhoto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendTgPhotoToChat(Long chatId, String uniqueFileId, String caption, List<MessageEntity> entities) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(uniqueFileId));
        sendPhoto.setCaption(caption);

        if (entities != null) {
            sendPhoto.setCaptionEntities(entities);
        }

        try {
            execute(sendPhoto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}