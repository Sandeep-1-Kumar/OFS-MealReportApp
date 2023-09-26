package ofs.mealtracking.repositories;

import org.springframework.data.repository.CrudRepository;
import ofs.mealtracking.model.Entities.Siteusers;

public interface SiteusersRepository extends CrudRepository<Siteusers,Long> {
     Siteusers findByUsername(String username);
     Iterable<Siteusers> findAll();

}


