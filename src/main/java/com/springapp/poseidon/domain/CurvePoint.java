package com.springapp.poseidon.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@DynamicUpdate
@Entity
@Table(name = "curvePoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "must not be null")
    private int curveId;

    private Timestamp asOfDate;

    private double term;

    private double value;

    private Timestamp creationDate;


    public CurvePoint(int curveId, double term, double value) {
        super();
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public CurvePoint() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurveId() {
        return curveId;
    }

    public void setCurveId(int curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "CurvePoint{" +
                "id=" + id +
                ", curveId=" + curveId +
                ", asOfDate=" + asOfDate +
                ", term=" + term +
                ", value=" + value +
                ", creationDate=" + creationDate +
                '}';
    }
}
