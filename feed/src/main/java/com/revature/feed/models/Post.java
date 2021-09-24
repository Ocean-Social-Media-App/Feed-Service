package com.revature.feed.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post {

    @Id
    @Column(name="postId")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer postId;

    //fk for the post it belongs to
    //If its the 1st post this will be null
    //any post that reference will contain this.
    @Column(name="postParentId")
    private Integer postParentId;

    @Column(name="postPicUrl")
    private String postPicUrl;

    @Column(name="postText", nullable=false)
    private String postText;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date postTime;

    @Column(name="postYouUrl")
    private String postYouUrl;

    @Column(name="userId", nullable = false)
    private Integer userId;

}