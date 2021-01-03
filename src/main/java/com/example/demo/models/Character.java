package com.example.demo.models;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * com.example.demo.models
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 17:37
 * @project demo
 */
@Entity
@Table(name = "character")
@Data
@NoArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<Weapon> weapons;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToOne(cascade = CascadeType.ALL)
    private Stat stats;

    public Character(String name, List<Weapon> weapons, List<Skill> skills, Stat stats) {
        this.name = name;
        this.weapons = weapons;
        this.skills = skills;
        this.stats = stats;
    }
}
