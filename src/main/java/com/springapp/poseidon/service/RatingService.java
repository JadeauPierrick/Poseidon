package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> getRatings();

    Rating getRatingById(Integer id) throws Exception;

    Rating addRating(Rating rating);

    void deleteRatingById(Integer id) throws Exception;
}
