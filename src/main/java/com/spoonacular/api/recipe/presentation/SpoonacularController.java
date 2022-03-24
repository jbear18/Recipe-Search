package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.service.SpoonacularService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpoonacularController {

    private final SpoonacularService locService;

    public SpoonacularController(SpoonacularService locService) {
        this.locService = locService;
    }

    @GetMapping("/searchLocResults")
    @ApiOperation(value = "Searches for books matching the search term",
            notes = "Response may include multiple Result values.",
            response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result(s) found")
    })
    public String getResults(    @RequestParam(value="q") String query){
        return "Searching for recipes related to " + query;
    }
}
