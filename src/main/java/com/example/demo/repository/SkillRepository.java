package com.example.demo.repository;

import com.example.demo.models.Character;
import com.example.demo.models.Skill;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * com.example.demo.repository
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 02/01/2021 - 19:33
 * @project demo
 */
public interface SkillRepository  extends CrudRepository<Skill, Long> {
    List<Skill> findAll();

    List<Skill> findByCharacterId(long idChar);
}