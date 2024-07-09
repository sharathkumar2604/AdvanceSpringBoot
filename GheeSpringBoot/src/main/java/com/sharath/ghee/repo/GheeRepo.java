package com.sharath.ghee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharath.ghee.entity.GheeEntity;

public interface GheeRepo extends JpaRepository<GheeEntity,Integer> {

	List<GheeEntity> findByNameIgnoreCase(String name);

}
