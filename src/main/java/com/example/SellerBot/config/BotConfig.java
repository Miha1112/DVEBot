package com.example.SellerBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Sh1chiro on 27.07.2024.
 * <p>
 * When I wrote this code, only god and
 * I knew how it worked.
 * Now, only god knows it!
 *
 * @author Sh1chiro
 */

@Configuration
@Data
public class BotConfig {
    @Value("Gena") String botName;
    @Value("7462967364:AAH_v2MEE3fxolMFZqekf-WGCXj-mcNV_fQ") String token;
    @Value("id") String chatId;
}
