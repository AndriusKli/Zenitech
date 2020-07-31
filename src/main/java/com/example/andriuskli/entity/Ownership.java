package com.example.andriuskli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ownership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownershipId;

    private Long buildingId;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "ownership",
//            joinColumns = @JoinColumn(name = "ownerId"),
//            inverseJoinColumns = @JoinColumn(name = "ownershipId")
//    )
//    private List<Owner> owners;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    @JsonIgnore
    private Owner owner;

    @DecimalMin("0.01")
    @DecimalMax("1.0")
    private double ownershipPercentage;

//    public List<Owner> getOwners() {
//        return owners;
//    }
//
//    public void setOwners(List<Owner> owners) {
//        this.owners = owners;
//    }
}
