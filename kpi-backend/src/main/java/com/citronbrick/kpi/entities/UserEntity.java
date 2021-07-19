package com.citronbrick.kpi.entities;

import lombok.*;
import java.util.*;
import java.io.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 23L;


	@Id
	private String email;

	private String username;

	private String password;

	private boolean isAdmin;


	public UserEntity(String email, String password) {
		this(email, email, password, false);
	}

	public UserEntity(String email, String password, boolean admin) {
		this(email,email,password,admin);
	}



	@Override
	public boolean equals(Object o) {
		if(o==null) return false;
		if(o instanceof UserEntity) {
			UserEntity ue = (UserEntity)o;
			return email.equals(ue.email) && username.equals(ue.username) && password.equals(ue.password);
		}
		return false;
	}


	@Override
	public int hashCode() {
		return (int)(email.hashCode() + username.hashCode() + password.hashCode());
	}


}
