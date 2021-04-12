package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticlesController {
    ArticlesModelRepository articlesModelRepository;

    @Autowired
    public ArticlesController(ArticlesModelRepository articlesModelRepository) {
        this.articlesModelRepository = articlesModelRepository;
    }

    /*view all articles*/
    @GetMapping("/articles")
    public List <Articles> listAllArticles() {
        List <Articles> articles = articlesModelRepository.findAll();
        return articles;
    }

    /*create a new article*/
    @PostMapping("/create")
    public ResponseEntity<Articles> createArticles(@RequestBody Articles article) {
        articlesModelRepository.save(article);
        // ResponseEntity is a wrapper around regular http status
        // allows us to set the status of whatever the response would be
        // HttpStatus.Created: one constant of http status. "Created" here means status 201.
        // .body: what the body of the response should be. In this case newly created article
        // should be the response to show.
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    /*return a specific article based on the provided id.*/
    @GetMapping ("/{id}")
    public ResponseEntity<Articles> getArticlesByID(@PathVariable Long id) {
        Articles targetArticle = articlesModelRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(targetArticle);
    }

    /*update the given article*/
    @PutMapping ("/update/{id}")
    public  ResponseEntity<Articles> updateArticle(@PathVariable Long id, @RequestBody Articles articleParams) {
        Articles article = articlesModelRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articleParams.setId(id);
        Articles updatedArticle = articlesModelRepository.save(articleParams);
        return ResponseEntity.ok(updatedArticle);
    }

    /*delete the given article.*/
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Articles> deleteArticle(@PathVariable Long id) {
        Articles article = articlesModelRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articlesModelRepository.delete(article);
        return ResponseEntity.ok(article); //return the article which was deleted //with http status 204 no_content

    }

}

