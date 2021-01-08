package com.example.demo.models;

import enums.SkillKeys;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Slugfier;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Set;

/**
 * objects
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 17:45
 * @project demo
 */
@Entity
@Table(name = "build")
@Data
@NoArgsConstructor
public class Build {
    /**
     * The id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String slug;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String description;


    @OneToMany(mappedBy = "build", cascade = CascadeType.ALL)
    private List<Location> locations;


    @ManyToOne
    @JoinColumn(name = "weapon", referencedColumnName = "id")
    @JsonIgnore
    private Weapon weapon;


    public Build(@NotNull(message = "Name cannot be null") @NotBlank(message = "Name cannot be blank") String name, @NotNull(message = "Name cannot be null") @NotBlank(message = "Name cannot be blank") String description, List<Location> locations, Weapon weapon) {
        this.name = name;
        this.slug = Slugfier.makeSlug(name);
        this.description = description;
        this.locations = locations;
        this.weapon = weapon;
    }
    //private List<Items>

}



