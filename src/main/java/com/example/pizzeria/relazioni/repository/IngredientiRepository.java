package com.example.pizzeria.relazioni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pizzeria.relazioni.model.Ingredienti;

public interface IngredientiRepository extends JpaRepository<Ingredienti, Integer>{

}
