package fr.fms.dao;

import fr.fms.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    public List<Article> findByBrand(String brand);
    public List<Article> findByBrandContains(String brand);
    public List<Article> findByBrandAndPrice(String brand, double price);
    public List<Article> findByCategoryId(Long categoryId);
    //added for ex 1.3
    public List<Article> findByBrandAndDescriptionContains(String brand, String description);
}
