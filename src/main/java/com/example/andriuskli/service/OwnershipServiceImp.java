package com.example.andriuskli.service;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.entity.Ownership;
import com.example.andriuskli.entity.Transaction;
import com.example.andriuskli.repository.OwnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class OwnershipServiceImp implements OwnershipService {

    private OwnershipRepository ownershipRepository;
    private OwnerService ownerService;
    private BuildingService buildingService;
    private TransactionService transactionService;

    @Autowired
    public OwnershipServiceImp(OwnershipRepository ownershipRepository, OwnerService ownerService, BuildingService buildingService, TransactionService transactionService) {
        this.ownershipRepository = ownershipRepository;
        this.ownerService = ownerService;
        this.buildingService = buildingService;
        this.transactionService = transactionService;
    }

    @Override
    public List<Ownership> getOwnerships() {
        return ownershipRepository.findAll();
    }

    @Override
    public Ownership getOwnership(Long ownershipId) {
        return ownershipRepository.findById(ownershipId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Ownership> getOwnershipsByOwner(Long ownerId) {
        return ownerService.getOwner(ownerId).getOwnerships();
    }

    @Override
    @Transactional
    public void createOwnership(Long buildingId, Map<String, Double> ownersAndOwnershipPercentages) {
        validateMap(ownersAndOwnershipPercentages);
        Building building = buildingService.getBuilding(buildingId);
        Double totalOwnershipPercentage = calculateOwnershipPercentage(ownersAndOwnershipPercentages);

        if (totalOwnershipPercentage == 1.0) {
            createOwnerships(building, ownersAndOwnershipPercentages, null);
        } else {
            throw new IllegalArgumentException("Incorrect ownership percentages supplied.");
        }
    }

    @Override
    public void updateOwnership(Long ownershipId, Map<String, Double> newOwnersAndOwnershipPercentages) {
        validateMap(newOwnersAndOwnershipPercentages);
        Ownership updatableOwnership = getOwnership(ownershipId);
        Double updatableOwnershipPercentage = updatableOwnership.getOwnershipPercentage();
        Double totalNewOwnershipPercentage = calculateOwnershipPercentage(newOwnersAndOwnershipPercentages);

        if (updatableOwnershipPercentage - totalNewOwnershipPercentage > 0.0 && updatableOwnershipPercentage - totalNewOwnershipPercentage < 1.00) {
            createOwnerships(updatableOwnership.getBuilding(), newOwnersAndOwnershipPercentages, updatableOwnership.getOwner().getOwnerId());
        } else {
            throw new IllegalArgumentException("Incorrect ownership percentages supplied.");
        }

        if (updatableOwnershipPercentage - totalNewOwnershipPercentage == 0) {
            deleteOwnership(ownershipId);
        } else {
            updatableOwnership.setOwnershipPercentage(updatableOwnershipPercentage - totalNewOwnershipPercentage);
            ownershipRepository.save(updatableOwnership);
        }
    }

    @Override
    public void deleteOwnership(Long ownershipId) {
        ownershipRepository.deleteById(ownershipId);
    }

    private void createOwnerships(Building building, Map<String, Double> map, Long previousOwner) {
        for (String ownerString : map.keySet()) {
            try {
                Long ownerId = Long.parseLong(ownerString);
                Owner owner = ownerService.getOwner(ownerId);
                ownershipRepository.save(new Ownership(building, owner, map.get(ownerString)));
                transactionService.createTransaction(new Transaction(previousOwner, ownerId));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Erroneous ownerId provided: " + ownerString);
            }
        }
    }

    private void validateMap(Map<String, Double> map) {
        if (map.isEmpty()) {
            throw new IllegalArgumentException("Please supply a non-empty list of owners and ownership percentages.");
        }
    }

    private Double calculateOwnershipPercentage(Map<String, Double> map) {
        return map.values().stream().reduce(0.0, Double::sum);
    }
}
