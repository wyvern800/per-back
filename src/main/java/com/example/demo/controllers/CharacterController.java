package com.example.demo.controllers;

import com.example.demo.models.Character;
import com.example.demo.models.Skill;
import com.example.demo.services.CharacterService;
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
public class CharacterController {
    @Autowired
    CharacterService characterService;

    /**
     * Represents the {@code GET} request
     * @return The list of characters list
     */
    @GetMapping
    @RequestMapping(method = RequestMethod.GET, path = "/characters")
    public List<Character> get() {
        return characterService.listAll();
    }

    /**
     * Represents the {@code GET} request
     * @param charId The id to be shown
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/characters/i", method = RequestMethod.GET)
    public ResponseEntity<Character> getCharInfo(@RequestParam long charId) {
        return characterService.findCharacterById(charId);
    }

    /**
     * Represents the {@code PUT} request
     * @param character The new character from the request present on the request body (format: {@code JSON})
     * @return The new character to be added
     */
    @RequestMapping(value = "/characters", method =  RequestMethod.POST)
    public ResponseEntity<Character> post(@RequestBody Character character) {
        return characterService.create(character);
    }

    /**
     * Represents the {@code PUT} request
     * @param id The id to be edited
     * @param newCharacter The new skill from the request present on the request body (format: {@code JSON})
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/characters/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Character> put(@PathVariable(value = "id") long id, @RequestBody Character newCharacter) {
        return characterService.update(id, newCharacter);
    }

    /**
     * Represents the {@code DELETE} request
     * @param id The id to be deleted
     * @return The response | The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/characters/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Character> delete(@PathVariable(value = "id") long id) {
        return characterService.delete(id);
    }
}
