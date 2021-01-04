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
 * objects
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 17:45
 * @project demo
 */
@Entity
@Table(name = "skill")
@Data
@NoArgsConstructor
public class Skill {
    /**
     * The id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The character
     */
    @JoinColumn(name = "character", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Character character;

    /**
     * The skill name
     */
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
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
    private SkillKeys key;

    /**
     * The icon of the skill
     */
    private String skillIcon;

    /**
     * The gif for each skill
     */
    private String skillGif;

    /**
     * Constructor
     * @param name The name of the skill
     * @param energyPoint The energy points used with this skill | Use {@code null} if skill is EP free
     * @param cooldown The cooldown of this skill | Use {@code null} if skill doesn't have a cooldown
     * @param key The key bound to this skill
     * @param skillIcon The skill icon URL
     */
    public Skill(Character character, String name, Integer energyPoint, Double cooldown, SkillKeys key, String skillIcon, String skillGif) {
        this.character = character;
        this.name = name;
        this.energyPoint = energyPoint;
        this.cooldown = cooldown;
        this.key = key;
        this.skillIcon = skillIcon;
        this.skillGif = skillGif;
    }
}



