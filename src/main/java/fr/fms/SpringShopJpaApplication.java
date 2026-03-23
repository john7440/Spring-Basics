package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

	public static void main(String[] args) {
        SpringApplication.run(SpringShopJpaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Category smartphone = categoryRepository.save(new Category("Smartphone"));
        Category tablet = categoryRepository.save(new Category("Tablet"));
        Category pc = categoryRepository.save(new Category("PC"));

        articleRepository.save(new Article("Samsung", "Galaxy S21", 250, smartphone));
        articleRepository.save(new Article("Samsung", "Galaxy S25", 500, smartphone));

        articleRepository.save(new Article("Apple", "Ipad 12", 1500, tablet));
        articleRepository.save(new Article("Apple", "Ipad 15", 3500, tablet));

        articleRepository.save(new Article("Apple", "MacBook Pro", 5500, smartphone));

        for (Article article : articleRepository.findByCategoryId(smartphone.getId())) {
            System.out.println(article);
        }
    }

}
