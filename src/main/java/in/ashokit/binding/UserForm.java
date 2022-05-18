package in.ashokit.binding;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserForm {

	private String firstName;

	private String lastName;

	private String email;

	private long phoneNo;

	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date dob;

	private String gender;

	private int countryId;

	private int stateId;

	private int cityId;
	
}
