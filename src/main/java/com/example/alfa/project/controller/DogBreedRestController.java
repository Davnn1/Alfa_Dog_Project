package com.example.alfa.project.controller;

import com.example.alfa.project.dto.base.WrapperResponse;
import com.example.alfa.project.service.DogBreedRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/breed/rest")
@CrossOrigin
public class DogBreedRestController {

    final DogBreedRestService dogBreedRestService;

    @Autowired
    public DogBreedRestController(DogBreedRestService dogBreedRestService) {
        this.dogBreedRestService = dogBreedRestService;
    }

    @GetMapping("/image/random")
    public WrapperResponse<String> getRandomDogImage() {
        String response = dogBreedRestService.randomImage();
        return WrapperResponse.generateSuccess(response);
    }

    @GetMapping("/image/random/{count}")
    public WrapperResponse<List<String>> getRandomDogListImages(@PathVariable int count) {
        List<String> response = dogBreedRestService.multipleRandomImage(count);
        return WrapperResponse.generateSuccess(response);
    }

    @GetMapping("/image/{breed}/random")
    public WrapperResponse<String> getRandomDogImageByBreed(@PathVariable String breed) {
        String response = dogBreedRestService.getRandomImageByBreed(breed);
        return WrapperResponse.generateSuccess(response);
    }

    @GetMapping("/image/{breed}/random/{count}")
    public WrapperResponse<List<String>> getRandomDogListImagesByBreed(@PathVariable String breed, @PathVariable int count) {
        List<String> response = dogBreedRestService.getListRandomImageByBreed(breed, count);
        return WrapperResponse.generateSuccess(response);
    }

    @GetMapping("/image/{breed}/{subBreed}/random")
    public WrapperResponse<String> getRandomDogImageBySubBreed(@PathVariable String breed, @PathVariable String subBreed) {
        String response = dogBreedRestService.getRandomImageBySubBreed(breed, subBreed);
        return WrapperResponse.generateSuccess(response);
    }

    @GetMapping("/image/{breed}/{subBreed}/random/{count}")
    public WrapperResponse<List<String>> getRandomDogListImagesBySubBreed(@PathVariable String breed, @PathVariable String subBreed, @PathVariable int count) {
        List<String> response = dogBreedRestService.getListRandomImageBySubBreed(breed, subBreed, count);
        return WrapperResponse.generateSuccess(response);
    }

    @GetMapping("/image/{breed}")
    public WrapperResponse<Object> getDogListImagesByBreed(@PathVariable String breed) {
        CompletableFuture<Object> futureResponse = dogBreedRestService.getListImageByBreed(breed);
        Object response = futureResponse.join(); // This blocks until the future completes
        return WrapperResponse.generateSuccess(response);
    }

    @GetMapping("/image/{breed}/{subBreed}")
    public WrapperResponse<List<String>> getDogListImagesBySubBreed(@PathVariable String breed, @PathVariable String subBreed) {
        List<String> response = dogBreedRestService.getListImageBySubBreed(breed, subBreed);
        return WrapperResponse.generateSuccess(response);
    }
}
