package com.example.demo.services;

import com.example.demo.repository.CharacterRepository;
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
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    /**
     * Creates a character
     * @param character The character that is being created
     * @return The character object and {@code HttpStatus.CREATED} as a response
     */
    public ResponseEntity<Character> create(Character character) {
        // If entry doesn't exist
        if (entryExists(character.getName())) {
            log.info("A character with name "+character.getName()+" already exists!");
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
        character.setSlug(Slugfier.makeSlug(character.getName()));
        characterRepository.save(character);
        log.info("Character "+character.getName()+" was created successfully!");
        return new ResponseEntity<>(character, HttpStatus.CREATED);
    }

    /**
     * Updates the character
     * @param charId The character id to be updated
     * @return {@code HttpStatus.OK} if character is updated | {@code HttpStatus.NOT_FOUND} if it isn't
     */
    public ResponseEntity<Character> update(long charId, Character reqBodyCharacter) {
        Optional<Character> optCharacter = characterRepository.findById(charId);

        if (optCharacter.isPresent()){
            Character oldCharacter = optCharacter.get();
            oldCharacter.setId(charId);
            oldCharacter.setName(reqBodyCharacter.getName());
            //oldCharacter.setSlug(reqBodyCharacter.getSlug());
            oldCharacter.setSkills(reqBodyCharacter.getSkills());
            oldCharacter.setWeapons(reqBodyCharacter.getWeapons());
            oldCharacter.setStats(reqBodyCharacter.getStats());
            oldCharacter.setCharacterIcon(reqBodyCharacter.getCharacterIcon());
            oldCharacter.setCharacterLargeIcon(reqBodyCharacter.getCharacterLargeIcon());
            oldCharacter.setLobbyVideo(reqBodyCharacter.getLobbyVideo());
            oldCharacter.setCharacterTier(reqBodyCharacter.getCharacterTier());
            characterRepository.save(oldCharacter);
            log.info("Character "+optCharacter.get().getName()+" was updated successfully!");
            return new ResponseEntity<>(reqBodyCharacter, HttpStatus.OK);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes the specified character
     * @param charId The id of the character to be deleted
     */
    public ResponseEntity<Character> delete(long charId) {
        Optional<Character> optCharacter = characterRepository.findById(charId);
        if(optCharacter.isPresent()){
            characterRepository.delete(optCharacter.get());
            log.info("Character "+optCharacter.get().getName()+" was deleted successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Saves the character
     * @param character The character to be saved
     */
    public void save(Character character) {
        characterRepository.save(character);
    }

    /**
     * Gets the characters list
     * @return The characters list
     */
    public List<Character> listAll() {
        return characterRepository.findAll();
    }

    /**
     * Gets the character info absed on id
     * @param charId The character id
     * @return The character id
     */
    public ResponseEntity<Character> findCharacterById(long charId) {
        Optional<Character> optCharacter = characterRepository.findById(charId);
        if(optCharacter.isPresent()){
            return new ResponseEntity<>(optCharacter.get(), HttpStatus.OK);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets the character info based on slug
     * @param slug The slug
     * @return The character object
     */
    public ResponseEntity<Character> findCharacterBySlug(String slug) {
        List<Character> lists = characterRepository.findAll();
        Optional<Character> optChar = lists.stream().filter(p-> p.getSlug().equals(slug)).findFirst();
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
    public Optional<Character> findById(long id) {return characterRepository.findById(id);}

    /**
     * Checks if entry exists already exists
     * @param name The entry name
     * @return {@code True} if entry already exists | {@code False} if entry doesn't
     */
    private boolean entryExists(String name) {
        List<Character> allCharacters = characterRepository.findAll();
        Optional<Character> optChar = allCharacters.stream().filter(p-> p.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
        return optChar.isPresent();
    }
}
