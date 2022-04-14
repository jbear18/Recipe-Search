package com.spoonacular.api.recipe.repository;

import com.spoonacular.api.recipe.repository.dto.SpoonacularObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class SpoonacularRepository {

  private final WebClient webClient;
  private static final String baseUrl =  "https://api.spoonacular.com/recipes/complexSearch";

  public SpoonacularRepository() {
    webClient = WebClient
            .builder()
            .baseUrl(baseUrl)
            .build();
  }

    public SpoonacularObject getResults(String query){
      return webClient.get()
              .uri(uriBuilder -> uriBuilder
                      .queryParam("query", query)
                      //GET API KEY FROM SPOONACULAR
                      .queryParam("apiKey", "a3e9c275cba8484cb80898a8423e9c34")
                      .build()
              ).retrieve()
              .bodyToMono(SpoonacularObject.class)
              .block()
              .getResults();
      //getResults is a method from SpoonacularController
    }

}
