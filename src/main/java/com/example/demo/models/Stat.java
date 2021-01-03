package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

/**
 * objects
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 17:46
 * @project demo
 */
@Entity
@Table(name = "stat")
@Data
@NoArgsConstructor
public class Stat {
    @OneToOne()
    @JoinColumn(name = "character", referencedColumnName = "id")
    @JsonIgnore
    private Character character;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero(message = "vitalityPoints must be a positive value or zero")
    private Integer vitalityPoints;

    @PositiveOrZero(message = "energyPoints must be a positive value or zero")
    private Integer energyPoints;

    @PositiveOrZero(message = "attackPower must be a positive value or zero")
    private Integer attackPower;

    @PositiveOrZero(message = "defense must be a positive value or zero")
    private Integer defense;

    public Stat(Character character, Integer vitalityPoints, Integer energyPoints, Integer attackPower, Integer defense) {
        this.character = character;
        this.vitalityPoints = vitalityPoints;
        this.energyPoints = energyPoints;
        this.attackPower = attackPower;
        this.defense = defense;
    }
}
