package com.example.alfa.project.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DogBreedRestService {
    String randomImage();

    List<String> multipleRandomImage(int count);

    CompletableFuture<Object> getListImageByBreed(String breed);

    String getRandomImageByBreed(String breed);

    List<String> getListRandomImageByBreed(String breed, int count);

    List<String> getListImageBySubBreed(String breed, String subBreed);

    String getRandomImageBySubBreed(String breed, String subBreed);

    List<String> getListRandomImageBySubBreed(String breed, String subBreed, int count);
}
