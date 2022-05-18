package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.UnlockForm;
import in.ashokit.service.UserManagement;

@RestController
public class UnlockAccountRestController {

	@Autowired
	private UserManagement service;

	@PostMapping("/unlock")
	public String unlockAccount(UnlockForm unlockForm) {
		return service.unlockAccount(unlockForm);
	}
}
