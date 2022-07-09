package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.repository.dto.Result;
import com.spoonacular.api.recipe.service.SpoonacularService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SpoonacularController.class)
public class SpoonacularControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpoonacularService spoonacularService;

    @Test
    public void givenGoodQuery_whenSearchForResults_thenIsOkAndReturnsResults() throws Exception {
        //given
        String query = "pasta";
        String title = "Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs";
        int calories = 636;
        String link = "LINK";

        Result result = new Result();

        result.setTitle(title);
        result.setAuthors(Collections.singletonList(author));
        result.setLink(link);
        List<Result> expectedResults = Collections.singletonList(result);

       //when
        when(spoonacularService.getResults(query)).thenReturn(expectedResults);

        //then
        MvcResult mvcResult = mockMvc.perform(get("/searchSpoonacularResults?q=" + query))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(title)))
                .andExpect(jsonPath("$[0].authors[0]", is(author)))
                .andExpect(jsonPath("$[0].link", is(link)))
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
