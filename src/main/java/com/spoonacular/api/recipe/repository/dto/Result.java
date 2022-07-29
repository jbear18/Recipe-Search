package com.spoonacular.api.recipe.repository.dto;

        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonAlias;
        import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

    //what types of results we want:
    // 1. title
    // 2. ID (part of generating the link)
    // 3. calories
    // 4. image

    //got rid of carbs


    @JsonAlias("title")
    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonAlias("minCalories")
    private int minCalories;
    public int getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(int minCalories) {
        this.minCalories = minCalories;
    }

//    @JsonAlias("carbs")
//    private int carbs;
//    public int getCarbs() {
//        return carbs;
//    }
//
//    public void setCarbs(int carbs) {
//        this.carbs = carbs;
//    }

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