package com.example.alfa.project.controller;

import com.example.alfa.project.dto.base.WrapperResponse;
import com.example.alfa.project.service.DogBreedCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/breed")
@CrossOrigin
public class DogBreedCrudController {

    final DogBreedCrudService dogBreedCrudService;

    @Autowired
    public DogBreedCrudController(DogBreedCrudService dogBreedCrudService) {
        this.dogBreedCrudService = dogBreedCrudService;
    }

    @GetMapping("/list/all")
    public WrapperResponse<Map<String, List<String>>> listAllBreed() throws Exception {
        Map<String, List<String>> response = dogBreedCrudService.findAllBreed();
        return WrapperResponse.generateSuccess(response);
    }

    @GetMapping("{breed}/list")
    public WrapperResponse<Map<String, List<String>>> listSubBreed(@PathVariable String breed) throws Exception {
        Map<String, List<String>> response = dogBreedCrudService.findSubBreadByBreed(breed);
        return WrapperResponse.generateSuccess(response);
    }

    @PostMapping("/create")
    public WrapperResponse<String> createBreed(@RequestParam String breed) {
        dogBreedCrudService.createNewBreed(breed);
        return WrapperResponse.generateSuccess("Breed created");
    }

    @PostMapping("/create/{breed}")
    public WrapperResponse<String> createSubBreed(@RequestParam String subBreed, @PathVariable String breed) {
        dogBreedCrudService.createNewSubBreed(subBreed, breed);
        return WrapperResponse.generateSuccess("Sub breed created");
    }

    @DeleteMapping("/delete")
    public WrapperResponse<String> deleteBreed(@RequestParam String breed) {
        dogBreedCrudService.deleteBreed(breed);
        return WrapperResponse.generateSuccess("Breed deleted");
    }

    @DeleteMapping("/delete/{breed}")
    public WrapperResponse<String> deleteSubBreed(@RequestParam String subBreed, @PathVariable String breed) {
        dogBreedCrudService.deleteSubBreed(subBreed, breed);
        return WrapperResponse.generateSuccess("Sub breed deleted");
    }

    @PutMapping("/update")
    public WrapperResponse<String> updateBreed(@RequestParam String breed, @RequestParam String newBreed) {
        dogBreedCrudService.updateBreed(breed, newBreed);
        return WrapperResponse.generateSuccess("Breed updated");
    }

    @PutMapping("/update/{breed}")
    public WrapperResponse<String> updateSubBreed(@RequestParam String subBreed, @RequestParam String newSubBreed, @PathVariable String breed) {
        dogBreedCrudService.updateSubBreed(subBreed, newSubBreed, breed);
        return WrapperResponse.generateSuccess("Sub breed updated");
    }
}
