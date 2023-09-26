package ofs.mealtracking.repositories;

import org.springframework.data.repository.CrudRepository;
import ofs.mealtracking.model.Entities.Siteusers;
import java.util.Optional;


public interface SiteusersRepository extends CrudRepository<Siteusers,Long> {
     Siteusers findByUsername(String username);
     Iterable<Siteusers> findAll();
     Optional<Siteusers> findById(Long id);

}


