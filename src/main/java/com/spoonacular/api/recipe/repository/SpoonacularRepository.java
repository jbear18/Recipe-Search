package com.spoonacular.api.recipe.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.spoonacular.api.recipe.repository.dto.Result;
import com.spoonacular.api.recipe.repository.dto.SpoonacularResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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

  public SpoonacularRepository(WebClient webClientMock) {
    this.webClient = webClientMock;
  }

  public List<Result> getResults(String topic) {
return webClient.get()
            .uri(uriBuilder -> uriBuilder
                      .queryParam("apiKey", "a3e9c275cba8484cb80898a8423e9c34")
                      .queryParam("query", topic)
                      .queryParam("sort", "popularity")
                    .build()
            ).retrieve()
            .bodyToMono(SpoonacularResponse.class)
            .block()

            .getResults();

  }


}
