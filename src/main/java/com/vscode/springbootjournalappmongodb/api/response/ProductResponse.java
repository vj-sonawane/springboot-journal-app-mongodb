package com.vscode.springbootjournalappmongodb.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private double price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private String category;
    @JsonProperty("image")
    private String image;
    @JsonProperty("rating")
    private Rating rating;

    @Data
    public class Rating{
        @JsonProperty("rate")
        private double rate;
        @JsonProperty("count")
        private int count;
    }

}

