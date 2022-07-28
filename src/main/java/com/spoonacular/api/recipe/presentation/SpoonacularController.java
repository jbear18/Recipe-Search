package com.spoonacular.api.recipe.presentation;

import com.spoonacular.api.recipe.repository.SpoonacularRepository;
import com.spoonacular.api.recipe.repository.dto.Result;
import com.spoonacular.api.recipe.repository.dto.Result;
import com.spoonacular.api.recipe.service.SpoonacularService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SpoonacularController {

    private final SpoonacularService spoonacularService;


    public SpoonacularController(SpoonacularService spoonacularService) {
        this.spoonacularService = spoonacularService;
    }

        @GetMapping("/searchSpoonacularResults")
    //originally response = Result.class
    @ApiOperation(value = "Searches for recipes matching the search term",
            notes = "Response may include multiple Result values.",
            response = Result.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result(s) found"),
            @ApiResponse(code = 404, message = "Result(s) not found")
    })


//Result result = spoonacularService.getResults(query);
//
//    List<Result>SpoonacularController results = spoonacularService.getResults(query);
//        if(result == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found.");
//        }
//       return result.toString();
//

    public List<Result> getResults(@RequestParam(value="q") String query){
        List<Result> results = new ArrayList<Result>();
        results.add(spoonacularService.getResults(query));
        if(CollectionUtils.isEmpty(results)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found.");
        }
        return results;
    }


    }
