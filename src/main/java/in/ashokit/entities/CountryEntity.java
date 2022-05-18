package in.ashokit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "country_master")
@Data
public class CountryEntity {
	@Id
	@Column(name = "country_id")
	private int countryId;
	@Column(name = "country_name")
	private String countryName;
}
