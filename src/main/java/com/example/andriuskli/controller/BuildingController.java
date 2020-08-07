package com.example.andriuskli.controller;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.service.BuildingService;
import com.example.andriuskli.service.OwnershipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("api/building")
@RestController
@RequestMapping(value = "api/buildings")
public class BuildingController {

    private BuildingService buildingService;
    private OwnershipService ownershipService;

    @Autowired
    public BuildingController(BuildingService buildingService, OwnershipService ownershipService) {
        this.buildingService = buildingService;
        this.ownershipService = ownershipService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves all buildings", notes = "Retrieves all buildings stored in the database")
    public List<Building> getAllBuildings() {
        return buildingService.getAllBuildings();
    }


//    @GetMapping(value = "/{ownerId}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Retrieves all buildings of a specific owner", notes = "Retrieves all buildings belonging to a specified owner")
//    public List<Building> getBuildings(@ApiParam(value = "The id of the owner") @PathVariable Long ownerId) {
//        return buildingService.getBuildings(ownerId);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Create a building", notes = "Creates a building.")
    public void createBuilding(@ApiParam("Building data") @RequestBody Building building) {
        buildingService.createBuilding(building);
    }

    // TODO move this out to the ownership controller?
//    @PostMapping(value = "{buildingId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation(value = "Create an ownership", notes = "Creates and assigns an ownership (or ownerships) for the specified building.")
//    public void createOwnership(@RequestBody Map<String, Double> ownersAndOwnershipPercentages, @PathVariable Long buildingId) {
//        ownershipService.createOwnership(buildingId, ownersAndOwnershipPercentages);
//    }


    @DeleteMapping(value = "/{buildingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a building", notes = "Deletes the specified building.")
    public void deleteBuilding(@ApiParam("The id of the to-be-deleted building") @PathVariable Long buildingId) {
        buildingService.deleteBuilding(buildingId);
    }

    @PutMapping(value = "/{buildingId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Updates a building", notes = "Updates a building's data.")
    public void updateBuilding(@ApiParam("The id of the to-be-updated building") @PathVariable Long buildingId,
                               @ApiParam("Update data") @RequestBody Building building) {
        buildingService.updateBuilding(buildingId, building);
    }
}
