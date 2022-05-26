package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.service.SpoonacularService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.transform.Result;

public class SpoonacularControllerTest {
    private SpoonacularController sc;

    @Mock
    private SpoonacularService ss;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sc = new SpoonacularController(ss);
    }

    @Test
    void givenGoodQuery_whenGetResults_thenReturnListOfResults() {
        //given
        String query = "Java";
        Result result = new Result();
        result.setTitle("TITLE");
        result.setLink("LINK");
        result.setAuthors(Collections.singletonList("AUTHORS"));
        List<Result> expectedResults = Collections.singletonList(result);

        when(locService.getResults(query))
                .thenReturn(expectedResults);

        //when
        List<Result> actualResults = locController.getResults(query);

        //then
        assertEquals(expectedResults, actualResults);
    }

    @Test
    void givenBadQuery_whenGetResults_thenThrowsException() {
        //given
        String query = "Java";

        //when
        //then
        Throwable exceptionThrown = assertThrows(ResponseStatusException.class, () -> locController.getResults(query));
        assertEquals(exceptionThrown.getMessage(), "404 NOT_FOUND \"Result(s) not found.\"");
    }



}
