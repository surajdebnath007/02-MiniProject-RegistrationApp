package in.ashokit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="state_master")
@Data
public class StateEntity {
	@Id
	@Column(name="state_id")
	private int stateId;
	@Column(name = "state_name")
	private String stateName;
	@Column(name="country_id")
	private int countryId;

}
