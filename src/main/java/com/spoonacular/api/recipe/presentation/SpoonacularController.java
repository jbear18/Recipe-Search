package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.repository.SpoonacularRepository;
import com.spoonacular.api.recipe.repository.dto.SpoonacularObject;
import com.spoonacular.api.recipe.service.SpoonacularService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpoonacularController {

    private final SpoonacularService locService;


    public SpoonacularController(SpoonacularService locService) {
        this.locService = locService;
    }

//    @GetMapping("/searchLocResults")
    //originally response = Result.class
@ApiOperation(value = "Searches for articles matching the search term",
        notes = "Response may include multiple Result values.",
        response = SpoonacularObject.class,
        responseContainer="List")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Result(s) found"),
        @ApiResponse(code = 404, message = "Result(s) not found")
})

    public String getResults(@RequestParam(value="q") String query){
//        return "Searching for recipes related to " + query;

    SpoonacularObject result = locService.getResults(query);

//    ArrayList<SpoonacularObject>SpoonacularController results = locService.getResults(query);
        if(result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found.");
        }
        return result.toString();
    }


}
