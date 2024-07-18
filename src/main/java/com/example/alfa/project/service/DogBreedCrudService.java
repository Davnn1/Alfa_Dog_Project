package com.example.alfa.project.service;

import java.util.List;
import java.util.Map;

public interface DogBreedCrudService {
    Map<String, List<String>> findAllBreed() throws Exception;

    Map<String, List<String>> findSubBreadByBreed(String breed) throws Exception;

    void createNewBreed(String breed);

    void createNewSubBreed(String subBreed, String breed);

    void deleteBreed(String breed);

    void deleteSubBreed(String subBreed, String breed);

    void updateBreed(String breed, String newBreed);

    void updateSubBreed(String subBreed, String newSubBreed, String breed);
}
