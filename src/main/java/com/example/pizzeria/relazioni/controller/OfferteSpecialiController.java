package com.example.pizzeria.relazioni.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzeria.relazioni.model.OfferteSpeciali;
import com.example.pizzeria.relazioni.model.Pizza;
import com.example.pizzeria.relazioni.repository.OfferteSpecialiRepository;
import com.example.pizzeria.relazioni.repository.PizzaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/offerte")
public class OfferteSpecialiController {

	private @Autowired OfferteSpecialiRepository offerteSpecialiRepository;
	
	private @Autowired PizzaRepository pizzaRepository;
	
	@GetMapping("/create")
	public String create(@RequestParam(name="pizzaId", required = true) Integer pizzaId, Model model) throws Exception {
		OfferteSpeciali os = new OfferteSpeciali();
		
		try {
			Pizza pizza = pizzaRepository.getReferenceById(pizzaId);
			os.setPizza(pizza);
		} catch (EntityNotFoundException e) {
			throw new Exception("Book not present. Id="+pizzaId);
		}
		
		
		model.addAttribute("offerte", os);
		
		return "offerte/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("offerte") OfferteSpeciali formOfferte, BindingResult bindingResult, Model model ) {
		if (bindingResult.hasErrors()) {
			return "offerte/create";
		}
		
		Pizza pizza = formOfferte.getPizza();
		offerteSpecialiRepository.save(formOfferte);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<OfferteSpeciali> off= offerteSpecialiRepository.findById(id);
		if(off.isEmpty()) {
			return "redirect:/pizze/error";
		}
		List<Pizza> elencoPizze;
		elencoPizze= pizzaRepository.findAll();
		model.addAttribute("offerte",off.get());
		model.addAttribute("pizze", elencoPizze);
		return "offerte/edit";
	}
	@PostMapping("/edit/{id}")
	public String update(@PathVariable("id") Integer id ,@Valid @ModelAttribute("offerta") OfferteSpeciali formOfferta, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "offerte/edit";
		}
		offerteSpecialiRepository.save(formOfferta);
		return "redirect:/pizze/"+ formOfferta.getPizza().getId();
	}
	
}
