package se.sdaproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.Model.Article;

public interface ArticleRepository extends JpaRepository <Article, Long>{

}
