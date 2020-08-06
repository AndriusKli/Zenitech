package com.example.andriuskli.controller;

import com.example.andriuskli.entity.Building;
import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.entity.Ownership;
import com.example.andriuskli.service.OwnershipService;
import io.swagger.annotations.Api;
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

//    @GetMapping(value = )
//    @ResponseStatus(HttpStatus.OK)
//    public List<Ownership> getOwnershipsByOwner(@RequestParam Long ownerId) {
//        ownershipService.getOwnershipsByOwner(ownerId);
//    }
}
