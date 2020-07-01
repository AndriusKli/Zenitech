package com.example.andriuskli;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.enums.PropertyType;
import com.example.andriuskli.repository.OwnerRepository;
import com.example.andriuskli.service.OwnerServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class OwnerServiceTests {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceImp ownerServiceImp;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getsOwners() {
        List<Owner> owners = new ArrayList<>();
        owners.add(new Owner(1L, "John", "Smith", new ArrayList<>()));
        owners.add(new Owner(2L, "John", "Doe", new ArrayList<>()));
        owners.add(new Owner(3L, "Joe", "Doe", new ArrayList<>()));
        when(ownerRepository.findAll()).thenReturn(owners);
        List<Owner> result = ownerServiceImp.getOwners();
        assertEquals(3, result.size(), "Should retrieve a list of owners");
    }

    @Test
    void getsSingleOwner() {
        when(ownerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Owner(1L, "John", "Smith", new ArrayList<>())));
        Owner result = ownerServiceImp.getOwner(1L);
        assertEquals("John", result.getName(), "Should retrieve a single owner");
    }


    @Test
    void createsOwner() {
        Owner owner = new Owner(1L, "John", "Smith", new ArrayList<>());
        ownerServiceImp.createOwner(owner);
        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    void deletesOwner() {
        ownerServiceImp.deleteOwner(1L);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void calculatesTax() {
        Owner owner = new Owner(1L, "John", "Smith", new ArrayList<>());
        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(2L, "Vilnius", "Ateities", "5", owner, 250.0, 100000, PropertyType.INDUSTRIAL));
        buildings.add(new Building(3L, "Vilnius", "Ateities", "5", owner, 250.0, 35000, PropertyType.APARTMENT));
        buildings.add(new Building(5L, "Kaunas", "Gatve", "5", owner, 15.0, 40000, PropertyType.HOUSE));
        owner.setBuildings(buildings);
        when(ownerRepository.findById(1L)).thenReturn(java.util.Optional.of(owner));
        double result = ownerServiceImp.calculateTax(1L);
        assertEquals(3055.0, result, "should calculate the tax of different types or properties correctly");
    }

}
