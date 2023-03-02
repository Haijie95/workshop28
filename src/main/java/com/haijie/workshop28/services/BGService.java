package com.haijie.workshop28.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haijie.workshop28.models.EditedReview;
import com.haijie.workshop28.models.Game;
import com.haijie.workshop28.models.Review;
import com.haijie.workshop28.repositories.BGRepo;

@Service
public class BGService {

    @Autowired
    BGRepo bgr;


    public String addReview(Review review) {
        String reviewId = UUID.randomUUID().toString().substring(0, 8);
        review.setRId(reviewId);
        bgr.insertReview(review);
        return reviewId;
    }


    public Review updateReviewById(String rId, String json) throws IOException{
            return bgr.updateReviewById(rId, json);
    }


    public Review getReviewById(String _id){
    
        Review r = bgr.getReviewById(_id);
        
        if(r.getEdited() != null){
            List<EditedReview> ll = (List<EditedReview>) r.getEdited();
            System.out.println(ll.size());
            // if there is an edited review in list, then Boolean = true
            if (ll.size() > 0)
                r.setIsEdited(Boolean.valueOf(true));
            else
                r.setIsEdited(Boolean.valueOf(false));
        }
        r.setTimestamp(LocalDateTime.now());

        return r;
    }

    //Day28Workshop #b
    public Optional<Game> aggregateGame(String gid){
        return bgr.aggregateGameReviews(gid);
    }
    
    public List<Review> aggregateGamesComments(Integer limit, String username, Integer rating){
            return bgr.aggregateGamesComments(limit, username, rating);
    }
    
}
