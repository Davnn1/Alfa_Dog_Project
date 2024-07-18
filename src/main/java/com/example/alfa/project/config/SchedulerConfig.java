package com.example.alfa.project.config;

import com.example.alfa.project.service.DogBreedSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final DogBreedSyncService dogBreedSyncService;

    @Autowired
    public SchedulerConfig(DogBreedSyncService dogBreedSyncService) {
        this.dogBreedSyncService = dogBreedSyncService;
    }

    //    @Scheduled(cron = "0 0 0/12 * * ?") // Sinkronisasi setiap 12 jam
    @Scheduled(cron = "0 * * * * ?") // Sinkronisasi setiap Menit
    public void scheduleDogBreedSync() {
        dogBreedSyncService.syncDogBreeds();
    }
}