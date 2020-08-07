package com.example.andriuskli.service;

import com.example.andriuskli.entity.Ownership;

import java.util.List;
import java.util.Map;


public interface OwnershipService {
    List<Ownership> getOwnerships();
    Ownership getOwnership(Long ownershipId);
    List<Ownership> getOwnershipsByOwner(Long ownerId);
    void createOwnership(Long buildingId, Map<String, Double> ownersAndOwnershipPercentages);
    void updateOwnership(Long ownershipId, Map<String, Double> newOwnersAndOwnershipPercentages);
    void deleteOwnership(Long ownershipId);
}
