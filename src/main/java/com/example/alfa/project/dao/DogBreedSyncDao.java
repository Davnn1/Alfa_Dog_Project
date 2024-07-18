package com.example.alfa.project.dao;

import java.util.List;
import java.util.Map;

public interface DogBreedSyncDao {
    void syncDogBreeds(List<String> dogBreeds);

    void syncDogSubBreeds(Map<String, List<String>> dogSubBreeds);
}
