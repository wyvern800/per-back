package com.example.demo.controllers;

import com.example.demo.models.Skill;
import com.example.demo.services.SkillService;
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
@RequestMapping(path = "/skills")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SkillController {
    @Autowired
    SkillService skillService;

    /**
     * List all skills from database
     * @return The skills list
     */
    @GetMapping
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<Skill> getAll() {
        return skillService.listAll();
    }

    /**
     * Represents the {@code GET} request of the skills list
     *
     * @return The list of all skills of a determined character
     */
    @GetMapping
    @RequestMapping(method = RequestMethod.GET, path = "/characters/{id}")
    public List<Skill> get(@PathVariable(value = "id") long charId) {
        return skillService.findSkillsByCharId(charId);
    }

    /**
     * Represents the {@code POST} request for the character skills
     * @param charId The id to be edited
     * @param newSkill The new skill from the request present on the request body (format: {@code JSON})
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/characters/{id}", method = RequestMethod.POST)
    public ResponseEntity<Skill> post(@PathVariable(value = "id") long charId, @RequestBody Skill newSkill) {
        return skillService.create(charId, newSkill);
    }

    /**
     * Represents the {@code PUT} request
     * @param charId The id to be edited
     * @param newSkill The new skill from the request present on the request body (format: {@code JSON})
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/characters/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Skill> put(@PathVariable(value = "id") long charId, @RequestParam long skillId, @RequestBody Skill newSkill) {
        return skillService.update(charId, skillId, newSkill);
    }

    /**
     * Represents the {@code DELETE} request for the character skills
     * @param charId The id to be deleted
     * @param skillId The id of the skill
     * @return The response | {@code HttpStatus.OK} if character is present & {@code HttpStatus.NOT_FOUND} if not present
     */
    @RequestMapping(value = "/characters/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Skill> delete(@PathVariable(value = "id") long charId, @RequestParam long skillId) {
        return skillService.delete(charId, skillId);
    }
}
