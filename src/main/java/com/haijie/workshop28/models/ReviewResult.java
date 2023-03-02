package com.haijie.workshop28.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResult {
    private String rating;
    private List<Review> games;
    private String timestamp;


    public JsonObject toJSON() {
        
        return Json.createObjectBuilder()
                .add("rating", getRating())
                .add("games", getGames().toString())
                .add("timestamp", getTimestamp())
                .build();
    }
}
