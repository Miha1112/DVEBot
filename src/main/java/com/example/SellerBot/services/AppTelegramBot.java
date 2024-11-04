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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Slf4j
@Component
public class AppTelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final UserService userService;
    private final ConfigService configService;
    private final Random random = new Random();
    private ReplyKeyboardMarkup keyboardMarkup;
    private int questionsStage = 0;
    private String wrongChoose = "Ви обрали неприпустиму відповідь, будь ласка оберіть із доступних на клавіатурі";
    private final List<String> firstQueuList = new ArrayList<>();
    private final List<String> secondQueuList = new ArrayList<>();;
    private final List<String> thirdQueuList = new ArrayList<>();;

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
            if(update.getMessage().hasText()){
                if(user != null)
                    checkText(update, chatId, user);
                if(user == null && update.getMessage().getText().equals("/start"))
                    registration(update);
            }
        } else if (update.hasCallbackQuery()) {
            log.info("second if detected");
        }
    }

    private void checkText(Update update, Long chatId, User user) {
        String text = update.getMessage().getText();
        if (text.equals("Головне меню")) {
            sendKeyboardToChat(chatId, "Для продовження обери дію з меню \uD83D\uDC47", ReplyKeyboardMarkupUtils.getMainKeyboard());
        } else if (text.equals("Розпочати тестування")) {
            sendKeyboardToChat(chatId, "Тест почався, я запам'ятаю на якому питанні ми зупинились, якщо тобі потрібно буде взяти паузу," +
                    " також приймаються будь які текстові варіанти відповіді на питання окрім запропонованих мною", ReplyKeyboardMarkupUtils.getFirstAnswerKeyboard());
            selectQuestions(user,chatId,update);
        } else if (text.contains("send message to chat:")){
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
        }else {
            selectQuestions(user,chatId,update);
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
            sendKeyboardToChat(registeredUser.getTelegramId(), "Бот працює в тестовому режимі \uD83D\uDE42 \nЯкщо під час проходження тесту виникла помилка, прохання звернутись до адміністратора - @Mikhail_Halahan", ReplyKeyboardMarkupUtils.getMainKeyboard());
        if(user.getName().equals("Misha Galagan")){
            sendMessageToChat("Вітаю мій любий розробник, що на цей раз будемо тестувати?",registeredUser.getTelegramId());
        }

    }

    public void checkMessageBeforeSend(String text, long chatId) {
        if (!text.isEmpty()){
            sendMessageToChat(text,chatId);
        }
    }

    private void iHaveProblem(User user, Update update) {



    }

    private void sendMessageToChat(String text, Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        if (text.equals("Ваш вік")||text.equals("Хто із членів колективу користується найбільшою повагою у колег? Напишіть два-три прізвища.")||text.equals("Прізвище вашого безпосереднього керівника:")||text.equals("Стаж роботи в компанії")) {
            ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
            replyKeyboardRemove.setRemoveKeyboard(true);
            message.setReplyMarkup(replyKeyboardRemove);
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("Message successful send to user: " + chatId + ", with text: " + text);
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
    private void selectQuestions(User user, Long chatId, Update update) {
        int questionsStage = user.getQuestionsStage();
        switch (questionsStage){
            case 0:
                sendKeyboardToChat(chatId, "Чи подобається Вам ваша робота?", ReplyKeyboardMarkupUtils.getFirstAnswerKeyboard());
                user.setQuestionsStage(questionsStage+1);
                break;
            case 1:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer1(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Чи хотіли б Ви перейти на іншу роботу?", ReplyKeyboardMarkupUtils.getSecondAnswerKeyboard());
                    user.setQuestionsStage(questionsStage + 1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 2:
                int count = user.getFirstQuestionsQueue();
                switch (count){
                    case 0:
                        if (chekAnswer(update,questionsStage-1)) {
                            user.setSecondAnswer2(update.getMessage().getText());
                            sendKeyboardToChat(chatId, "Оцініть, будь ласка, за п'ятибальною шкалою ступінь розвитку наведених нижче якостей у вашого безпосереднього керівника: 5 – якість розвинена дуже сильно; 1 – якість не розвинена.",
                                    ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            sendKeyboardToChat(chatId, "Працьовитість:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 1:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0, "Працьовитість: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Громадська активність:", ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 2:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Громадська активність: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Професійні знання:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 3:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Професійні знання: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Турбота про людей:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 4:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Турбота про людей: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Вибагливість:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 5:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Вибагливість: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Чуйність:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 6:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Чуйність: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Комунікабельність:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 7:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Комунікабельність: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Здатність розумітися на людях:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 8:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Здатність розумітися на людях: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Справедливість:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 9:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Справедливість: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Доброзичливість:",ReplyKeyboardMarkupUtils.get3AnswerKeyboard());
                            user.setFirstQuestionsQueue(count+1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 10:
                        if (chekAnswer(update,questionsStage)) {
                            firstQueuList.add(0,"Доброзичливість: " + update.getMessage().getText() + "\n");
                            String ss = "";
                            for (String string : firstQueuList) {
                                ss += "\n" + string + "\n";
                            }
                            user.setSecondAnswer3(ss);
                            user.setQuestionsStage(questionsStage+1);
                            selectQuestions(user,chatId,update);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                }
                break;
            case 3:
                sendMessageToChat("Хто із членів колективу користується найбільшою повагою у колег? Напишіть два-три прізвища."
                        ,chatId);
                user.setQuestionsStage(questionsStage+1);
                break;
            case 4:
                    user.setSecondAnswer4(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Припустимо, що з якихось причин Ви тимчасово не працюєте: чи повернулися Ви на своє нинішнє місце роботи?",
                            ReplyKeyboardMarkupUtils.get5AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                break;
            case 5:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer5(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Виділіть, будь ласка, з яким із наведених тверджень Ви найбільше згодні",
                            ReplyKeyboardMarkupUtils.get6AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 6:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer6(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Чи вважаєте Ви, що було б добре, якби члени вашого колективу жили близько один від одного?",
                            ReplyKeyboardMarkupUtils.get7AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 7:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer7(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Зверніть увагу на наведену нижче шкалу. " +
                                    "Цифра 1 характеризує колектив, який вам дуже подобається, " +
                                    "а 9 – колектив, який вам дуже не подобається. У яку стрічку Ви помістили свій колектив?",
                            ReplyKeyboardMarkupUtils.get8AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 8:
                int count2 = user.getSecondQuestionsQueue();
                switch (count2) {
                    case 0:
                        if (chekAnswer(update,questionsStage-1)) {
                            user.setSecondAnswer8(update.getMessage().getText());
                            sendKeyboardToChat(chatId, "Як Вам здається, могли б Ви дати досить повну характеристику ділових " +
                                    "та особистих якостей більшості членів колективу", ReplyKeyboardMarkupUtils.get9AnswerKeyboard());
                            sendKeyboardToChat(chatId, "Ділові якості більшості членів колективу: ",
                                    ReplyKeyboardMarkupUtils.get9AnswerKeyboard());
                            user.setSecondQuestionsQueue(count2 + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                        case 1:
                            if (chekAnswer(update,questionsStage)) {
                                    secondQueuList.add(0,"Ділові якості більшості членів колективу: " + update.getMessage().getText() + "\n");
                                sendKeyboardToChat(chatId, "Особисті якості більшості членів колективу: ",
                                        ReplyKeyboardMarkupUtils.get9AnswerKeyboard());
                                user.setSecondQuestionsQueue(count2 + 1);
                            }else {
                                sendMessageToChat(wrongChoose,chatId);
                            }
                        break;
                    case 2:
                        if (chekAnswer(update,questionsStage)) {
                            secondQueuList.add(0,"Особисті якості більшості членів колективу: " +
                                    update.getMessage().getText() + "\n");
                            String ss = "";
                            for (String string : secondQueuList) {
                                ss += string + "\n";
                            }
                            user.setSecondAnswer9(ss);
                            user.setQuestionsStage(questionsStage+1);
                            selectQuestions(user,chatId,update);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                }
                break;
            case 9:
                sendKeyboardToChat(chatId, "Чи могли б Ви з упевненістю сказати про більшість членів вашого колективу з ким вони охоче спілкуються з ділових питань?",
                        ReplyKeyboardMarkupUtils.get10AnswerKeyboard());
                user.setQuestionsStage(questionsStage+1);
                break;
            case 10:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer10(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Яка атмосфера зазвичай переважає у Вашому колективі? На наведеній нижче шкалі цифра 1 відповідає хворій, нетовариській атмосфері, а 9 - навпаки, атмосфері взаєморозуміння, взаємної поваги. У яку із стрічки Ви помістили б свій колектив?",
                            ReplyKeyboardMarkupUtils.get8AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 11:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer11(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Як Ви думаєте, якби Ви вийшли на пенсію або довго не працювали з якоїсь причини, чи прагнули Ви зустрічатися з членами вашого колективу?",
                            ReplyKeyboardMarkupUtils.get11AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 12:
                int count3 = user.getThirdQuestionsQueue();
                switch (count3) {
                    case 0:
                        if (chekAnswer(update,questionsStage-1)) {
                            user.setSecondAnswer12(update.getMessage().getText());
                            sendKeyboardToChat(chatId, "Вкажіть, будь ласка, якою мірою Ви задоволені різними умовами роботи?",
                                    ReplyKeyboardMarkupUtils.get12AnswerKeyboard());
                            sendKeyboardToChat(chatId, "Стан устаткування (техніки): ",
                                    ReplyKeyboardMarkupUtils.get12AnswerKeyboard());
                            user.setThirdQuestionsQueue(count3 + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 1:
                        if (chekAnswer(update,questionsStage)) {
                            thirdQueuList.add(0, "Стан устаткування (техніки): " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Рівномірність забезпечення роботою: ",
                                    ReplyKeyboardMarkupUtils.get12AnswerKeyboard());
                            user.setThirdQuestionsQueue(count3 + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 2:
                        if (chekAnswer(update,questionsStage)) {
                            thirdQueuList.add(0, "Рівномірність забезпечення роботою: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Розмір заробітної плати: ",
                                    ReplyKeyboardMarkupUtils.get12AnswerKeyboard());
                            user.setThirdQuestionsQueue(count3 + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 3:
                        if (chekAnswer(update,questionsStage)) {
                            thirdQueuList.add(0, "Розмір заробітної плати: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Санітарно-гігієнічні умови: ",
                                    ReplyKeyboardMarkupUtils.get12AnswerKeyboard());
                            user.setThirdQuestionsQueue(count3 + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 4:
                        if (chekAnswer(update,questionsStage)) {
                            thirdQueuList.add(0, "Санітарно-гігієнічні умови: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Відносини з безпосереднім керівником: ",
                                    ReplyKeyboardMarkupUtils.get12AnswerKeyboard());
                            user.setThirdQuestionsQueue(count3 + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 5:
                        if (chekAnswer(update,questionsStage)) {
                            thirdQueuList.add(0, "Відносини з безпосереднім керівником: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Можливість підвищення кваліфікації: ",
                                    ReplyKeyboardMarkupUtils.get12AnswerKeyboard());
                            user.setThirdQuestionsQueue(count3 + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 6:
                        if (chekAnswer(update,questionsStage)) {
                            thirdQueuList.add(0, "Можливість підвищення кваліфікації: " + update.getMessage().getText() + "\n");
                            sendKeyboardToChat(chatId, "Різноманітність роботи: ",
                                    ReplyKeyboardMarkupUtils.get12AnswerKeyboard());
                            user.setThirdQuestionsQueue(count3 + 1);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                    case 7:
                        if (chekAnswer(update,questionsStage)) {
                            thirdQueuList.add(0,"Різноманітність роботи: " +
                                    update.getMessage().getText() + "\n");
                            String ss = "";
                            for (String string : thirdQueuList) {
                                ss += string + "\n";
                            }
                            user.setSecondAnswer13(ss);
                            user.setQuestionsStage(questionsStage+1);
                            selectQuestions(user,chatId,update);
                        }else {
                            sendMessageToChat(wrongChoose,chatId);
                        }
                        break;
                }
                break;
            case 13:
                sendKeyboardToChat(chatId, "Наскільки добре, на вашу думку, організовано вашу роботу?",
                        ReplyKeyboardMarkupUtils.get13AnswerKeyboard());
                user.setQuestionsStage(questionsStage+1);
                break;
            case 14:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer14(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Як Ви вважаєте, чи має ваш керівник реальний вплив на справи колективу?",
                            ReplyKeyboardMarkupUtils.get14AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 15:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer15(update.getMessage().getText());
                    sendMessageToChat( "Прізвище вашого безпосереднього керівника:",
                            chatId);
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 16:
                    user.setSecondAnswer15(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Ваша стать",
                            ReplyKeyboardMarkupUtils.get15AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                break;
            case 17:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer16(update.getMessage().getText());
                    sendMessageToChat("Ваш вік",
                            chatId);
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 18:
                    user.setSecondAnswer17(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Освіта",
                            ReplyKeyboardMarkupUtils.get17AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                break;
            case 19:
                if (chekAnswer(update,questionsStage-1)) {
                user.setSecondAnswer18(update.getMessage().getText());
                sendMessageToChat( "Стаж роботи в компанії",
                        chatId);
                user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            case 20:
                    user.setSecondAnswer19(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Оберіть бренд в якому ви працюєте",
                            ReplyKeyboardMarkupUtils.get19AnswerKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                break;
            case 21:
                if (chekAnswer(update,questionsStage-1)) {
                    user.setSecondAnswer20(update.getMessage().getText());
                    sendKeyboardToChat(chatId, "Дякую за ваші відповіді!",
                            ReplyKeyboardMarkupUtils.getBackToMainKeyboard());
                    user.setQuestionsStage(questionsStage+1);
                }else {
                    sendMessageToChat(wrongChoose,chatId);
                }
                break;
            default: sendKeyboardToChat(chatId, "Запитання для Вас закінчились, до зустрічі)",
                    ReplyKeyboardMarkupUtils.getBackToMainKeyboard());
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
    private boolean chekAnswer(Update update, int questionsStage){
        String answer = update.getMessage().getText();
        switch (questionsStage){
            case 0:
                if (answer.equals("Дуже подобається")||answer.equals("Мабуть, подобається")||answer.equals("Робота мені байдужа")||answer.equals("Мабуть, не подобається")||answer.equals("Дуже не подобається"))
                    return true;
            break;
            case 1:
            case 4:
                if (answer.equals("Так")||answer.equals("Ні")||answer.equals("Не знаю"))
                    return true;
                break;
            case 2:
                if (answer.equals("0")||answer.equals("1")||answer.equals("2")||answer.equals("3")||answer.equals("4")||answer.equals("5"))
                    return true;
                break;
            case 5:
                if (answer.equals("Більшість членів нашого колективу – добрі, симпатичні люди")||answer.equals("У нашому колективі є усілякі люди.")||answer.equals("Більшість членів нашого колективу – люди малоприємні"))
                    return true;
                break;
            case 6:
                if (answer.equals("Ні, звичайно")||answer.equals("Скоріше ні, ніж так")||answer.equals("Не знаю, не замислювався над цим")||answer.equals("Скоріше так, ніж ні")||answer.equals("Так, звичайно"))
                    return true;
                break;
            case 7:
            case 10:
                if (answer.equals("1")||answer.equals("2")||answer.equals("3")||answer.equals("4")||answer.equals("5")||answer.equals("6")||answer.equals("7")||answer.equals("8")||answer.equals("9"))
                    return true;
                break;
            case 8:
                if (answer.equals("Так")||answer.equals("Мабуть, так")||answer.equals("Не знаю, чи не замислювався над цим")||answer.equals("Мабуть, ні")||answer.equals("Ні"))
                    return true;
                break;
            case 9:
                if (answer.equals("Ні, не міг би")||answer.equals("Не знаю, не замислювався над цим")||answer.equals("Так, міг би"))
                    return true;
                break;
            case 11:
                if (answer.equals("Так, звичайно")||answer.equals("Швидше ні, ніж так")||answer.equals("Не знаю, не замислювався над цим")||answer.equals("Скоріше так, ніж ні")||answer.equals("Ні, звичайно"))
                    return true;
                break;
            case 12:
                if (answer.equals("Повністю задоволений")||answer.equals("Мабуть, задоволений")||answer.equals("Важко сказати")||answer.equals("Мабуть, незадоволений")||answer.equals("Цілком незадоволений"))
                    return true;
                break;
            case 13:
                if (answer.equals("На мою думку, наша робота організована дуже добре")||answer.equals("Загалом, непогано, хоча є можливість покращення")||answer.equals("Важко сказатиВажко сказати")||answer.equals("Робота організована незадовільно, багато часу витрачається марно")||answer.equals("На мою думку, робота організована дуже погано"))
                    return true;
                break;
            case 14:
                if (answer.equals("Безумовно, так")||answer.equals("Загалом, непогано, хоча є можливість покращення")||answer.equals("Мабуть, так")||answer.equals("Мабуть, ні")||answer.equals("Безумовно, ні"))
                    return true;
                break;
            case 16:
                if (answer.equals("Чоловік")||answer.equals("Жінка"))
                    return true;
                break;
            case 18:
                if (answer.equals("Незакінчена середня")||answer.equals("Середня освіта")||answer.equals("Середнє спеціальне")||answer.equals("Незакінчена вища")||answer.equals("Вища освіта"))
                    return true;
                break;
            case 20:
                if (answer.equals("DVE")||answer.equals("Konstar")||answer.equals("Bagland")||answer.equals("Have A Rest")||answer.equals("Home Rest")||answer.equals("Born"))
                    return true;
                break;
        }
        return false;
    }
}