package com.example.SellerBot.services;

import com.example.SellerBot.models.Config;
import com.example.SellerBot.repositories.ConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
@Service
@RequiredArgsConstructor
public class ConfigService {
    private final ConfigRepository configRepository;

    public Config save(Config config){
        return configRepository.save(config);
    }

    public Config getConfig(){
        return configRepository.findFirstByOrderByIdAsc();
    }
}
