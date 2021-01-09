package com.example.demo.controllers;

import com.example.demo.models.Build;
import com.example.demo.services.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * com.example.demo.controllers
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 02/01/2021 - 04:13
 * @project demo
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BuildController {
    @Autowired
    BuildService buildService;

    /**
     * Represents the {@code GET} request
     * @return The list of characters list
     */
    @GetMapping
    @RequestMapping(method = RequestMethod.GET, path = "/builds")
    public List<Build> get() {
        return buildService.listAll();
    }

    /**
     * Represents the {@code GET} request
     * @param weaponId The weapon Id
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/builds/view/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Build>> getCharInfo(@PathVariable(value = "id") long weaponId) {
        return buildService.findBuildByWeaponId(weaponId);
    }

    /**
     * Represents the {@code GET} request
     * @param weaponId The weapon Id
     * @param buildId The build Id
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/builds/view/{id}/build", method = RequestMethod.GET)
    public ResponseEntity<Build> getCharInfo(@PathVariable(value = "id") long weaponId, @RequestParam(value="buildId") long buildId) {
        return buildService.findBuildByWeaponIdAndBuildId(weaponId, buildId);
    }

    /**
     * Represents the {@code PUT} request
     * @param build The new build from the request present on the request body (format: {@code JSON})
     * @return The new character to be added
     */
    @RequestMapping(value = "/builds", method =  RequestMethod.POST)
    public ResponseEntity<Build> post(@RequestBody Build build) {
        return buildService.create(build);
    }

    /**
     * Represents the {@code PUT} request
     * @param id The id to be edited
     * @param newBuild The new skill from the request present on the request body (format: {@code JSON})
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/builds/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Build> put(@PathVariable(value = "id") long id, @RequestBody Build newBuild) {
        return buildService.update(id, newBuild);
    }

    /**
     * Represents the {@code DELETE} request
     * @param id The id to be deleted
     * @return The response | The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/builds/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Build> delete(@PathVariable(value = "id") long id) {
        return buildService.delete(id);
    }
}
