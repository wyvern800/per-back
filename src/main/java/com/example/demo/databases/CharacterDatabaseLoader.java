package com.example.demo.databases;

import com.example.demo.models.*;
import com.example.demo.models.Character;
import com.example.demo.repository.CharacterRepository;
import enums.Difficulties;
import enums.Locations;
import enums.SkillKeys;
import enums.Tiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/* com.example.demo
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 23:17
 * @project demo*/


@Component
public class CharacterDatabaseLoader implements CommandLineRunner {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterDatabaseLoader(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void run(String... args) {
        // Create the lists
        List<Weapon> weaponsList = new ArrayList<>();
        List<Skill> skillsList = new ArrayList<>();

        // Create the character
        Character theCharacter = new Character("Jackie", weaponsList, skillsList, null, null, null, null, Tiers.A);

        // Create the weapon to the character
        Weapon adaga = new Weapon(theCharacter, "Adaga", Difficulties.NORMAL, 4, 1, 4, 1, 1, "", null, null);

        // Set the weapon skill
        WeaponSkill theWeaponSkill = new WeaponSkill(adaga, "Disfarçar e Esfaquear", 0, 40.0, "https://i.imgur.com/UNrQRJQ.png");
        adaga.setWeaponSkill(theWeaponSkill);

        // Set the stats
        theCharacter.setStats(new Stat(theCharacter, 560, 430 , 37, 26));

        // Create the skills
        Skill theSkill = new Skill(theCharacter, "Cortar e Dilacerar", 50, 10.0, SkillKeys.Q, "https://i.imgur.com/guE79Ui.png", "#");

        // Add the skills to the skills List
        skillsList.addAll(Collections.singletonList(theSkill));

        // Add the weapons to the weapons list
        weaponsList.add(adaga);

        Locations beco = Locations.BECO;
        Locations estande = Locations.BECO;
        Locations templo = Locations.TEMPLO;

        //Location location = new Location(beco.getName(),beco.getPos(), 13, 42, "Um lugar dharoka", null);
        //Location location2 = new Location(estande.getName(),estande.getPos(), 48, 39, "Um lugar dharoka", null);
        //Location location3 = new Location(templo.getName(),templo.getPos(), 27, 79, "Um lugar dharoka", null);

        List<Location> locsSet = new ArrayList<>();

        //locsSet.addAll(Arrays.asList(location, location3));

        Build theBuild = new Build("The way to malta", "Uma build feita para dharoks", null, adaga);

        List<Build> builds = new ArrayList<>();

        //location.setBuild(theBuild);
        //location2.setBuild(theBuild);

        builds.add(theBuild);

        adaga.setBuilds(builds);

        List<Character> firstChar = characterRepository.findAll();

        if (firstChar.size() <= 0) {
            this.characterRepository.save(theCharacter);
        }
    }
}
