package com.spoonacular.api.recipe.service;

import com.spoonacular.api.recipe.repository.SpoonacularRepository;
import com.spoonacular.api.recipe.repository.dto.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//MILESTONE 10
public class SpoonacularServiceTest {
    private SpoonacularService spoonacularService;

    @Mock
    private SpoonacularRepository spoonacularRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        spoonacularService = new SpoonacularService(spoonacularRepository);
    }
    @Test
    void whenGetResults_thenReturnListOfResults() {
        //given
        String query = "Java";
        Result result = new Result();
        result.setTitle("TITLE");
        result.setCalories(100);

        List<Result> expectedResults = Collections.singletonList(result);

        //when
        when(spoonacularService.getResults(query))
                .thenReturn((Result) expectedResults);


        List<Result> actualResults = (List<Result>) spoonacularService.getResults(query);

        //then
        assertEquals(expectedResults, actualResults);
    }

}
