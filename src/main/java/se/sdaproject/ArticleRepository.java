package se.sdaproject;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.Article;

public interface ArticleRepository extends JpaRepository <Article, Long>{

}
