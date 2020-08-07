package com.example.andriuskli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ownership {

    public Ownership(Building building, Owner owner, @DecimalMin("0.01") @DecimalMax("1.0") double ownershipPercentage) {
        this.building = building;
        this.owner = owner;
        this.ownershipPercentage = ownershipPercentage;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownershipId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildingId")
    @JsonIgnore
    private Building building;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    @JsonIgnore
    private Owner owner;

    @DecimalMin("0.01")
    @DecimalMax("1.0")
    private double ownershipPercentage;
}
