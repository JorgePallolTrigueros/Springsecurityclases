package com.exampleSecurity.exampleSecurity.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exampleSecurity.exampleSecurity.entity.Roll;
import com.exampleSecurity.exampleSecurity.entity.Usuario;
import com.exampleSecurity.exampleSecurity.repository.DireccionRepository;
import com.exampleSecurity.exampleSecurity.repository.RollRepository;
import com.exampleSecurity.exampleSecurity.repository.UsuarioRepository;
import com.exampleSecurity.exampleSecurity.service.UsuarioSrviceAPI;

public class RollController {

	@Autowired
	private RollRepository rollRepository;


	
	@GetMapping({"/roll"})
	public String findCars(Model model) {
		model.addAttribute("roll", rollRepository.findAll());
		return "roll-list";
	}
	
	
	

	
	
	
	@GetMapping("/roll/{id}/view")
	public String viewCar(@PathVariable Long id, Model model) {
		Optional<Roll> rollOpt = rollRepository.findById(id);
		if (!rollOpt.isPresent()) {
			model.addAttribute("error", "ID roll not found.");
			model.addAttribute("rolls", rollRepository.findAll());
			return "roll-list";
		}
		model.addAttribute("roll", rollOpt.get());
		return "roll-view";
	}
	
	
	
	
	
	
	
	@GetMapping("/rolls/new")
	public String newCar(Model model) {
		model.addAttribute("roll", new Roll());
		return "roll-edit";
		
	}
	
	@GetMapping("/rolls/{id}/edit")
	public String editCar(@PathVariable Long id, Model model) {
		model.addAttribute("roll", rollRepository.findById(id).get());
		return "roll-edit";
		
	}
	
	@PostMapping("/rolls")
	public String saveRoll(@ModelAttribute("roll") Roll roll) {
		System.out.println(roll);
		rollRepository.save(roll);
		return "redirect:/rolls";
	}
	
	@GetMapping("/rolls/{id}/delete")
	public String deleteRoll(@PathVariable Long id){
		rollRepository.deleteById(id);
		return "redirect:/rolls";

	}
	
	
	
	
	@GetMapping("/roll/delete")
	public String deleteRolls() {
		rollRepository.deleteAll();
		return "redirect:/rolls";
	}
	

	
	

	

	
	
}
