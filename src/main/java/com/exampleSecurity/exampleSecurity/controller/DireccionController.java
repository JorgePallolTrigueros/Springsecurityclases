package com.exampleSecurity.exampleSecurity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.exampleSecurity.exampleSecurity.entity.Direccion;
import com.exampleSecurity.exampleSecurity.repository.DireccionRepository;

public class DireccionController {

	@Autowired
	private DireccionRepository DireccionRepository;
	@Autowired
	private DireccionRepository direccionRepository;


	
	@GetMapping({"/direccions"})
	public String findCars(Model model) {
		model.addAttribute("direccions", DireccionRepository.findAll());
		return "Direccion-list";
	}
	
	
	

	
	
	
	@GetMapping("/direccions/{id}/view")
	public String viewCar(@PathVariable Long id, Model model) {
		Optional<Direccion> carOpt = DireccionRepository.findById(id);
		if (!carOpt.isPresent()) {
			model.addAttribute("error", "ID car not found.");
			model.addAttribute("cars", DireccionRepository.findAll());
			return "car-list";
		}
		model.addAttribute("car", carOpt.get());
		return "car-view";
	}
	
	
	
	
	
	
	
	@GetMapping("/Direccions/new")
	public String newCar(Model model) {
		model.addAttribute("Direccion", new Direccion());
		model.addAttribute("manufacturer", direccionRepository.findAll());
		return "car-edit";
		
	}
	
	@GetMapping("/Direccions/{id}/edit")
	public String editCar(@PathVariable Long id, Model model) {
		model.addAttribute("Direccion", DireccionRepository.findById(id).get());
		model.addAttribute("manufacturer", direccionRepository.findAll());
		return "car-edit";
		
	}
	
	@PostMapping("/Direccions")
	public String saveDireccion(@ModelAttribute("Direccion") Direccion Direccion) {
		System.out.println(Direccion);
		DireccionRepository.save(Direccion);
		return "redirect:/Direccions";
	}
	
	@GetMapping("/Direccions/{id}/delete")
	public String deleteDireccion(@PathVariable Long id){
		DireccionRepository.deleteById(id);
		return "redirect:/Direccions";

	}
	
	
	
	
	@GetMapping("/Direccion/delete")
	public String deleteDireccions() {
		DireccionRepository.deleteAll();
		return "redirect:/Direccions";
	}
	

	
	

	

	
	
}
