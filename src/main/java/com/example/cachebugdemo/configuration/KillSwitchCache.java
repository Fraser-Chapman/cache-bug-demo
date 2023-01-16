package com.example.cachebugdemo.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class KillSwitchCache {
    public static final String KILL_SWITCH_CACHE_MANAGER = "kill-switch-cache-manager";

    private static final int MAXIMUM_CACHE_SIZE = 1000;
    private static final int TIME_TO_LIVE = 60;

    @Bean(name = KILL_SWITCH_CACHE_MANAGER)
    public CacheManager killSwitchCache() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("killSwitch");

        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(MAXIMUM_CACHE_SIZE)
                .expireAfterWrite(TIME_TO_LIVE, TimeUnit.SECONDS));

        return caffeineCacheManager;
    }
}


