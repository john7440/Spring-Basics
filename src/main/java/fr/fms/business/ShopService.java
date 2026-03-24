package fr.fms.business;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //---------------------------------articles-------------------------------
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Page<Article> getAllArticlesByPage(int page, int size) {
        return articleRepository.findAll(PageRequest.of(page, size));
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public boolean deleteArticleById(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //-----------------------------categories-----------------------------------
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public boolean deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Article> getArticlesByCategory(Long categoryId) {
        return articleRepository.findByCategoryId(categoryId);
    }
}
