package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.repository.dto.Result;
import com.spoonacular.api.recipe.service.SpoonacularService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SpoonacularController.class)
class SpoonacularControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpoonacularService spoonacularService;

    @Test
    public void givenGoodQuery_whenSearchForResults_thenIsOkAndReturnsResults() throws Exception {
        //given
        String query = "pasta";
        String title = "Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs";
        int id = 716429;
        int minCalories = 636;
        String image = "https://webknox.com/recipeImages/716429-556x370.jpg";


        Result result = new Result();
        result.setTitle(title);
        result.setImage(image);
        result.setId(id);
        result.setCalories(minCalories);

        List<Result> expectedResults = Collections.singletonList(result);

        //when
        when(spoonacularService.getResults(query)).thenReturn((Result) expectedResults);

        //then
        MvcResult mvcResult = mockMvc.perform(get("/searchSpoonacularResults?q=" + query))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(title)))
                .andExpect(jsonPath("$[0].image", is(image)))
                .andExpect(jsonPath("$[0].minCalories", is(minCalories)))
                .andExpect(jsonPath("$[0].id", is(id)))
                .andReturn();

        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
    }

    @Test
    public void givenBadQuery_whenSearchForResults_thenIsNotFound() throws Exception {
        //given
        String query = "Java";

        //when
        //then
        mockMvc.perform(get("/searchSpoonacularResults?q=" + query))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
