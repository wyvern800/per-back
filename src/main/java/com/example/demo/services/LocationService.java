package com.example.demo.services;

import com.example.demo.models.Build;
import com.example.demo.models.Location;
import com.example.demo.repository.BuildRepository;
import com.example.demo.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * com.example.demo.services
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 02/01/2021 - 04:19
 * @project demo
 */
@Service
@Slf4j
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    BuildRepository buildRepository;
    /**
     * Creates a character
     * @param location The location that is being created
     * @return The character object and {@code HttpStatus.CREATED} as a response
     */
    public ResponseEntity<Location> create(Location location) {
        locationRepository.save(location);
        log.info("Location "+location.getName()+" was created successfully!");
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    /**
     * Updates the character
     * @param locationId The location id to be updated
     * @return {@code HttpStatus.OK} if character is updated | {@code HttpStatus.NOT_FOUND} if it isn't
     */
    public ResponseEntity<Location> update(long locationId, Location reqBodyLocation) {
        Optional<Location> optLocation = locationRepository.findById(locationId);

        if (optLocation.isPresent()){
            Location oldLocation = optLocation.get();
            oldLocation.setId(locationId);
            oldLocation.setName(reqBodyLocation.getName());
            oldLocation.setDescription(reqBodyLocation.getDescription());
            locationRepository.save(oldLocation);
            log.info("Location "+optLocation.get().getName()+" was updated successfully!");
            return new ResponseEntity<>(reqBodyLocation, HttpStatus.OK);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Location> addLocationToBuild(long buildId, long locationId) {
        Optional<Build> optBuild = buildRepository.findById(buildId);
        if (optBuild.isPresent()) {
            Optional<Location> optLocation = locationRepository.findById(locationId);
            if (optLocation.isPresent()) {
                Build theBuild = optBuild.get();
                Location theLocation = optLocation.get();

                List<Location> theNewLocationsList = new ArrayList<>(theBuild.getLocations());

                theLocation.setBuild(theBuild);

                theNewLocationsList.add(theLocation);

                theBuild.setLocations(theNewLocationsList);

                buildRepository.save(theBuild);


                log.info("Location " + theLocation.getName() + " was added to build " + optBuild.get().getName() + " successfully!");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.warn("Location was not found!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Build was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes the specified character
     * @param locationId The id of the locationId to be deleted
     */
    public ResponseEntity<Location> delete(long locationId) {
        Optional<Location> optLocation = locationRepository.findById(locationId);
        if(optLocation.isPresent()){
            locationRepository.delete(optLocation.get());
            log.info("Location "+optLocation.get().getName()+" was deleted successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.warn("Character was not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Saves the build
     * @param location The location to be saved
     */
    public void save(Location location) {
        locationRepository.save(location);
    }

    /**
     * Gets the build list
     * @return The build list
     */
    public List<Location> listAll() {
        return locationRepository.findAll();
    }

    public ResponseEntity<List<Location>> findLocationsByBuildId(long buildId) {
        Optional<Build> optBuild = buildRepository.findById(buildId);
        if(optBuild.isPresent()){
            List<Location> locations = optBuild.get().getLocations();

            // Order de display by order to show the real rotation
            locations.sort(Comparator.comparing(Location::getDisplayOrder));

            return new ResponseEntity<>(locations, HttpStatus.OK);
        } else {
            log.warn("Build was not found!");
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
