package com.example.andriuskli.service;

import com.example.andriuskli.entity.Building;

import java.util.List;

public interface BuildingService {
    List<Building> getAllBuildings();
    List<Building> getBuildings(Long ownerId);
    void createBuilding(Building building, Long ownerId);
    void deleteBuilding(Long buildingId);
    void updateBuilding(Long buildingId, Building building);

}
