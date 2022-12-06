package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private PizzaService pizzaService;
	
	
	@GetMapping("/")
	public String index(Model model) {
	
	List<Pizza> pizzas = pizzaService.findAll();
	model.addAttribute("pizzas", pizzas);
	
	return "index";
	}

	@GetMapping("/pizza/{id}")
	public String getPizza(@PathVariable("id") int id, Model model) {
		
		Optional <Pizza> optPizza = pizzaService.getPizzaById(id);
		if(optPizza.isEmpty()) {
			System.err.println("Pizza non presente con id: " + id);
		}
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		return "pizza";
		
	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		return "pizza-create";
	}
	
	@PostMapping ("/pizza/create")
	public String storePizza(@Valid @ModelAttribute("pizza")Pizza pizza) {
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}

	@GetMapping ("/pizza/update/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		
		Optional <Pizza> optPizza = pizzaService.getPizzaById(id);
		if(optPizza.isEmpty()) {
			System.err.println("Pizza non presente con id: " + id);
		}
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		return "pizza-update";
	}
	
	@PostMapping("/pizza/store")
	public String updatePizza(@Valid Pizza pizza) {
		
		pizzaService.save(pizza);		
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		Optional<Pizza> optPizza = pizzaService.getPizzaById(id);
		Pizza pizza = optPizza.get();
		
		pizzaService.deletePizzaById(pizza);
		
		return "redirect:/";
	}

}
