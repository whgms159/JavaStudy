package com.kosta.domain;

import com.kosta.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {

	private int id;
	private String username, name, password;
	private UserRole role;
	
	public User setUser() {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setName(name);
		user.setPassword(password);
		return user;
	}
	
	public static UserDTO setUserDTO(User u) {
		return UserDTO.builder()
			.id(u.getId())
			.username(u.getUsername())
			.name(u.getName())
			.role(u.getRole())
			.build();
	}
	
}
