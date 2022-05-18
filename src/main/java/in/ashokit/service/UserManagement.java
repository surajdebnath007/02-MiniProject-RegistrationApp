package in.ashokit.service;

import java.util.Map;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.binding.UserForm;

public interface UserManagement {

	public String login(LoginForm loginForm);

	public String checkEmail(String email);

	public Map<Integer, String> loadCountries();

	public Map<Integer, String> loadStates(int countryId);

	public Map<Integer, String> loadCities(int stateId);

	public String registerUser(UserForm userForm);

	public String unlockAccount(UnlockForm unlockAccForm);

	public String forgotPassword(String email);

}
