package com.example.andriuskli;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.enums.PropertyType;
import com.example.andriuskli.repository.BuildingRepository;
import com.example.andriuskli.repository.OwnerRepository;
import com.example.andriuskli.service.BuildingServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BuildingServiceTest {

    @Mock
    BuildingRepository buildingRepository;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    BuildingServiceImp buildingServiceImp;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllBuildings() {
        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(2L, "Vilnius", "Ateities", "5", 250.0, 100000, PropertyType.INDUSTRIAL));
        when(buildingRepository.findAll()).thenReturn(buildings);
        List<Building> result = buildingServiceImp.getAllBuildings();
        assertEquals(1, result.size(), "Should retrieve a list of all buildings");
    }

//    @Test
//    void getOwnerBuildings() {
//        Owner owner = new Owner(1L, "John", "Smith", new ArrayList<>());
//        List<Building> buildings = new ArrayList<>();
//        buildings.add(new Building(2L, "Vilnius", "Ateities", "5", owner, 250.0, 100000, PropertyType.INDUSTRIAL));
//        buildings.add(new Building(3L, "Vilnius", "Ateities", "5", owner, 250.0, 35000, PropertyType.APARTMENT));
//        buildings.add(new Building(5L, "Kaunas", "Gatve", "5", owner, 15.0, 40000, PropertyType.HOUSE));
//        owner.setBuildings(buildings);
//        when(ownerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(owner));
//        List<Building> result = buildingServiceImp.getBuildings(1L);
//        assertEquals(3, result.size(), "Should return all buildings belonging to an owner");
//    }

//    @Test
//    void createsBuildings() {
//        Owner owner = new Owner(1L, "John", "Smith", new ArrayList<>());
//        List<Building> buildings = new ArrayList<>();
//        buildings.add(new Building(3L, "Vilnius", "Ateities", "5", owner, 250.0, 35000, PropertyType.APARTMENT));
//        when(ownerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(owner));
//        buildingServiceImp.createBuilding(new Building(2L, "Vilnius", "Ateities", "5", owner, 250.0, 100000, PropertyType.INDUSTRIAL), 1L);
//        verify(buildingRepository, times(1)).save(any());
//    }

    @Test
    void deletesBuildings() {
        buildingServiceImp.deleteBuilding(anyLong());
        verify(buildingRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void updatesBuildings() {
        Building building = new Building(3L, "Vilnius", "Ateities", "5", 250.0, 35000, PropertyType.APARTMENT);
        when(buildingRepository.findById(anyLong())).thenReturn(java.util.Optional.of(building));
        buildingServiceImp.updateBuilding(3L, new Building(3L, "Kaunas", "Gatve", "5", 15.0, 40000, PropertyType.HOUSE));
        verify(buildingRepository, times(1)).save(any());
    }
}
