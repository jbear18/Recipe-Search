package com.spoonacular.api.recipe.service;

import com.spoonacular.api.recipe.repository.SpoonacularRepository;
import com.spoonacular.api.recipe.repository.dto.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpoonacularService {
    private final SpoonacularRepository spoonacularRepository;

    public SpoonacularService(SpoonacularRepository spoonacularRepository) {
        this.spoonacularRepository = spoonacularRepository;
    }

    public List<Result> getResults(String query){
        return spoonacularRepository.getResults(query);
    }
}
