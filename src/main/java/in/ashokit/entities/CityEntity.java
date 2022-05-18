package in.ashokit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "city_master")
@Data
public class CityEntity {
	@Id
	@Column(name = "city_id")
	private int cityId;
	@Column(name = "city_name")
	private String cityName;
	@Column(name = "state_id")
	private int stateId;

}
