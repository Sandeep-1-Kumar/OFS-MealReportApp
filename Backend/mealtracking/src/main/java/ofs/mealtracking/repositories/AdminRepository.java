package ofs.mealtracking.repositories;

import org.springframework.data.repository.CrudRepository;
import ofs.mealtracking.model.Entities.Admins;


public interface AdminRepository extends CrudRepository<Admins, Long>{

    Admins findByUsername(String username);
}


