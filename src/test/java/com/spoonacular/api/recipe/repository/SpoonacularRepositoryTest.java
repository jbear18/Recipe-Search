package com.spoonacular.api.recipe.repository;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.spoonacular.api.recipe.repository.dto.SpoonacularResponse;
import com.spoonacular.api.recipe.repository.dto.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SpoonacularRepositoryTest {

    private SpoonacularRepository spoonacularRepository;

    @Mock
    WebClient webClientMock;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    WebClient.ResponseSpec responseSpecMock;

    @Mock
    Mono<SpoonacularResponse> SpoonacularResponseMonoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        spoonacularRepository = new SpoonacularRepository(webClientMock);
    }

    @Test
    void whenGetResults_thenReturnListOfResults() {
        //given
        String query = "Java";
        SpoonacularResponse spoonacularResponse = new SpoonacularResponse();
        Result result = new Result();
        result.setTitle("Find recipes!!");
        result.setCalories(150);
        List<Result> expectedResults = Collections.singletonList(result);
        spoonacularResponse.setResults(expectedResults);

        when(webClientMock.get())
                .thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(SpoonacularResponse.class))
                .thenReturn(SpoonacularResponseMonoMock);
        when(SpoonacularResponseMonoMock.block())
                .thenReturn(spoonacularResponse);

        //when
        List<Result> actualSpoonacularResults = new ArrayList<Result>();
        actualSpoonacularResults.add(spoonacularRepository.getResults(query));

        //then
        assertEquals(expectedResults, actualSpoonacularResults);
    }

}