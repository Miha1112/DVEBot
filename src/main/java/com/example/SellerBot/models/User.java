package com.example.SellerBot.models;

import com.example.SellerBot.enums.Role;
import com.example.SellerBot.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

@Data
@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long telegramId;
    private String name;
    private String nickname;
    private UserStatus status;
    private String secondAnswer1;
    private String secondAnswer2;
    @Column(length = 2500)
    private String secondAnswer3;
    private String secondAnswer4;
    private String secondAnswer5;
    private String secondAnswer6;
    private String secondAnswer7;
    private String secondAnswer8;
    private String secondAnswer9;
    private String secondAnswer10;
    private String secondAnswer11;
    private String secondAnswer12;
    @Column(length = 2500)
    private String secondAnswer13;
    private String secondAnswer14;
    private String secondAnswer15;
    private String secondAnswer16;
    private String secondAnswer17;
    private String secondAnswer18;
    private String secondAnswer19;
    private String secondAnswer20;
    private String secondAnswer21;
    private LocalDateTime dateOfCreated;
    private int questionsStage = 0;
    private int firstQuestionsQueue = 0;
    private int secondQuestionsQueue = 0;
    private int thirdQuestionsQueue = 0;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
