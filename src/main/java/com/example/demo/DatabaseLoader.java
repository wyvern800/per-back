/*
package com.example.demo;

import com.example.demo.models.Skill;
import com.example.demo.models.Stat;
import com.example.demo.models.Weapon;
import com.example.demo.models.WeaponSkill;
import com.example.demo.repository.CharacterRepository;
import enums.Difficulties;
import enums.SkillKeys;
import enums.Tiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.models.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


 */
/* com.example.demo
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 23:17
 * @project demo*//*



@Component
public class DatabaseLoader implements CommandLineRunner {
    private final CharacterRepository characterRepository;

    @Autowired
    public DatabaseLoader(CharacterRepository characterRepository) {
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
        Weapon adaga = new Weapon(theCharacter, "Adaga", Difficulties.NORMAL, 4, 1, 4, 1, 1, "", null);

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

        this.characterRepository.save(theCharacter);
    }
}
*/
