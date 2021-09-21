package com.ocean.repository;

import com.ocean.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("postDao")
@Transactional
public interface PostDao extends JpaRepository<Post, Integer> {

    //Custom Query to get the Post by the UserId
    @Query("from Post where user_id = :userId")
    List<Post> getPostByUserId(@Param("userId") Integer userId);

}
