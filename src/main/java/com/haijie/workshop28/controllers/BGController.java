package com.haijie.workshop28.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haijie.workshop28.models.Game;
import com.haijie.workshop28.models.Review;
import com.haijie.workshop28.models.ReviewResult;
import com.haijie.workshop28.services.BGService;

import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/game",produces = MediaType.APPLICATION_JSON_VALUE)
public class BGController {
    
        @Autowired
        BGService bgs;

        // Day28workshop #a
        @GetMapping("/{gid}/reviews")
        public ResponseEntity<String> getGameIdReviews(@PathVariable String gid){
        Optional<Game> r = bgs.aggregateGame(gid);
        System.out.println(r.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(r.get().toJSON().toString());
    }

     // Day28workshop #b
     @GetMapping("/highest")
     public ResponseEntity<String> getHighestRatedGames(@RequestParam String username,
             @RequestParam Integer limit) {
 
         return ResponseEntity
                 .status(HttpStatus.OK)
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(getResultForCommentResult(limit, username, "highest", 6).toString());
     }
 
     @GetMapping("/lowest")
     public ResponseEntity<String> getLowestRatedGames(@RequestParam String username,
             @RequestParam Integer limit) {
 
         return ResponseEntity
                 .status(HttpStatus.OK)
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(getResultForCommentResult(limit, username, "lowest", 5).toString());
     }
 
     private JsonObject getResultForCommentResult(Integer limit, String username,
             String rating, Integer ratingInt) {
         // JsonObject result = null;
         List<Review> r = bgs.aggregateGamesComments(limit, username, ratingInt);
         // JsonObjectBuilder builder = Json.createObjectBuilder();
         ReviewResult rR = new ReviewResult();
         rR.setRating(rating);
         rR.setGames(r);
         rR.setTimestamp(LocalDateTime.now().toString());
         // builder.add("workshop28b", rR.toJSON());
         // result = builder.build();
         return rR.toJSON();
     }
}
