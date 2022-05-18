package in.ashokit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.UserForm;
import in.ashokit.service.UserManagement;

@RestController
public class RegistrationRestController {

	@Autowired
	private UserManagement service;

	@GetMapping("/email/{email}")
	public String checkEmailId(@PathVariable("email") String email) {
		return service.checkEmail(email);
	}

	@GetMapping("/countries")
	public Map<Integer, String> loadCountries() {
		return service.loadCountries();
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> loadStates(@PathVariable("countryId") int countryId) {
		return service.loadStates(countryId);
	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> loadCities(@PathVariable("stateId") int stateId) {
		return service.loadCities(stateId);
	}

	@PostMapping("/registration")
	public String registerUser(UserForm userForm) {
		return service.registerUser(userForm);
	}
}
