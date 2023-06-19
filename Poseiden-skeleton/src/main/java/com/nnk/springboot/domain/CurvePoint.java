package com.nnk.springboot.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curve_points")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "must not be null")
    private Integer curveId;
    private Timestamp asOfDate;
    @Digits(integer = 10, fraction = 2)
    private Double term;
    @Digits(integer = 10, fraction = 2)
    private Double value;
    private Timestamp creationDate;

    public CurvePoint() {
    }

    public CurvePoint(Integer id,Integer curveId, Double term, Double value) {
        this.id = id;
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

}
