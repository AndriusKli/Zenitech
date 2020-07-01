package com.example.andriuskli.controller;

import com.example.andriuskli.entity.Owner;
import com.example.andriuskli.service.OwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "api/owner")
@RestController
@RequestMapping(value = "api/owner")
public class OwnerController {

    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves owners", notes = "Retrieves all owners.")
    public List<Owner> getOwners() {
        return ownerService.getOwners();
    }

    @GetMapping(value = "/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve an owner", notes = "Retrieves the specified owner's data.")
    public Owner getOwner(@ApiParam(value = "The id of the owner being retrieved.") @PathVariable Long ownerId) {
        return ownerService.getOwner(ownerId);
    }

    @GetMapping(value = "/tax/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Calculates tax on buildings.", notes = "Calculates the total tax owed for all building in the owners possession.")
    public double calculateTax(@ApiParam(value = "The owner for whom the tax will be calculated") @PathVariable Long ownerId) {
        return ownerService.calculateTax(ownerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new owner", notes = "Creates a new owner than can be assigned ownership of buildings.")
    public void createOwner(@ApiParam(value = "Name and surname of the to-be-created owner.") @Valid @RequestBody Owner owner) {
        ownerService.createOwner(owner);
    }

    @DeleteMapping(value = "{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an owner", notes = "Deletes a specified owner. Note that associated buildings are deleted as well.")
    public void deleteOwner(@ApiParam(value = "Id of the to-be-deleted owner.") @PathVariable Long ownerId) {
        ownerService.deleteOwner(ownerId);

    }

}
