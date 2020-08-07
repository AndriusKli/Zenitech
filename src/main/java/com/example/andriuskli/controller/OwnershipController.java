package com.example.andriuskli.controller;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.entity.Ownership;
import com.example.andriuskli.service.OwnershipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("api/ownership")
@RestController
@RequestMapping("api/ownership")
public class OwnershipController {

    private OwnershipService ownershipService;

    @Autowired
    public OwnershipController(OwnershipService ownershipService) {
        this.ownershipService = ownershipService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ownership> getOwnerships() {
        return ownershipService.getOwnerships();
    }

    @GetMapping(value = "/{ownershipId}")
    @ResponseStatus(HttpStatus.OK)
    public Ownership getOwnership(@PathVariable Long ownershipId) {
        return ownershipService.getOwnership(ownershipId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create an ownership", notes = "Creates and assigns an ownership (or ownerships) for the specified building.")
    public void createOwnership(@RequestBody Map<String, Double> ownersAndOwnershipPercentages, @RequestParam Long buildingId) {
        ownershipService.createOwnership(buildingId, ownersAndOwnershipPercentages);
    }

    @PostMapping(value = "/{ownershipId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateOwnership(@PathVariable Long ownershipId, @RequestBody Map<String, Double> ownersAndOwnershipPercentages) {
        ownershipService.updateOwnership(ownershipId, ownersAndOwnershipPercentages);
    }
}
