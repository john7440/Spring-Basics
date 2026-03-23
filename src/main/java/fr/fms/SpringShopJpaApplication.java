package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

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
//        Category smartphone = categoryRepository.save(new Category("Smartphone"));
//        Category tablet = categoryRepository.save(new Category("Tablet"));
//        Category pc = categoryRepository.save(new Category("PC"));
//
//        articleRepository.save(new Article("Samsung", "Galaxy S21", 250, smartphone));
//        articleRepository.save(new Article("Samsung", "Galaxy S25", 500, smartphone));
//
//        articleRepository.save(new Article("Apple", "Ipad 12", 1500, tablet));
//        articleRepository.save(new Article("Apple", "Ipad 15", 3500, tablet));
//
 //       articleRepository.save(new Article("Apple", "MacBook Pro", 5500, pc));

//        for (Article article : articleRepository.findAll()) {
//            System.out.println(article.toString());
//        }
        //----------------------------------------------------------------------------------------

        //question 1.2: method 1
        System.out.println("--------------Question 1.2 method 1-------------------");
        Optional<Article> opt = articleRepository.findById(12L);
        opt.ifPresent(a -> System.out.println(a));
        //method 2
        System.out.println("--------------Question 1.2 method 2-------------------");
        Article a = articleRepository.findById(14L).orElse(null);
        if (a != null) System.out.println(a);
        //all articles
        System.out.println("--------------Question 1.2 List-------------------");
        List<Article> articles = articleRepository.findAll();
        articles.forEach(System.out::println);
        //----------------------------------------------------------------------------------

        //question 1.3 (first part is in ArticleRepository)
        System.out.println("--------------Question 1.3-------------------");
        List<Article> results = articleRepository.findByBrandAndDescriptionContains("Apple", "Ipad");
        results.forEach(System.out::println);
        //----------------------------------------------------------------------------------

        //question 1.4
//        articleRepository.deleteById(16L);
//        System.out.println("--------------Question 1.4-------------------");
//        System.out.println("Article deleted successfully");
        //------------------------------------------------------------

        //question 1.5
        System.out.println("--------------Question 1.5-------------------");
        Optional<Article> opt2 = articleRepository.findById(16L);
        if (opt2.isPresent()) {
            Article article = opt2.get();
            article.setDescription("Test name");
            article.setPrice(999.99);
            articleRepository.save(article);
        }
        List<Article> res = articleRepository.findByDescriptionContains("Test");
        res.forEach(System.out::println);
        //---------------------------------------------------------------------------

        //question 1.6
        System.out.println("--------------Question 1.6-------------------");
        System.out.println("--------------Asc-------------------");
        List<Category> categoriesAsc = categoryRepository.findAllByOrderByNameAsc();
        List<Category> categoriesDesc = categoryRepository.findAllByOrderByNameDesc();
        categoriesAsc.forEach(System.out::println);
        System.out.println("--------------Desc-------------------");
        categoriesDesc.forEach(System.out::println);
        //-----------------------------------------------------------------------

        //question 1.7
        System.out.println("--------------Question 1.7-------------------");
        List <Article> articlesDesc = articleRepository.findAllByOrderByPriceDesc();
        articlesDesc.forEach(System.out::println);

    }

}
