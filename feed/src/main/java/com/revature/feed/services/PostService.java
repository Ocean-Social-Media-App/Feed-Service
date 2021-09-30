package com.revature.feed.services;

import com.revature.feed.models.Post;
import com.revature.feed.repository.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("postService")
public class PostService {
    private PostDao postDao;

    @Autowired
    public PostService(PostDao postDao){this.postDao = postDao;}

    //Create a Post
    public Post createPost(Post post) {
        return this.postDao.save(post);
    }

    //Post for the favorite
    public List<Post> selectPostForFav(Integer page, List<Integer> fave ) {
        List<Post> fullListPost = new ArrayList<>();
        for (int i = 0; i < fave.size(); i++) {
            List<Post> userPost = this.postDao.getPostByUserId(fave.get(i));
            List<Post> filteredPosts = userPost.stream()
                    .filter(x -> x.getPostParentId() == null)
                    .collect(Collectors.toList());
            fullListPost.addAll(filteredPosts);
        }
        Collections.sort(fullListPost, Comparator.comparingInt(Post::getPostId).reversed());
        Double checkPages = Double.valueOf(fullListPost.size()) / 20;
        int num = (int) (Math.ceil(checkPages));
        if (page <= num) {
            Integer pageEnd = page *20 -1;
            Integer offset = page * 20 - 20;
            List<Post> pagePost = new ArrayList<>();
            for (int j = offset; j < pageEnd; j++) {
                if (fullListPost.size() == j) {
                    break;
                } else {
                    pagePost.add(fullListPost.get(j));
                }
            }

            return pagePost;

        }else{
            return null;
        }

    }



    //Get comments for a post
    public List<Post> getAllParentId(Integer parentId){
        List<Post> commentList = this.postDao.getPostByParentId(parentId);
        Collections.sort(commentList, Comparator.comparingInt(Post::getPostId).reversed());
        return commentList;
    }



    //Read a post
    public Post getPostById(Integer postId) {
        return this.postDao.findById(postId).orElse(null);
    }

    public List<Post> getPostByUserId(Integer userId, Integer pageNumber) {
        List<Post> database = this.postDao.getPostByUserId(userId);
        List<Post> filteredPosts = database.stream()
                .filter(x -> x.getPostParentId() == null)
                .collect(Collectors.toList());

        Collections.sort(filteredPosts, Comparator.comparingInt(Post::getPostId).reversed());
        Double checkPages = Double.valueOf(filteredPosts.size()) / 20;
        int num = (int) (Math.ceil(checkPages));
        if (pageNumber <= num) {
            Integer pageEnd = pageNumber *20 -1;
            Integer offset = pageNumber * 20 - 20;
            List<Post> pagePost = new ArrayList<>();
            for (int j = offset; j < pageEnd; j++) {
                if (filteredPosts.size() == j) {
                    break;
                } else {
                    pagePost.add(filteredPosts.get(j));
                }
            }
            return pagePost;
        }else{
            return null;
        }

    }



    //Update a post
    public Post updatePost(Post post) {
        //Gets Post from Database
        Post dataBasePost = getPostById(post.getPostId());
        //Checks to see if a result is returned
        if(dataBasePost != null){
            //To make sure the Id doesn't get changed by anyone
            post.setPostId(dataBasePost.getPostId());
            //Have to set the userId or null exception is thrown
            post.setUserId(dataBasePost.getUserId());
            //Set postParentId
            post.setPostParentId(dataBasePost.getPostParentId());
            //Executes the update
            this.postDao.save(post);
            //Returns the updated post
            return post;
        }
        return null;
    }

    //Delete a post
    public Post deletePost(Integer postId) {
        Post checkDelete = this.postDao.findById(postId).orElse(null);
        if(checkDelete != null){
            //deletes the child posts
            this.postDao.deleteByParentId(postId);
            //deletes the parent posts
            this.postDao.deleteById(postId);
            return checkDelete;
        }
        return null;
    }


}
