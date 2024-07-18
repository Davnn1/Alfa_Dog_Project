package com.example.alfa.project.service.impl;

import com.example.alfa.project.dao.DogBreedSyncDao;
import com.example.alfa.project.dto.rest.RestResponse;
import com.example.alfa.project.service.DogBreedSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class DogBreedSyncServiceImpl implements DogBreedSyncService {

    DogBreedSyncDao dogBreedSyncDao;

    @Autowired
    public DogBreedSyncServiceImpl(DogBreedSyncDao dogBreedSyncDao) {
        this.dogBreedSyncDao = dogBreedSyncDao;
    }

    @Override
    public void syncDogBreeds() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dog.ceo/api/breeds/list/all";

        ResponseEntity<RestResponse<Map<String, List<String>>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        Map<String, List<String>> restResponse = Objects.requireNonNull(response.getBody()).getMessage();
        List<String> breeds = convertBreedsToList(restResponse);
        Map<String, List<String>> subBreeds = convertBreedsSubBreedToList(restResponse);

        dogBreedSyncDao.syncDogBreeds(breeds);
        dogBreedSyncDao.syncDogSubBreeds(subBreeds);
    }

    public static Map<String, List<String>> convertBreedsSubBreedToList(Map<String, List<String>> breeds) {
        Map<String, List<String>> breedList = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : breeds.entrySet()) {
            String breed = entry.getKey();
            List<String> subBreeds = entry.getValue();
            if (!subBreeds.isEmpty()) {
                breedList.put(breed, subBreeds);
            }
        }
        return breedList;
    }

    public List<String> convertBreedsToList(Map<String, List<String>> breeds) {
        List<String> allBreeds = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : breeds.entrySet()) {
            String breed = entry.getKey();
            allBreeds.add(breed); // Add the main breed
        }
        return allBreeds;
    }
}
