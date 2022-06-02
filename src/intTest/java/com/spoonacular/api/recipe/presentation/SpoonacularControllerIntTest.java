package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.service.SpoonacularService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SpoonacularController.class)
public class SpoonacularControllerIntTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SpoonacularService ss;
    private SpoonacularController sc;
    //^added the spoonacularcontroller myself (not in instructions)

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sc = new SpoonacularController(ss);

        //similar to what we did earlier (with the API). resolve both issues
        List<Result> actualResults = sc.getResults(query);
    }
    @MockBean
    private SpoonacularService spoonacularService;

    //CHANGE THE TEST: NEED TO RUN A SIMILARONE BUT NOT THIS EXACT ONE
    @Test
    public void givenGoodQuery_whenSearchForResults_thenIsOkAndReturnsResults() throws Exception {
        //given
        String query = "Java";
        String title = "Java: A Drink, an Island, and a Programming Language";
        String author = "AUTHOR";
        String link = "LINK";
        Result result = new Result();
        result.setTitle(title);
        result.setAuthors(Collections.singletonList(author));
        result.setLink(link);
        List<Result> expectedResults = Collections.singletonList(result);

        when(locService.getResults(query)).thenReturn(expectedResults);

        //when
        //then
        MvcResult mvcResult = mockMvc.perform(get("/searchLocResults?q=" + query))
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
        mockMvc.perform(get("/searchLocResults?q=" + query))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
