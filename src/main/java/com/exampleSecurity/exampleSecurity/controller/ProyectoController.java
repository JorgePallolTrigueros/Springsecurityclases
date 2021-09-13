package com.exampleSecurity.exampleSecurity.controller;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.exampleSecurity.exampleSecurity.entity.Proyecto;
import com.exampleSecurity.exampleSecurity.repository.ProyectoRepository;


public class ProyectoController {

	
	@Autowired
	private ProyectoRepository proyectoRepository;

	
	
	@GetMapping({"/proyectos"})
	public String findCars(Model model) {
		model.addAttribute("proyectos", proyectoRepository.findAll());
		return "proyecto-list";
	}
	
	
	

	
	
	
	@GetMapping("/proyectos/{id}/view")
	public String viewCar(@PathVariable Long id, Model model) {
		Optional<Proyecto> proyectoOpt = proyectoRepository.findById(id);
		if (!proyectoOpt.isPresent()) {
			model.addAttribute("error", "ID proyecto not found.");
			model.addAttribute("proyectos", proyectoRepository.findAll());
			return "proyecto-list";
		}
		model.addAttribute("proyecto", proyectoOpt.get());
		return "proyecto-view";
	}
	
	
	
	
	
	
	
	@GetMapping("/proyectos/new")
	public String newCar(Model model) {
		model.addAttribute("proyecto", new Proyecto());
		model.addAttribute("manufacturer", proyectoRepository.findAll());
		return "proyecto-edit";
		
	}
	
	@GetMapping("/proyectos/{id}/edit")
	public String editCar(@PathVariable Long id, Model model) {
		model.addAttribute("proyecto", proyectoRepository.findById(id).get());
		model.addAttribute("manufacturer", proyectoRepository.findAll());
		return "proyecto-edit";
		
	}
	
	@PostMapping("/proyectos")
	public String saveProyecto(@ModelAttribute("proyecto") Proyecto proyecto) {
		System.out.println(proyecto);
		proyectoRepository.save(proyecto);
		return "redirect:/proyectos";
	}
	
	@GetMapping("/proyectos/{id}/delete")
	public String deleteProyecto(@PathVariable Long id){
		proyectoRepository.deleteById(id);
		return "redirect:/proyectos";

	}
	
	
	
	
	@GetMapping("/proyecto/delete")
	public String deleteProyectos() {
		proyectoRepository.deleteAll();
		return "redirect:/proyectos";
	}
	

	
	

	

}
