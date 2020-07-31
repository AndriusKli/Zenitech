package com.example.andriuskli.service;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.repository.BuildingRepository;
import com.example.andriuskli.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BuildingServiceImp implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public List<Building> getBuildings(Long ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(NoSuchElementException::new).getBuildings();
    }

    @Override
    public void createBuilding(Building building, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(NoSuchElementException::new);
        building.setOwner(owner);
        buildingRepository.save(building);
    }

    @Override
    public void deleteBuilding(Long buildingId) {
        buildingRepository.deleteById(buildingId);
    }

    @Override
    public void updateBuilding(Long buildingId, Building building) {
        Building updatedBuilding = buildingRepository.findById(buildingId).orElseThrow(NoSuchElementException::new);
        updatedBuilding.setStreet(building.getStreet());
        updatedBuilding.setStreetNo(building.getStreetNo());
        updatedBuilding.setCity(building.getCity());
        updatedBuilding.setMarketValue(building.getMarketValue());
        updatedBuilding.setPropertyType(building.getPropertyType());
        updatedBuilding.setBuildingSize(building.getBuildingSize());
        updatedBuilding.setOwner(building.getOwner());
        buildingRepository.save(updatedBuilding);
    }
}
