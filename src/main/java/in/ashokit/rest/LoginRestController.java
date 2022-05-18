package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.LoginForm;
import in.ashokit.service.UserManagement;

@RestController
public class LoginRestController {

	@Autowired
	private UserManagement service;

	@PostMapping("/login")
	public String login(LoginForm loginForm) {
		return service.login(loginForm);
	}
}