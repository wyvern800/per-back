package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    private Integer displayOrder;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String slugMap;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private Integer topPos;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private Integer leftPos;


    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String description;

    /**
     * The build
     */

    @ManyToOne
    @JoinColumn(name = "build", referencedColumnName = "id")
    @JsonIgnore
    private Build build;


    public Location(@NotNull(message = "Name cannot be null") @NotBlank(message = "Name cannot be blank") String name, Integer order, @NotNull(message = "Name cannot be null") @NotBlank(message = "Name cannot be blank") String map, @NotNull(message = "Name cannot be null") @NotBlank(message = "Name cannot be blank") Integer topPos, @NotNull(message = "Name cannot be null") @NotBlank(message = "Name cannot be blank") Integer leftPos, @NotNull(message = "Name cannot be null") @NotBlank(message = "Name cannot be blank") String description, Build build) {
        this.name = name;
        this.displayOrder = order;
        this.slugMap = map;
        this.topPos = topPos;
        this.leftPos = leftPos;
        this.description = description;
        this.build = build;
    }
}



