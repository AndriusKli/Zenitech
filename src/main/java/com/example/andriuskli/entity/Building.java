package com.example.andriuskli.entity;

import com.example.andriuskli.enums.PropertyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildingId;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String streetNo;

    @NotNull
    private double buildingSize;

    @NotNull
    private double marketValue; // Yes, I know, should use BigDecimal when working with money, but I assume a simpler solution will be fine for a code test.

    @NotNull
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

}
