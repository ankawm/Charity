package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.domain.Institution;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {
    Institution findByName(String name);
    Institution findById(long id);
    List<Institution> findAll();
}
