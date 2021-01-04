package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Difficulties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * objects
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 17:55
 * @project demo
 */
@Entity
@Table(name = "weapon")
@Data
@NoArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Character
     */
    @ManyToOne
    @JoinColumn(name = "character", referencedColumnName = "id")
    @JsonIgnore
    private Character character;

    /**
     * Weapon name
     */
    @NotNull(message = "Weapon name cannot be null")
    @NotBlank(message = "Weapon name cannot be blank")
    private String name;

    /**
     * Difficulty
     */
    private Difficulties difficulty;

    /**
     * Damage stat
     */
    @PositiveOrZero(message = "damage must be a positive value or zero")
    private Integer damage;

    /**
     * Support stat
     */
    @PositiveOrZero(message = "support must be a positive value or zero")
    private Integer support;

    /**
     * Movement stat
     */
    @PositiveOrZero(message = "movement must be a positive value or zero")
    private Integer movement;

    /**
     * Crowd control stat
     */
    @PositiveOrZero(message = "crowdControl must be a positive value or zero")
    private Integer crowdControl;

    /**
     * Defense stat
     */
    @PositiveOrZero(message = "defense must be a positive value or zero")
    private Integer defense;

    /**
     * Weapon icon
     */
    private String icon;

    /**
     * Represets the skill of the current weapon
     */
    @OneToOne(cascade = CascadeType.ALL)
    private WeaponSkill weaponSkill;

    public Weapon(Character character, String name, Difficulties difficulty, Integer damage, Integer support, Integer movement, Integer crowdControl, Integer defense, String icon, WeaponSkill weaponSkill) {
        this.character = character;
        this.name = name;
        this.difficulty = difficulty;
        this.damage = damage;
        this.support = support;
        this.movement = movement;
        this.crowdControl = crowdControl;
        this.defense = defense;
        this.icon = icon;
        this.weaponSkill = weaponSkill;
    }
}
