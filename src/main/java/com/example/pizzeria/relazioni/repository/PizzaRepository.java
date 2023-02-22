package com.example.pizzeria.relazioni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pizzeria.relazioni.model.Pizza;



public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

	public List<Pizza> findByNameLike(String keyword);
	
}
