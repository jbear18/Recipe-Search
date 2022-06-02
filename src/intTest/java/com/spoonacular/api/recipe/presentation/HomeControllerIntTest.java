package com.spoonacular.api.recipe.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@WebMcvTest(HomeController.class)
public class HomeControllerIntTest {
@Autowired
private MockMvc mockMvc;

    @Test
    public void whenHome_ThenReturnMovedPermanentlyAndRedirect() {

    }

    @Test
    public void givenController_WhenHome_ThenReturnMovedPermanentlyAndRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isMovedPermanently())
                .andExpect(redirectedUrl("swagger-ui.html"));
    }
}

//PROBLEM: packages names are weird and combined together.
//then run the test to make sure it passes using play button.
