package com.example.greetingdemo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	//define greeting component variable without initialize
	
	private GreetingComponent gc;
	
	//define a constructor that will inject the GreeetingComponent object/instance
	//GreetingComponent will be injected/initialized in the constructor
	
	@Autowired
	public GreetingController(GreetingComponent gc) {
		this.gc = gc;
	}
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(),String.format(template, name));
	}
	
	//define another method to invoke method defined in greetingComponent
	
	@GetMapping("/testGreeting")
	public ResponseEntity<String> getMessage(){
		return ResponseEntity.ok(gc.getMessage());
		
	}

}
