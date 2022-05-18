package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {

	public List<CityEntity> findByStateId(int stateId);
}
