package com.example.demo.controllers;

import com.example.demo.models.Weapon;
import com.example.demo.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * com.example.demo.controllers
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 02/01/2021 - 19:38
 * @project demo
 */
@RestController
@RequestMapping(path = "/weapons")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeaponController {
    @Autowired
    WeaponService weaponService;

    /**
     * List all weapons from database
     * @return The weapons list
     */
    @GetMapping
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<Weapon> getAll() {
        return weaponService.listAll();
    }

    /**
     * Represents the {@code GET} request of the weapons list
     *
     * @return The list of all weapons of a determined character
     */
    @GetMapping
    @RequestMapping(method = RequestMethod.GET, path = "/weapons/{id}")
    public List<Weapon> get(@PathVariable(value = "id") long charId) {
        return weaponService.findWeaponsByCharId(charId);
    }

    /**
     * Represents the {@code POST} request for the character weapons
     * @param charId The id to be edited
     * @param newWeapon The new weapon from the request present on the request body (format: {@code JSON})
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/characters/{id}", method = RequestMethod.POST)
    public ResponseEntity<Weapon> post(@PathVariable(value = "id") long charId, @RequestBody Weapon newWeapon) {
        return weaponService.create(charId, newWeapon);
    }

    /**
     * Represents the {@code PUT} request
     * @param charId The id to be edited
     * @param newWeapon The new weapon from the request present on the request body (format: {@code JSON})
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/weapons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Weapon> put(@PathVariable(value = "id") long charId, @RequestParam long weaponId, @RequestBody Weapon newWeapon) {
        return weaponService.update(charId, weaponId, newWeapon);
    }

    /**
     * Represents the {@code DELETE} request for the character weapons
     * @param charId The id to be deleted
     * @param weaponId The id of the weapon
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/characters/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Weapon> delete(@PathVariable(value = "id") long charId, @RequestParam long weaponId) {
        return weaponService.delete(charId, weaponId);
    }
}
