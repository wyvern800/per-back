package com.example.demo.databases;

import com.example.demo.models.*;
import com.example.demo.repository.LocationRepository;
import enums.Locations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

/* com.example.demo
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 23:17
 * @project demo*/


@Component
public class LocationDatabaseLoader implements CommandLineRunner {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationDatabaseLoader(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(String... args) {
        Locations beco = Locations.BECO;
        Locations estande = Locations.ESTANDE_ARQUEIROS;
        Locations templo = Locations.TEMPLO;

        Location location = new Location(beco.getName(), 1, beco.getPos(), 13, 42, "Um lugar dharoka", null);
        Location location2 = new Location(estande.getName(),3, estande.getPos(), 48, 39, "Um lugar dharoka", null);
        Location location3 = new Location(templo.getName(),2, templo.getPos(), 27, 79, "Um lugar dharoka", null);

        List<Location> locsSet = new ArrayList<>(Arrays.asList(location, location2, location3));

        locsSet.forEach(this.locationRepository::save);
    }
}
