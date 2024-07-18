package com.example.alfa.project.service.impl;

import com.example.alfa.project.dao.DogBreedCrudDao;
import com.example.alfa.project.service.DogBreedCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.example.alfa.project.util.BaseUtil.convertBreedsToList;

@Service
public class DogBreedCrudServiceImpl implements DogBreedCrudService {

    private final DogBreedCrudDao dogBreedCrudDao;

    @Autowired
    public DogBreedCrudServiceImpl(DogBreedCrudDao dogBreedCrudDao) {
        this.dogBreedCrudDao = dogBreedCrudDao;
    }

    public Map<String, List<String>> findAllBreed() throws Exception {
        Instant start = Instant.now();

        Map<String, List<String>> breeds = dogBreedCrudDao.getBreedList();

        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);

        if (duration.toMillis() > 5000) {
            throw new Exception("Operation timed out");
        }

        return convertBreedsToList(breeds);
    }

    @Override
    public Map<String, List<String>> findSubBreadByBreed(String breed) throws Exception {
        Instant start = Instant.now();

        Map<String, List<String>> breeds = dogBreedCrudDao.getSubBreedListByBreed(breed);

        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);

        if (duration.toMillis() > 2000) {
            throw new Exception("Operation timed out");
        }

        return convertBreedsToList(breeds);
    }

    @Override
    public void createNewBreed(String breed) {
        dogBreedCrudDao.createNewBreed(breed);
    }

    @Override
    public void createNewSubBreed(String subBreed, String breed) {
        dogBreedCrudDao.createNewSubBreed(subBreed, breed);
    }

    @Override
    public void deleteBreed(String breed) {
        dogBreedCrudDao.deleteBreed(breed);
    }

    @Override
    public void deleteSubBreed(String subBreed, String breed) {
        dogBreedCrudDao.deleteSubBreed(subBreed, breed);
    }

    @Override
    public void updateBreed(String breed, String newBreed) {
        dogBreedCrudDao.updateBreed(breed, newBreed);
    }

    @Override
    public void updateSubBreed(String subBreed, String newSubBreed, String breed) {
        dogBreedCrudDao.updateSubBreed(subBreed, newSubBreed, breed);
    }
}
