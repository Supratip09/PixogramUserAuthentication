package com.ibm.fsd.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsd.training.model.Message;
import com.ibm.fsd.training.model.User;
import com.ibm.fsd.training.service.PixogramUserDetailsService;

import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/gateway")
public class PixogramAuthenticationController {
	
	@Autowired
	private PixogramUserDetailsService pixogramUserDetailsService;
	
	@GetMapping("/")
	public Mono<Message> login(){
		final Message message = new Message();
		message.setCode("SUC001");
		message.setDescription("User Login Successful!!");
		message.setPriority("Low");
		return Mono.just(message);
	}
	
	@PostMapping("/register")
	public Mono<Message> register(@RequestBody User user){
		pixogramUserDetailsService.registerNewUserAccount(user).subscribe();
		final Message message = new Message();
		message.setCode("SUC001");
		message.setDescription("User Registration Successful!!");
		message.setPriority("Low");
		return Mono.just(message);
	}

}
