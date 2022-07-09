package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.repository.dto.Result;
import com.spoonacular.api.recipe.service.SpoonacularService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class SpoonacularControllerTest {
    private SpoonacularController spoonacularController;

    @Mock
    private SpoonacularService spoonacularService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        spoonacularController = new SpoonacularController(spoonacularService);
    }

    @Test
    void givenGoodQuery_whenGetResults_thenReturnListOfResults() {
        //given
        String query = "Java";
        Result result = new Result();
        result.setTitle("TITLE");
        result.setCalories(100);

        List<Result> expectedResults = Collections.singletonList(result);

        when(spoonacularService.getResults(query))
                .thenReturn((Result) expectedResults);

        //when
        //GIVES A SINGLE RESULT, INSTEAD A LIST OF RESULTS??! (this is milestone 10)
        List<Result> actualResults = spoonacularController.getResults(query);

        //then
        assertEquals(expectedResults, actualResults);
    }

    @Test
    void givenBadQuery_whenGetResults_thenThrowsException() {
        //given
        String query = "Java";

        //when
        //then
        Throwable exceptionThrown = assertThrows(ResponseStatusException.class, () -> spoonacularController.getResults(query));
        assertEquals(exceptionThrown.getMessage(), "404 NOT_FOUND \"Result(s) not found.\"");
    }




}
