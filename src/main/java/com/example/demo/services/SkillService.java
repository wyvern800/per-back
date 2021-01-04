package com.example.demo.services;

import com.example.demo.models.Character;
import com.example.demo.models.Skill;
import com.example.demo.repository.CharacterRepository;
import com.example.demo.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * com.example.demo.services
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 02/01/2021 - 19:35
 * @project demo
 */
@Service
@Slf4j
public class SkillService {
    @Autowired
    SkillRepository skillRepository;

    @Autowired
    CharacterRepository characterRepository;

    /**
     * List all the skills on the database
     * @return All the skills
     */
    public List<Skill> listAll() {
        log.debug("skillService:listAll()");
        return skillRepository.findAll();
    }

    /**
     * Deletes the specified skill
     * @param charId The char id
     * @param skillId The skill to be deleted
     */
    public ResponseEntity<Skill> delete(long charId, long skillId) {
        Optional<Skill> optSkill = skillRepository.findById(skillId);
        Optional<Character> optChar = characterRepository.findById(charId);

        if (optChar.isPresent()) {
            if (optSkill.isPresent()) {
                skillRepository.delete(optSkill.get());
                log.info("Skill "+optSkill.get().getName()+" was deleted successfully!");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.warn("Skill not found!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Used to create a skill
     * @param charId The char id
     * @param skill The skill to be added
     * @return {@code HttpStatus.CREATED} if the skill is created | {@code HttpStatus.NOT_FOUND} if the skill is not found
     */
    public ResponseEntity<Skill> create(long charId, Skill skill) {
        Optional<Character> optChar = characterRepository.findById(charId);

        if (optChar.isPresent()) {
            // If entry doesn't exist
            if (entryExists(skill.getName())) {
                log.info("A character with name "+optChar.get().getName()+" already exists!");
                return new ResponseEntity<>(HttpStatus.IM_USED);
            }

            Skill skillToBeAdded = new Skill(optChar.get(), skill.getName(), skill.getEnergyPoint(), skill.getCooldown(), skill.getKey(), skill.getSkillIcon());
            skillRepository.save(skillToBeAdded);

            log.info("Skill "+skillToBeAdded.getName()+" was created successfully!");
            return new ResponseEntity<>(skill, HttpStatus.CREATED);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Used to update a skill
     * @param charId The char id
     * @param reqBodySkill The skill to be updated
     * @return {@code HttpStatus.CREATED} if the skill is created | {@code HttpStatus.NOT_FOUND} if the skill is not found
     */
    public ResponseEntity<Skill> update(long charId, long skillId, Skill reqBodySkill) {
        Optional<Character> optCharacter = characterRepository.findById(charId);

        if (optCharacter.isPresent()){
            Optional<Skill> optSkill = skillRepository.findById(skillId);

            if (optSkill.isPresent()) {
                // If entry doesn't exist
                if (entryExists(reqBodySkill.getName())) {
                    log.info("A skill with name "+optSkill.get().getName()+" already exists!");
                    return new ResponseEntity<>(HttpStatus.IM_USED);
                }

                Skill oldSkill = optSkill.get();
                oldSkill.setId(skillId);
                oldSkill.setName(reqBodySkill.getName());
                oldSkill.setCooldown(reqBodySkill.getCooldown());
                oldSkill.setEnergyPoint(reqBodySkill.getEnergyPoint());
                oldSkill.setKey(reqBodySkill.getKey());
                skillRepository.save(reqBodySkill);
                log.info("Skill was updated successfully.");
                return new ResponseEntity<>(reqBodySkill, HttpStatus.OK);
            } else {
                log.warn("Skill not found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Skill not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Find skills by char Id
     * @param id The char  id
     * @return The skills
     */
    public List<Skill> findSkillsByCharId(long id) {return skillRepository.findByCharacterId(id);}

    /**
     * Saves the skill
     * @param skill The skill to be saved
     */
    public void save(Skill skill) {
        skillRepository.save(skill);
    }

    /**
     * Checks if entry exists already exists
     * @param name The entry name
     * @return {@code True} if entry already exists | {@code False} if entry doesn't
     */
    private boolean entryExists(String name) {
        List<Skill> allSkills = skillRepository.findAll();
        Optional<Skill> optChar = allSkills.stream().filter(p-> p.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
        return optChar.isPresent();
    }
}
