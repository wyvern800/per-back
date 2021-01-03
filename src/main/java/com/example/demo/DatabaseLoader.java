package com.example.demo;

import com.example.demo.models.Skill;
import com.example.demo.models.Stat;
import com.example.demo.models.Weapon;
import com.example.demo.models.WeaponSkill;
import com.example.demo.repository.CharacterRepository;
import enums.Difficulty;
import enums.SkillKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.models.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * com.example.demo
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 23:17
 * @project demo
 */
@Component
public class DatabaseLoader implements CommandLineRunner {
    private final CharacterRepository characterRepository;

    @Autowired
    public DatabaseLoader(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void run(String... args) {
        List<Weapon> weaponList = new ArrayList<>();
        List<Skill> theSkills = new ArrayList<>();

        Character theCharacter = new Character("Jackie", weaponList, theSkills, null);

        Weapon theWeapon = new Weapon(theCharacter, "Arroba", Difficulty.EASY, 10, 0, 0, 0, 0, "", null);

        WeaponSkill theWeaponSkill = new WeaponSkill(theWeapon, "Fakinha", 10, 10.0, "");
        theWeapon.setWeaponSkill(theWeaponSkill);

        theCharacter.setStats(new Stat(theCharacter, 10, 10 , 10, 10));

        Skill theSkill = new Skill(theCharacter, "Fogo", 10, 10.0, SkillKey.Q, "");
        Skill theSkill2 = new Skill(theCharacter, "Barrigada", 10, 10.0, SkillKey.Q, "");

        theSkills.addAll(Arrays.asList(theSkill, theSkill2));

        weaponList.add(theWeapon);

        this.characterRepository.save(theCharacter);
    }
}
