package org.generation.italy.demo;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Pizza p1 = new Pizza("Margherita", "Pomodoro, mozzarella, basilico",5);
		Pizza p2 = new Pizza("Marinara", "Pomodoro, olio, origano",3);		
		Pizza p3 = new Pizza("Capricciosa", "Pomodoro, mozzarella, funghi",6);		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		
				
		List <Pizza> pizzas = pizzaService.findAll();
		System.out.println(pizzas);
	}

}
