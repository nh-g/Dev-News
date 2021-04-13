package se.sdaproject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findByAuthorName(String authorName);
    List<Comment> findByArticleId(Long id);
}
