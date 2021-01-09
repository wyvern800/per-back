package com.example.demo.services;

import com.example.demo.models.Build;
import com.example.demo.models.Weapon;
import com.example.demo.repository.BuildRepository;
import com.example.demo.repository.WeaponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.models.Character;
import utils.Slugfier;

import java.util.List;
import java.util.Optional;

/**
 * com.example.demo.services
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 02/01/2021 - 04:19
 * @project demo
 */
@Service
@Slf4j
public class BuildService {

    @Autowired
    BuildRepository buildRepository;

    @Autowired
    WeaponRepository weaponRepository;

    /**
     * Creates a character
     * @param build The character that is being created
     * @return The character object and {@code HttpStatus.CREATED} as a response
     */
    public ResponseEntity<Build> create(Build build) {
        build.setSlug(Slugfier.makeSlug(build.getName()));
        buildRepository.save(build);
        log.info("Build "+build.getName()+" was created successfully!");
        return new ResponseEntity<>(build, HttpStatus.CREATED);
    }

    /**
     * Updates the character
     * @param charId The character id to be updated
     * @return {@code HttpStatus.OK} if character is updated | {@code HttpStatus.NOT_FOUND} if it isn't
     */
    public ResponseEntity<Build> update(long charId, Build reqBodyBuild) {
        Optional<Build> optBuild = buildRepository.findById(charId);

        if (optBuild.isPresent()){
            Build oldBuild = optBuild.get();
            oldBuild.setId(charId);
            oldBuild.setName(reqBodyBuild.getName());
            oldBuild.setDescription(reqBodyBuild.getDescription());
            oldBuild.setLocations(reqBodyBuild.getLocations());
            oldBuild.setWeapon(reqBodyBuild.getWeapon());
            buildRepository.save(oldBuild);
            log.info("Character "+optBuild.get().getName()+" was updated successfully!");
            return new ResponseEntity<>(reqBodyBuild, HttpStatus.OK);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes the specified character
     * @param charId The id of the character to be deleted
     */
    public ResponseEntity<Build> delete(long charId) {
        Optional<Build> optCharacter = buildRepository.findById(charId);
        if(optCharacter.isPresent()){
            buildRepository.delete(optCharacter.get());
            log.info("Build "+optCharacter.get().getName()+" was deleted successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Saves the build
     * @param build The build to be saved
     */
    public void save(Build build) {
        buildRepository.save(build);
    }

    /**
     * Gets the build list
     * @return The build list
     */
    public List<Build> listAll() {
        return buildRepository.findAll();
    }

    public ResponseEntity<List<Build>> findBuildByWeaponId(long weaponId) {
        Optional<Weapon> optWeapon = weaponRepository.findById(weaponId);
        if(optWeapon.isPresent()){
            List<Build> builds = optWeapon.get().getBuilds();
            return new ResponseEntity<>(builds, HttpStatus.OK);
        } else {
            log.warn("Build was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Build> findBuildByWeaponIdAndBuildId(long weaponId, long buildId) {
        Optional<Weapon> optWeapon = weaponRepository.findById(weaponId);
        if(optWeapon.isPresent()){
            Optional<Build> optBuild = buildRepository.findById(buildId);
            if (optBuild.isPresent()) {
                Build theBuild = optBuild.get();
                return new ResponseEntity<>(theBuild, HttpStatus.OK);
            } else {
                log.warn("Build was not found!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Build was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets the character info based on slug
     * @param slug The slug
     * @return The character object
     */
    public ResponseEntity<Build> findCharacterBySlug(String slug) {
        List<Build> lists = buildRepository.findAll();
        Optional<Build> optChar = lists.stream().filter(p-> p.getSlug().equals(slug)).findFirst();
        if(optChar.isPresent()){
            return new ResponseEntity<>(optChar.get(), HttpStatus.OK);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Find all entries
     * @param id The id
     * @return The optional in case it finds any
     */
    public Optional<Build> findById(long id) {return buildRepository.findById(id);}

    /**
     * Checks if entry exists already exists
     * @param name The entry name
     * @return {@code True} if entry already exists | {@code False} if entry doesn't
     */
    private boolean entryExists(String name) {
        List<Build> allCharacters = buildRepository.findAll();
        Optional<Build> optChar = allCharacters.stream().filter(p-> p.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
        return optChar.isPresent();
    }
}
