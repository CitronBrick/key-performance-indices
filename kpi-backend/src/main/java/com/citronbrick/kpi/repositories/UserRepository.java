package com.citronbrick.kpi.repositories;

import com.citronbrick.kpi.entities.*;
import com.citronbrick.kpi.services.*;
import org.springframework.data.jpa.repository.*;


import org.springframework.stereotype.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;
import lombok.*;

@Repository
// public interface UserRepository extends JpaRepository<UserEntity, String> {
public class UserRepository {

	private FakeUserService fus;

	public UserRepository(FakeUserService fus) {
		this.fus = fus;
		System.out.println("UserRepository initialized");
	}


	// UserEntity findByEmail(String email);
	public UserEntity findByEmail(String email) {
		System.out.println("findByEmail called " + email);
		List<UserEntity> users = fus.getUsers();
		System.out.println(users);
		for(UserEntity ue : users) {
			System.out.println(ue);
			if(ue.getEmail().equalsIgnoreCase(email)) {
				return ue;
			}
		}
		return null;
	}


}