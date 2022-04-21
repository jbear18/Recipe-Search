package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.repository.dto.SpoonacularObject;
import com.spoonacular.api.recipe.service.SpoonacularService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.transform.Result;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HomeControllerTest {

    HomeController homecontroller = new HomeController();

    private SpoonacularController scontroller;

    @Mock
    private SpoonacularService sService;
    //sService is the same as locService

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        scontroller = new SpoonacularController(sService);
    }

    @Test
    void whenHome_thenReturnRedirect() {
        //given
        String expected = "redirect:swagger-ui.html";

        //when
        String actual = homecontroller.home();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenGoodQuery_whenGetResults_thenReturnListOfResults() {
        //given
        String query = "pasta";
        String result = scontroller.getResults(query);

//       "https://spoonacular.com/Breadcrumbs-716429"


        //when
        List<Result> actualResults = scontroller.getResults(query);

        //then
        assertEquals(expectedResults, actualResults);
    }

    @Test
    void givenBadQuery_whenGetResults_thenThrowsException() {
        //given
        String query = "Java";

        //when
        //then
        Throwable exceptionThrown = assertThrows(ResponseStatusException.class, () -> scontroller.getResults(query));
        assertEquals(exceptionThrown.getMessage(), "404 NOT_FOUND \"Result(s) not found.\"");
    }


    private void assertEquals(String expected, String actual) {
    }


}