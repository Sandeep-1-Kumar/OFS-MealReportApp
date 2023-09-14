package ofs.mealtracking.repositories;

import ofs.mealtracking.model.Entities.Mealcount;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MealCountRepository extends CrudRepository<Mealcount,Long> {

   Optional<Mealcount> findById(Long id);

List<Mealcount> findAll(Example<Mealcount> example);
    
}
