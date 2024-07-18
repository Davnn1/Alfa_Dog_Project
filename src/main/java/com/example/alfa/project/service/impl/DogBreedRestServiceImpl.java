package com.example.alfa.project.service.impl;

import com.example.alfa.project.dto.rest.RestResponse;
import com.example.alfa.project.service.DogBreedRestService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class DogBreedRestServiceImpl implements DogBreedRestService {
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;

    public DogBreedRestServiceImpl() {
        this.executorService = Executors.newCachedThreadPool();
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String randomImage() {
        String url = "https://dog.ceo/api/breeds/image/random";

        ResponseEntity<RestResponse<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> multipleRandomImage(int count) {
        String url = "https://dog.ceo/api/breeds/image/random/{count}";

        ResponseEntity<RestResponse<List<String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                count
        );

        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public CompletableFuture<Object> getListImageByBreed(String breed) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "https://dog.ceo/api/breed/{breed}/images";
            ResponseEntity<RestResponse<List<String>>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<RestResponse<List<String>>>() {
                    },
                    breed
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<String> images = response.getBody().getMessage();
                if (breed.equalsIgnoreCase("shiba")) {
                    return filterShibaImages(images);
                } else if (breed.equalsIgnoreCase("terrier")) {
                    return groupTerrierImages(images);
                } else {
                    return images;
                }
            } else {
                throw new RuntimeException("Failed to fetch images for breed: " + breed);
            }
        }, executorService);
    }

    @Override
    public String getRandomImageByBreed(String breed) {
        String url = "https://dog.ceo/api/breed/{breed}/images/random";

        ResponseEntity<RestResponse<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                breed
        );

        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> getListRandomImageByBreed(String breed, int count) {
        String url = "https://dog.ceo/api/breed/{breed}/images/random/{count}";

        ResponseEntity<RestResponse<List<String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                breed,
                count
        );

        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> getListImageBySubBreed(String breed, String subBreed) {
        String url = "https://dog.ceo/api/breed/{breed}/{subBreed}/images";

        ResponseEntity<RestResponse<List<String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                breed,
                subBreed
        );

        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public String getRandomImageBySubBreed(String breed, String subBreed) {
        String url = "https://dog.ceo/api/breed/{breed}/{subBreed}/images/random";

        ResponseEntity<RestResponse<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                breed,
                subBreed
        );

        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> getListRandomImageBySubBreed(String breed, String subBreed, int count) {
        String url = "https://dog.ceo/api/breed/{breed}/{subBreed}/images/random/{count}";

        ResponseEntity<RestResponse<List<String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                breed,
                subBreed,
                count
        );

        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    private List<String> filterShibaImages(List<String> images) {
        return images.stream()
                .filter(image -> {
                    String filename = image.substring(image.lastIndexOf("/") + 1, image.lastIndexOf("."));
                    String numberPart = filename.replaceAll("[^0-9]", "");
                    if (!numberPart.isEmpty()) {
                        int number = Integer.parseInt(numberPart);
                        return number % 2 != 0;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> groupTerrierImages(List<String> images) {
        return images.stream()
                .collect(Collectors.groupingBy(image -> {
                    String[] parts = image.split("/");
                    return parts[parts.length - 2];
                }));
    }
}
