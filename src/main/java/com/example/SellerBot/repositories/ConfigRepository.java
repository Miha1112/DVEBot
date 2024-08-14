package com.example.SellerBot.repositories;

import com.example.SellerBot.models.Config;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sh1chiro on 27.07.2024.
 * <p>
 * When I wrote this code, only god and
 * I knew how it worked.
 * Now, only god knows it!
 *
 * @author Sh1chiro
 */

public interface ConfigRepository extends JpaRepository<Config, Long> {
    Config findFirstByOrderByIdAsc();
}
