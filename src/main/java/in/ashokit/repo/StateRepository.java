package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Integer> {

	public List<StateEntity> findByCountryId(int countryId);
}
