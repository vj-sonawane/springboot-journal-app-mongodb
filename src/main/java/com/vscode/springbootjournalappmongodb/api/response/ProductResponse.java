package com.vscode.springbootjournalappmongodb.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

    @Data
    public class Rating{
        private double rate;
        private int count;
    }

}

