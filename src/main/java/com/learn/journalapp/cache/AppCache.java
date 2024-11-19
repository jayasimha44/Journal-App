package com.learn.journalapp.cache;

import com.learn.journalapp.entity.ConfigJournalApp;
import com.learn.journalapp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AppCache {

    public Map<String, String> APP_CACHE;

    private final ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init() {
        APP_CACHE = new HashMap<>();
         List<ConfigJournalApp> configJournalAppList = configJournalAppRepository.findAll();
         for(ConfigJournalApp configJournalApp : configJournalAppList) {
             APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue());
         }
    }
}
