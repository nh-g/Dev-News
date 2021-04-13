package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {

    TopicRepository topicRepository;
    ArticleRepository articleRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository, ArticleRepository articleRepository) {
        this.topicRepository = topicRepository;
        this.articleRepository = articleRepository;
    }

    /* return all topics. */
    @GetMapping("/topics")
    public List<Topic> listAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics;
    }

    /* return all topics associated with article given by articleId. */
    @GetMapping("/articles/{articleId}/topics")
    public ResponseEntity<List<Topic>> listTopicsOnArticle(@PathVariable Long articleId) {
        articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(topicRepository.findByArticlesId(articleId));
    }

    /* create a new topic. */
    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        topicRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    /* associate the topic with the article given by articleId.
    If topic does not already exist, it is created.*/
    @PostMapping("/articles/{articleId}/topics/{topicId}")
    public ResponseEntity<Topic> associateTopic(@PathVariable Long articleId, @PathVariable Long topicId) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new); //remove?
        topic.getArticles().add(article);
        topicRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    /* update the given topic*/
    @PutMapping("/topics/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic updatedTopic) {
        topicRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedTopic.setId(id);
        Topic topic = topicRepository.save(updatedTopic);
        return ResponseEntity.ok(topic);
    }

    /* delete the given topic*/
    @DeleteMapping("/topics/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        topicRepository.delete(topic);
    }

    /* delete the association of a topic for the given article.
    The topic & article themselves remain. */

    @DeleteMapping("/articles/{articleId}/topics/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAssociation(@PathVariable Long topicId, @PathVariable Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
        if (topic.getArticles().contains(article)) {
            topic.getArticles().remove(article);
            topicRepository.save(topic);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /* return all articles associated with the topic given by topicId. */
    @GetMapping("/topics/{topicId}/articles")
    public ResponseEntity<List<Article>> listArticlesInTopic(@PathVariable Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
        List<Article> articles = topic.getArticles();
        return ResponseEntity.ok(articles);
    }

}