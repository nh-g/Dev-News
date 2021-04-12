package se.sdaproject;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sdaproject.Comment;

public interface CommentRepository extends JpaRepository <Comment, Long> {

}
