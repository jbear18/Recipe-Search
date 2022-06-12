package com.spoonacular.api.recipe.repository.dto;

        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonAlias;
        import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

    //what types of results we want:
    // 1. title
    // 2. ID (part of generating the link)
    // 3. calories
    // 4. carbs
    // 5. image


    @JsonAlias("title")
    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonAlias("calories")
    private int calories;
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @JsonAlias("carbs")
    private String carbs;
    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    @JsonAlias("image")
    private String image;
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image= image;
    }

    @JsonAlias("id")
    private int id;
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}