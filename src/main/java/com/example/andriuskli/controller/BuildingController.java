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

@Api("api/building")
@RestController
@RequestMapping(value = "api/buildings")
public class BuildingController {

    private BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves all buildings", notes = "Retrieves all buildings stored in the database")
    public List<Building> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Create a building", notes = "Creates a building.")
    public void createBuilding(@ApiParam("Building data") @RequestBody Building building) {
        buildingService.createBuilding(building);
    }

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
