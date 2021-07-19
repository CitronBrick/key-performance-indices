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
	}


	// UserEntity findByEmail(String email);
	public UserEntity findByEmail(String email) {
		List<UserEntity> users = fus.getUsers();
		for(UserEntity ue : users) {
			if(ue.getEmail().equalsIgnoreCase(email)) {
				return ue;
			}
		}
		return null;
	}


}