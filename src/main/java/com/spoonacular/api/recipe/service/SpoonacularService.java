package com.spoonacular.api.recipe.service;

import org.springframework.stereotype.Service;

@Service
public class SpoonacularService {
    public String getResults(String query){
        return "Searching for recipes related to " + query;
    }
}
