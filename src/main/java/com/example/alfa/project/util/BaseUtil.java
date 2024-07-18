package com.example.alfa.project.util;

import com.example.alfa.project.config.Config;
import com.example.alfa.project.util.exception.access.BadArgumentsException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;

import java.util.*;
import java.util.stream.Collectors;


public class BaseUtil {

    public static Map<String, String> getErrorMsg(MessageSource messageSource, String code, String message) {
        return Config.getSupportedLanguage().entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    Locale locale = entry.getValue();
                    if (message != null && !message.isBlank()) {
                        return messageSource.getMessage(code, null, locale) + " " + message;
                    } else {
                        return messageSource.getMessage(code, null, locale);
                    }
                }
        ));
    }

    public static Map<String, List<String>> convertBreedsToList(Map<String, List<String>> breeds) {
        Map<String, List<String>> breedList = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : breeds.entrySet()) {
            String breed = entry.getKey();
            List<String> subBreeds = entry.getValue();
            if (subBreeds.isEmpty() || Objects.equals(breed, "sheepdog") || Objects.equals(breed, "terrier")) {
                for (String subBreed : subBreeds) {
                    breedList.put(breed + (subBreed.isEmpty() ? "" : "-" + subBreed), List.of());
                }
            } else {
                breedList.put(breed, subBreeds);
            }
        }
        return breedList;
    }

    public static List<Map<String, Object>> convertJsonToMap(String jsonString) throws BadArgumentsException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Map<String, Object>>> typeRef = new TypeReference<>() {};
            return objectMapper.readValue(jsonString, typeRef);
        } catch (Exception e) {
            throw new BadArgumentsException("");
        }
    }

    public static Map<String, Map<String, Object>> convertJsonToCondition(String jsonString) throws BadArgumentsException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<Map<String, Map<String, Object>>> typeRef = new TypeReference<>() {};
            return objectMapper.readValue(jsonString, typeRef);
        } catch (Exception e) {
            throw new BadArgumentsException("");
        }
    }
}