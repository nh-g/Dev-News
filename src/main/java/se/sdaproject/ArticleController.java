package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /*return all articles*/
    @GetMapping("/articles")
    public List <Article> listAllArticles() {
        List <Article> articles = articleRepository.findAll();
        return articles;
    }

    /*create a new article*/
    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        articleRepository.save(article);
        // ResponseEntity is a wrapper around regular http status
        // allows us to set the status of whatever the response would be
        // HttpStatus.Created: one constant of http status. "Created" here means status 201.
        // .body: what the body of the response should be. In this case newly created article
        // should be the response to show.
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    /*return a specific article based on the provided id.*/
    @GetMapping ("/articles/{id}")
    public ResponseEntity<Article> getArticleByID(@PathVariable Long id) {
        Article targetArticle = articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(targetArticle);
    }

    /*update the given article*/
    @PutMapping ("/articles/{id}")
    public  ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleParams) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articleParams.setId(id);
        Article updatedArticle = articleRepository.save(articleParams);
        return ResponseEntity.ok(updatedArticle);
    }

    /*delete the given article.*/
    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articleRepository.delete(article);
        return ResponseEntity.ok(article); //return the article which was deleted //with http status 204 no_content
    }

}

