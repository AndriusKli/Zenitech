package com.example.andriuskli.service;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.repository.BuildingRepository;
import com.example.andriuskli.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BuildingServiceImp implements BuildingService {

    private BuildingRepository buildingRepository;

    @Autowired
    public BuildingServiceImp(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public Building getBuilding(Long buildingId) {
        return buildingRepository.findById(buildingId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void createBuilding(Building building) {
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
        updatedBuilding.setCity(building.getCity());
        updatedBuilding.setMarketValue(building.getMarketValue());
        updatedBuilding.setPropertyType(building.getPropertyType());
        updatedBuilding.setBuildingSize(building.getBuildingSize());
        buildingRepository.save(updatedBuilding);
    }
}
