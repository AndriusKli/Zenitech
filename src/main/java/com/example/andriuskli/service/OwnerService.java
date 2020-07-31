package com.example.andriuskli.service;

import com.example.andriuskli.entity.Owner;

import java.util.List;

public interface OwnerService {
    Owner getOwner(Long ownerId);
    void createOwner(Owner owner);
    void deleteOwner(Long ownerId);
    List<Owner> getOwners();
    double calculateTax(Long ownerId);
}
