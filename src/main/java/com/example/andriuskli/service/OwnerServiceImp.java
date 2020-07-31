package com.example.andriuskli.service;

import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OwnerServiceImp implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

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
    public double calculateTax(Long ownerId) {
        Owner owner = getOwner(ownerId);
        return owner.getBuildings().stream()
                .mapToDouble(building -> building.getMarketValue() * building.getPropertyType().getTaxRate())
                .reduce(0, Double::sum);
    }
}
