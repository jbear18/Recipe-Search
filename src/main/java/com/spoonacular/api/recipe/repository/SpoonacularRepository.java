package com.spoonacular.api.recipe.repository;

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

    public String getResults(String query){
      return webClient.get()
              .uri(uriBuilder -> uriBuilder
                      .queryParam("query", query)
                      //GET API KEY FROM SPOONACULAR
                      .queryParam("apiKey", "YOUR API KEY GOES HERE")
                      .build()
              ).retrieve()
              .bodyToMono(String.class)
              .block();
    }
}
