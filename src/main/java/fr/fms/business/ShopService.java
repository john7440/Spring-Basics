package fr.fms.business;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
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
}
