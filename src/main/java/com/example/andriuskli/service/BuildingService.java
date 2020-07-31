package com.example.andriuskli.service;

import com.example.andriuskli.entity.Building;

import java.util.List;

public interface BuildingService {
    List<Building> getAllBuildings();
    Building getBuilding(Long buildingId);
    void createBuilding(Building building);
    void deleteBuilding(Long buildingId);
    void updateBuilding(Long buildingId, Building building);

}
