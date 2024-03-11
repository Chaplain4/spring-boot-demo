package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "currencies")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_code")
    @JsonProperty(value = "NumCode")
    private Integer numCode;

    @Column(name = "char_code", nullable = false, unique = true)
    @JsonProperty(value = "CharCode")
    private String charCode;

    @JsonProperty(value = "Scale")
    @Column(name = "scale")
    private Integer scale;

    @JsonProperty(value = "Name")
    @Column(name = "name")
    private String name;

    @JsonProperty(value = "Rate")
    @Column(name = "rate")
    private Double rate;

    @Column(name = "daily_ts")
    private Timestamp dailyTs;

    @Column(name = "updated_ts")
    private Timestamp updatedTs;


}
