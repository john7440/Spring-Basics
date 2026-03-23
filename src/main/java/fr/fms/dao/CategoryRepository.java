package fr.fms.dao;

import fr.fms.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    //added for question 1.6
    List<Category> findAllByOrderByNameAsc();
    List<Category> findAllByOrderByNameDesc();
}
