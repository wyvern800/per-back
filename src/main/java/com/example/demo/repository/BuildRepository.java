package com.example.demo.repository;

import com.example.demo.models.Build;
import com.example.demo.models.Skill;
import com.example.demo.models.Weapon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * com.example.demo.repository
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 03/01/2021 - 15:48
 * @project demo
 */
public interface BuildRepository  extends CrudRepository<Build, Long> {
    List<Build> findAll();
}