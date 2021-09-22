package com.revature.feed.services;

import com.revature.feed.models.Like;
import com.revature.feed.repository.LikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("likeService")
public class LikeService {
    private LikeDao likeDao;

    @Autowired
    public LikeService(LikeDao likeDao){this.likeDao = likeDao;}

    public Like createLike(Like like) {
        return this.likeDao.save(like);
    }

    public List<Like> getLikeByPostId(Integer postId) {
        return this.likeDao.getLikesByPostId(postId);
    }


    public Boolean deleteLike(Integer likeId) {
        boolean checkDelete = this.likeDao.existsById(likeId);
        if (checkDelete){
            this.likeDao.deleteById(likeId);
            return true;
        }
        return false;
    }
}