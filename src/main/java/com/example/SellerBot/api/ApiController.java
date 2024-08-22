package com.example.SellerBot.api;

import com.example.SellerBot.config.Initializer;
import com.example.SellerBot.models.User;
import com.example.SellerBot.services.AppTelegramBot;
import com.example.SellerBot.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final UserService userService;
    private final AppTelegramBot bot;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching users", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest messageRequest ) {
        System.out.println("User telegram id to send message: " + messageRequest.getTelegramId());
        System.out.println("Message text: " + messageRequest.getMessage());
        bot.checkMessageBeforeSend(messageRequest.getMessage(), messageRequest.getChatId());
        System.out.println("try send message in tg");
        return ResponseEntity.ok("Повідомлення відправлено успішно!");
    }
}
class MessageRequest {
    @Setter
    @Getter
    private String telegramId;  // Змінив на String, так як це ID

    @Setter
    @Getter
    private String message;

    public Long getChatId() {
        // Перетворити telegramId на Long
        try {
            return Long.parseLong(telegramId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
