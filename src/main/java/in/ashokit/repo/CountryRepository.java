package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

}
