package com.vscode.springbootjournalappmongodb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FakeUser {

    private String email;
    private String username;
    private String password;
    private String phone;
    private Name name;
    private Address address;

    @Data
    public static class Address {
        private String city;
        private String street;
        private int number;
        private String zipcode;
        private Geolocation geolocation;
    }

    @Data
    public static class Geolocation {
        private double lat;
        @JsonProperty("long")
        private double longitude;
    }

    @Data
    public static class Name {
        private String firstname;
        private String lastname;
    }
}

