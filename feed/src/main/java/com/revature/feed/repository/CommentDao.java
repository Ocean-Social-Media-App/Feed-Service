package com.revature.feed.repository;

import com.revature.feed.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("commentDao")
@Transactional
public interface CommentDao extends JpaRepository<Comment, Integer> {


    @Query("from Comment where post_id = :postId")
    List<Comment> getCommentByPostId(@Param("postId")Integer postId);


    @Query("from Comment where userId = :userId")
    List<Comment> getCommentByUserId(@Param("userId")Integer userId);

}
