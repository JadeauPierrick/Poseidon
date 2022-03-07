package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.Rating;
import com.springapp.poseidon.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Integer id) throws Exception {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            return rating.get();
        }else {
            throw new Exception("The rating was not found");
        }
    }

    @Override
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRatingById(Integer id) throws Exception {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            ratingRepository.deleteById(id);
        }else {
            throw new Exception("Invalid rating Id : " + id);
        }
    }
}
