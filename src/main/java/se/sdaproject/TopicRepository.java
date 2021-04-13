package se.sdaproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByArticlesId(Long articlesID);
}
