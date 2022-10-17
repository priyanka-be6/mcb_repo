package com.mcb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.login.model.User;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/login")
public class LoginController {
	
	
	@Autowired
	private LoginService loginSerivce;
	
	@PostMapping
	public String loadLogin(@RequestParam(name="username") String username,@RequestParam(name="password") String password) {
		String message = null;
		message = loginSerivce.loadLogin(username,password);
		return message;
	}
	
	@PostMapping("/create")
	public String createLogin(@RequestBody User users) {
		try {
			loginSerivce.createLogin(users);
			return "user created";
		}catch (DataIntegrityViolationException e) {
			return "user already exist";
		}
		
	}

}
