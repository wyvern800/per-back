package com.example.demo.controllers;

import com.example.demo.models.Build;
import com.example.demo.models.Location;
import com.example.demo.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

/**
 * com.example.demo.controllers
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 02/01/2021 - 04:13
 * @project demo
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LocationController {
    @Autowired
    LocationService locationService;

    /**
     * Represents the {@code GET} request
     * @return The list of characters list
     */
    @GetMapping
    @RequestMapping(method = RequestMethod.GET, path = "/locations/all")
    public List<Location> get() {
        return locationService.listAll();
    }

    /**
     * Represents the {@code GET} request
     * @param buildId The build Id
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/locations/view/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getCharInfo(@PathVariable(value = "id") long buildId) {
        return locationService.findLocationsByBuildId(buildId);
    }

    /**
     * Represents the {@code PUT} request
     * @param location The new location from the request present on the request body (format: {@code JSON})
     * @return The new character to be added
     */
    @RequestMapping(value = "/locations/add", method =  RequestMethod.POST)
    public ResponseEntity<Location> post(@RequestBody Location location) {
        return locationService.create(location);
    }

    /**
     * Represents the {@code PUT} request
     * @param id The id to be edited
     * @param newLocation The new location from the request present on the request body (format: {@code JSON})
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/locations/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Location> put(@PathVariable(value = "id") long id, @RequestBody Location newLocation) {
        return locationService.update(id, newLocation);
    }

    /**
     * Represents the {@code PUT} request
     * @param buildId The build id we are adding the location to
     * @param locationId The location we are adding to the build
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/locations/add/{buildId}", method =  RequestMethod.POST)
    public ResponseEntity<Location> put2(@PathVariable(value = "buildId") long buildId, @RequestParam(value = "locationId") long locationId) {
        return locationService.addLocationToBuild(buildId, locationId);
    }

    /**
     * Represents the {@code DELETE} request
     * @param id The id to be deleted
     * @return The response | The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/locations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Location> delete(@PathVariable(value = "id") long id) {
        return locationService.delete(id);
    }
}
