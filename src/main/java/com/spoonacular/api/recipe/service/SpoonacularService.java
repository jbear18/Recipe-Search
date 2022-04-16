package com.spoonacular.api.recipe.service;

import com.spoonacular.api.recipe.repository.SpoonacularRepository;
import com.spoonacular.api.recipe.repository.dto.SpoonacularObject;
import org.springframework.stereotype.Service;

@Service
public class SpoonacularService {
    private final SpoonacularRepository spoonacularRepository;

    public SpoonacularService(SpoonacularRepository spoonacularRepository) {
        this.spoonacularRepository = spoonacularRepository;
    }

    public SpoonacularObject getResults(String query){
        return spoonacularRepository.getResults(query);
    }
}
