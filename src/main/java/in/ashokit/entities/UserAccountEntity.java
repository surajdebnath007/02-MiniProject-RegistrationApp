package in.ashokit.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserAccountEntity {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	private String password;

	@Column(name = "phone_no")
	private long phoneNo;

	private Date dob;

	private String gender;

	@Column(name = "country_id")
	private int countryId;

	@Column(name = "state_id")
	private int stateId;

	@Column(name = "city_id")
	private int cityId;

	@Column(name = "acc_status")
	private String accStatus;

	@Column(name = "created_date", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;

	@Column(name = "updated_date", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
}
