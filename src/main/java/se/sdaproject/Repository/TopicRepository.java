package se.sdaproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.Model.Topic;

import java.util.List;


public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByArticlesId(Long articlesID);
}
