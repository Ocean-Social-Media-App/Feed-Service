package com.ocean.repository;

import com.ocean.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("likeDao")
@Transactional
public interface LikeDao extends JpaRepository<Like, Integer> {
    //Custom Query to get the Post by the UserId
    @Query("from Like where post_id = :postId")
    List<Like> getLikesByPostId(@Param("postId") Integer postId);

/*    @Query("from Like where post_id = :postId and user_id = :userId")
    boolean getLikesByPostIdAndUserId(@Param("postId")Integer postId, @Param("userId") Integer userId);*/

}
