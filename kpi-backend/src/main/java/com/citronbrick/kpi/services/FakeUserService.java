package com.citronbrick.kpi.services;

import java.util.*;
import com.citronbrick.kpi.entities.*;
import com.citronbrick.kpi.repositories.*;
import java.io.*;
import javax.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import lombok.*;
import org.springframework.core.io.*;

@Service
@AllArgsConstructor
public class FakeUserService {

	// private UserRepository userRepository;

	@Getter
	private List<UserEntity> users;

	@PostConstruct
	public void loadFakeUsers() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("users.txt").getInputStream()))) {

			for(var line = br.readLine(); line != null; line=br.readLine()) {
				var admin = line.toLowerCase().startsWith("s");
				var ue = new UserEntity(line.trim().toLowerCase()+"@me.com","password", admin);
				users.add(ue);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}