package com.example.demo.models;

import enums.SkillKeys;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * objects.character
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 02/01/2021 - 02:51
 * @project demo
 */
@Entity
@Table(name = "weaponSkill")
@Data
@NoArgsConstructor
public class WeaponSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The weaponSkill constructor
     */
    @OneToOne
    @JsonIgnore
    private Weapon weapon;

    /**
     * The skill name
     */
    @NotNull(message = "Weapon skill name cannot be null")
    @NotBlank(message = "Weapon skill name cannot be blank")
    private String name;

    /**
     * The energy point that is needed to process the skill
     */
    @PositiveOrZero(message = "energyPoint must be a positive value or zero")
    private Integer energyPoint;

    /**
     * The skill cooldown
     */
    @PositiveOrZero(message = "cooldown must be a positive value or zero")
    private Double cooldown;

    /**
     * The key used with the kill
     */
    private SkillKeys key = SkillKeys.D;

    /**
     * The icon of the skill
     */
    private String weaponSkillIcon;

    public WeaponSkill(Weapon weapon, String name, Integer energyPoint, Double cooldown, String weaponSkillIcon) {
        this.weapon = weapon;
        this.name = name;
        this.energyPoint = energyPoint;
        this.cooldown = cooldown;
        this.weaponSkillIcon = weaponSkillIcon;
    }
}