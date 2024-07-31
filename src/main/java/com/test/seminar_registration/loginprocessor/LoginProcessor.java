package com.test.seminar_registration.loginprocessor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {
	
	private String username;
	private String password;
	
	public boolean login() {
		String username = this.getUsername();
		String password = this.getPassword();
		
		if ("Pozha".equals(username) && "Tes123Tes".equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setUsername (String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	
}
