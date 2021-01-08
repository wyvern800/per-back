package com.example.demo.services;

import com.example.demo.models.Character;
import com.example.demo.models.Weapon;
import com.example.demo.repository.CharacterRepository;
import com.example.demo.repository.WeaponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
public class WeaponService {
    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    CharacterRepository characterRepository;

    /**
     * List all the skills on the database
     * @return All the skills
     */
    public List<Weapon> listAll() {
        return weaponRepository.findAll();
    }

    /**
     * Used to create a skill
     * @param charId The char id
     * @param reqBodyWeapon The weapon to be added
     * @return {@code HttpStatus.CREATED} if the skill is created | {@code HttpStatus.NOT_FOUND} if the skill is not found
     */
    public ResponseEntity<Weapon> create(long charId, Weapon reqBodyWeapon) {
        Optional<Character> optChar = characterRepository.findById(charId);

        if (optChar.isPresent()) {
            Character theCharacter = optChar.get();
            Weapon weaponToBeAdded = new Weapon(theCharacter, reqBodyWeapon.getName(),
                    reqBodyWeapon.getDifficulty(), reqBodyWeapon.getDamage(), reqBodyWeapon.getSupport(), reqBodyWeapon.getMovement(),
                    reqBodyWeapon.getCrowdControl(), reqBodyWeapon.getDefense(), reqBodyWeapon.getIcon(), reqBodyWeapon.getWeaponSkill(), reqBodyWeapon.getBuilds());
            weaponRepository.save(weaponToBeAdded);
            log.info("Weapon "+optChar.get().getName()+" was created successfully!");
            return new ResponseEntity<>(reqBodyWeapon, HttpStatus.CREATED);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Used to update a skill
     * @param charId The char id
     * @param reqBodyWeapon The skill to be updated
     * @return {@code HttpStatus.CREATED} if the skill is created | {@code HttpStatus.NOT_FOUND} if the skill is not found
     */
    public ResponseEntity<Weapon> update(long charId, long weaponId, Weapon reqBodyWeapon) {
        Optional<Character> oldCharacter = characterRepository.findById(charId);

        if (oldCharacter.isPresent()){
            Optional<Weapon> optWeapon = weaponRepository.findById(weaponId);
            if (optWeapon.isPresent()) {
                Weapon oldWeapon = optWeapon.get();
                oldWeapon.setId(weaponId);
                oldWeapon.setName(reqBodyWeapon.getName());
                oldWeapon.setDifficulty(reqBodyWeapon.getDifficulty());
                oldWeapon.setDamage(reqBodyWeapon.getDamage());
                oldWeapon.setSupport(reqBodyWeapon.getSupport());
                oldWeapon.setMovement(reqBodyWeapon.getMovement());
                oldWeapon.setCrowdControl(reqBodyWeapon.getCrowdControl());
                oldWeapon.setDefense(reqBodyWeapon.getDefense());
                oldWeapon.setIcon(reqBodyWeapon.getIcon());
                oldWeapon.setWeaponSkill(reqBodyWeapon.getWeaponSkill());
                oldWeapon.setBuilds(reqBodyWeapon.getBuilds());
                weaponRepository.save(oldWeapon);
                log.info("Weapon "+optWeapon.get().getName()+" was updated successfully!");
                return new ResponseEntity<>(reqBodyWeapon, HttpStatus.OK);
            } else {
                log.warn("Weapon was not found!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes the specified skill
     * @param charId The char id
     * @param weaponId The weapon id to be deleted
     */
    public ResponseEntity<Weapon> delete(long charId, long weaponId) {
        Optional<Weapon> optWeapon = weaponRepository.findById(weaponId);
        Optional<Character> optChar = characterRepository.findById(charId);

        if (optChar.isPresent()) {
            if (optWeapon.isPresent()) {
                weaponRepository.delete(optWeapon.get());
                log.info("Weapon "+optChar.get().getName()+" was deleted successfully!");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.warn("Weapon was not found!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Find skills by char Id
     * @param id The char  id
     * @return The skills
     */
    public List<Weapon> findWeaponsByCharId(long id) {return weaponRepository.findByCharacterId(id);}

    /**
     * Saves the skill
     * @param weapon The weapon to be saved
     */
    public void save(Weapon weapon) {
        weaponRepository.save(weapon);
    }

    /**
     * Checks if entry exists already exists
     * @param name The entry name
     * @return {@code True} if entry already exists | {@code False} if entry doesn't
     */
    private boolean entryExists(String name) {
        List<Weapon> allSkills = weaponRepository.findAll();
        Optional<Weapon> optChar = allSkills.stream().filter(p-> p.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
        return optChar.isPresent();
    }
}
