package com.springapp.poseidon.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@DynamicUpdate
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 125)
    private String moodysRating;

    @Column(length = 125)
    private String sandPRating;

    @Column(length = 125)
    private String fitchRating;

    private int orderNumber;


    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        super();
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Rating() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", moodysRating='" + moodysRating + '\'' +
                ", sandPRating='" + sandPRating + '\'' +
                ", fitchRating='" + fitchRating + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
