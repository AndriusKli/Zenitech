package com.example.andriuskli.service;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OwnerServiceImp implements OwnerService {

    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImp(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getOwner(Long ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void createOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateOwner(Long ownerId, Owner owner) {
        Owner updatableOwner = getOwner(ownerId);
        updatableOwner.setName(owner.getName());
        updatableOwner.setSurname(owner.getSurname());
        updatableOwner.setOwnerships(owner.getOwnerships());
    }

    @Override
    public double calculateTax(Long ownerId) {
        Owner owner = getOwner(ownerId);
        return owner.getOwnerships().stream()
                .mapToDouble(ownership -> {
                    Building building = ownership.getBuilding();
                    return building.getMarketValue() * building.getPropertyType().getTaxRate() * ownership.getOwnershipPercentage();
                })
                .reduce(0, Double::sum);
    }
}
