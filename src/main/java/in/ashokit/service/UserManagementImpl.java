package in.ashokit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.binding.UserForm;
import in.ashokit.entities.CityEntity;
import in.ashokit.entities.CountryEntity;
import in.ashokit.entities.StateEntity;
import in.ashokit.entities.UserAccountEntity;
import in.ashokit.repo.CityRepository;
import in.ashokit.repo.CountryRepository;
import in.ashokit.repo.StateRepository;
import in.ashokit.repo.UserAccountRepository;
import in.ashokit.utils.EmailUtils;

@Service
public class UserManagementImpl implements UserManagement {

	@Autowired
	private UserAccountRepository userRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String login(LoginForm loginForm) {

		UserAccountEntity entity = userRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		if (entity == null) {
			return "Invalid Credentials";
		}
		if (entity != null && entity.getAccStatus().equals("LOCKED")) {
			return "Account is Locked";
		}
		return "Login Successfull";
	}

	@Override
	public String checkEmail(String email) {

		UserAccountEntity entity = userRepo.findByEmail(email);
		if (entity == null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> loadCountries() {

		List<CountryEntity> countries = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();

		for (CountryEntity entity : countries) {
			countryMap.put(entity.getCountryId(), entity.getCountryName());
		}

		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(int countryId) {

		List<StateEntity> states = stateRepo.findByCountryId(countryId);

		Map<Integer, String> stateMap = new HashMap<>();

		for (StateEntity entity : states) {
			stateMap.put(entity.getStateId(), entity.getStateName());
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCities(int stateId) {

		List<CityEntity> cities = cityRepo.findByStateId(stateId);

		Map<Integer, String> cityMap = new HashMap<>();

		for (CityEntity entity : cities) {
			cityMap.put(entity.getCityId(), entity.getCityName());
		}
		return cityMap;

	}

	@Override
	public String registerUser(UserForm userForm) {

		UserAccountEntity entity = new UserAccountEntity();

		BeanUtils.copyProperties(userForm, entity);

		entity.setAccStatus("LOCKED");

		entity.setPassword(randomPasswordGenerator());

		UserAccountEntity savedEntity = userRepo.save(entity);

		String fileName = "Unlock-Account-Template.txt";
		String to = userForm.getEmail();
		String subject = "User Regitration";
		String body = readMailBody(fileName, entity);

		boolean isSent = emailUtils.sendEmail(to, subject, body);

		if (savedEntity != null && isSent) {
			return "Registration Successful";
		}
		return "Registration Failed";
	}

	@Override
	public String unlockAccount(UnlockForm unlockAccForm) {

		if (!unlockAccForm.getNewPwd().equals(unlockAccForm.getConfirmNewPwd())) {
			return " Password and Confirm Password should be same";
		}

		UserAccountEntity entity = userRepo.findByEmailAndPassword(unlockAccForm.getEmail(),
				unlockAccForm.getConfirmNewPwd());

		if (entity == null) {
			return "Invalid Temporary Password";
		}

		entity.setPassword(unlockAccForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");

		userRepo.save(entity);

		return "Account Unlock Successful";
	}

	@Override
	public String forgotPassword(String email) {

		UserAccountEntity entity = userRepo.findByEmail(email);

		if (entity == null) {
			return "Enter Correct Emaild";
		}

		String fileName = "Forgot-Password-Template.txt";
		String to = email;
		String subject = "Forgot Password";
		String body = readMailBody(fileName, entity);

		boolean isSet = emailUtils.sendEmail(to, subject, body);

		if (isSet == true) {
			return "Password sent to email succesfully";
		}
		return "Password reset failed";
	}

	private String randomPasswordGenerator() {

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	private String readMailBody(String fileName, UserAccountEntity entity) {

		String mailBody = null;
		try {
			StringBuffer buffer = new StringBuffer();
			FileReader fileReader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fileReader);
			String line = br.readLine();

			while (line != null) {
				buffer.append(line);
				line = br.readLine();
			}
			mailBody = buffer.toString();
			mailBody = mailBody.replace("{fNAME}", entity.getFirstName());
			mailBody = mailBody.replace("{LNAME}", entity.getLastName());
			mailBody = mailBody.replace("{TEMP-PWD}", entity.getPassword());
			mailBody = mailBody.replace("{PWD}", entity.getPassword());
			mailBody = mailBody.replace("{EMAIL}", entity.getEmail());

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

}
