package com.example.demo.models;

import enums.SkillKeys;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * objects
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 17:45
 * @project demo
 */
@Entity
@Table(name = "tb_location")
@Data
@NoArgsConstructor
public class Location {
    /**
     * The id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    /**
     * The pos name
     */
    @NotNull(message = "Pos cannot be null")
    @NotBlank(message = "Pos cannot be blank")
    private String pos;

    /**
     * The top
     */
    @Column(name = "topPos")
    @PositiveOrZero(message = "top must be a positive value or zero")
    private Integer top;

    /**
     * The left
     */
    @Column(name = "leftPos")
    @PositiveOrZero(message = "left must be a positive value or zero")
    private Integer left;

    /**
     * The build
     */

    @ManyToOne
    @JoinColumn(name = "build", referencedColumnName = "id")
    @JsonIgnore
    private Build build;


    public Location(String name, String pos, Integer top, Integer left, Build build) {
        this.name = name;
        this.pos = pos;
        this.top = top;
        this.left = left;
        this.build = build;
    }
}



