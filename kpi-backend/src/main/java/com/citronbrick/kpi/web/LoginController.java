package com.citronbrick.kpi.web;

import com.citronbrick.kpi.services.*;
import com.citronbrick.kpi.entities.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.*;
import lombok.*;


@RestController
@AllArgsConstructor
// @CrossOrigin({"http://localhost:3000"})
public class LoginController {

	private TokenService tokenService;
	
	@Getter
	@Setter
	private static class LoginCredentials {
		String email;
		String password;
	}


	@PostMapping(path={"/login"},consumes={"application/json"})
	// String login(@RequestBody Map<String,String> obj) {
	String login(@RequestBody LoginCredentials lc) {
		// System.out.println("ohohohohooooo " +obj);
		System.out.println("ohohohohooooo " + lc.email + ", "+lc.password);
		var res = tokenService.addUser(lc.email, lc.password);
		System.out.println(res);
		return res;
		// return null;
	}	


	@RequestMapping(value="/**",method=RequestMethod.OPTIONS)
	public String seeOptions() {
		return "abc";		
	}

} 