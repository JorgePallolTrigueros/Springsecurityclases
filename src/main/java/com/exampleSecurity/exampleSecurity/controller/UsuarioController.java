package com.exampleSecurity.exampleSecurity.controller;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import com.exampleSecurity.exampleSecurity.entity.Usuario;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.exampleSecurity.exampleSecurity.repository.DireccionRepository;
import com.exampleSecurity.exampleSecurity.repository.UsuarioRepository;
import com.exampleSecurity.exampleSecurity.service.UsuarioSrviceAPI;




@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private DireccionRepository direccionRepository;
	@Autowired
	private UsuarioSrviceAPI usuarioServiceAPI;
	
	
	
	@GetMapping({"/users"})
	public String findCars(Model model) {
		model.addAttribute("users", usuarioRepository.findAll());
		return "usuario-list";
	}
	
	
	

	
	
	
	@GetMapping("/users/{id}/view")
	public String viewCar(@PathVariable Long id, Model model) {
		Optional<Usuario> carOpt = usuarioRepository.findById(id);
		if (!carOpt.isPresent()) {
			model.addAttribute("error", "ID car not found.");
			model.addAttribute("cars", usuarioRepository.findAll());
			return "car-list";
		}
		model.addAttribute("car", carOpt.get());
		return "car-view";
	}
	
	
	
	
	
	
	
	@GetMapping("/usuarios/new")
	public String newCar(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("manufacturer", direccionRepository.findAll());
		return "car-edit";
		
	}
	
	@GetMapping("/usuarios/{id}/edit")
	public String editCar(@PathVariable Long id, Model model) {
		model.addAttribute("usuario", usuarioRepository.findById(id).get());
		model.addAttribute("manufacturer", direccionRepository.findAll());
		return "car-edit";
		
	}
	
	@PostMapping("/usuarios")
	public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
		System.out.println(usuario);
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/{id}/delete")
	public String deleteUsuario(@PathVariable Long id){
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios";

	}
	
	
	
	
	@GetMapping("/usuario/delete")
	public String deleteUsuarios() {
		usuarioRepository.deleteAll();
		return "redirect:/usuarios";
	}
	

	
	

	
	@GetMapping(value = "/usuariopaginada")
	public String finnAll (@RequestParam Map <String, Object> params, Model model){
		int page = params.get("page") !=null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 2);
		Page<Usuario> pageUsuarios = usuarioServiceAPI.gettAll(pageRequest);
		
		int totalPage =  pageUsuarios.getTotalPages();
		if (totalPage >0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());		
			model.addAttribute("pages", pages);		
		}
		model.addAttribute("list", pageUsuarios.getContent());

		return "carpaginada";
	}
	
	
}