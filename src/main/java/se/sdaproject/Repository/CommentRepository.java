package se.sdaproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.Model.Comment;

import java.util.List;


public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findByAuthorName(String authorName);
    List<Comment> findByArticleId(Long id);
}
