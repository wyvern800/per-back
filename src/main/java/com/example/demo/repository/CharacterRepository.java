package com.example.demo.repository;

import com.example.demo.models.Character;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * com.example.demo.repository
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 01/01/2021 - 23:15
 * @project demo
 */
public interface CharacterRepository  extends CrudRepository<Character, Long> {
    List<Character> findAll();
}
