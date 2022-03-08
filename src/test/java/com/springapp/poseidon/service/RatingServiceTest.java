package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.Rating;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RatingServiceTest {

    @Autowired
    private RatingService ratingService;

    private Rating rating;

    @BeforeAll
    private void setUp() {
        rating = new Rating();
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);
    }

    @Test
    @Order(1)
    public void addRatingTest() {
        ratingService.addRating(rating);
        assertNotNull(rating.getId());
        assertThat(rating.getOrderNumber()).isEqualTo(10);
    }

    @Test
    @Order(2)
    public void getRatingsTest() {
        List<Rating> list = ratingService.getRatings();
        assertTrue(list.size() > 0);
    }

    @Test
    @Order(3)
    public void getRatingByIdTest() throws Exception {
        Rating r = ratingService.getRatingById(rating.getId());
        assertThat(r.getId()).isEqualTo(rating.getId());
    }

    @Test
    @Order(4)
    public void getRatingByNullIdTest() {
        try {
            ratingService.getRatingById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("The rating was not found");
        }
    }

    @Test
    @Order(5)
    public void deleteRatingByIdTest() throws Exception {
        ratingService.deleteRatingById(rating.getId());
        List<Rating> list = ratingService.getRatings();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    @Order(6)
    public void deleteRatingByNullIdTest() {
        try {
            ratingService.deleteRatingById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("Invalid rating Id : " + 10);
        }
    }
}
