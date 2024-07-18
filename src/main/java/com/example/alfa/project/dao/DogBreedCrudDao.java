package com.example.alfa.project.dao;

import java.util.List;
import java.util.Map;

public interface DogBreedCrudDao {
    Map<String, List<String>> getBreedList();

    Map<String, List<String>> getSubBreedListByBreed(String breed);

    void createNewBreed(String breed);

    void createNewSubBreed(String subBreed, String breed);

    void deleteBreed(String breed);

    void deleteSubBreed(String subBreed, String breed);

    void updateBreed(String breed, String newBreed);

    void updateSubBreed(String subBreed, String newSubBreed, String breed);
}
