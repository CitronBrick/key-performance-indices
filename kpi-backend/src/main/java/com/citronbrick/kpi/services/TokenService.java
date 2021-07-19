package com.citronbrick.kpi.services;

import com.citronbrick.kpi.entities.*;
import com.citronbrick.kpi.repositories.*;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import javax.annotation.*;


@Service
public class TokenService {

	private Random rand = new Random();

	private Map<String,String> loggedInUsers = new TreeMap<String,String>(); 

	@Autowired
	private UserRepository userRepository;

	public TokenService() {}

	public TokenService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public String generateToken() {
		var length = 30 + rand.nextInt(5);
		System.out.println("tokenLength: " + length);
		var token = "";
		do {
			token = "";
			for(var i = 0; i < length; i++) {
				var useNum = (rand.nextInt(5) > 3);
				if(useNum) {
					token += rand.nextInt(9);
				} else { // use letter
					int c = rand.nextInt(25);
					var base = (rand.nextInt(1) == 0)?65:97; // capital or small
					token += (char)(base + c);
				} 
			}
		}while(loggedInUsers.containsKey(token));
		return token;
	}

	public String addUser(String email, String password) {
		UserEntity ue = userRepository.findByEmail(email);
		if (ue != null && ue.getPassword().equals(password)) {
			System.out.println("user is matching");
			var token = generateToken();
			System.out.println(token);
			loggedInUsers.put(token, email);
			return token;
		}
		return null;

	}

	

	public UserEntity findUser(String token) {
		String email = null;
		for(Map.Entry<String,String> me : loggedInUsers.entrySet()) {
			if(me.getKey() == token) {
				email = me.getValue();
				break;
			}
		}
		if(email == null) {
			return null;
		}
		return userRepository.findByEmail(email);
	}

	@PreDestroy 
	public void cleanUp() {
		loggedInUsers.clear();
	}

}