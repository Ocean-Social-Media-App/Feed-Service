package com.ocean.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
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

    @Column(name="postPicUrl")
    private String postPicUrl;

    @Column(name="postText", nullable=false)
    private String postText;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date postTime;

    @Column(name="postYouUrl") //youtube url link
    private String postYouUrl;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user;


    //Modified Constructor
    public Post(Integer postId, String postPicUrl, String postText, User user) {
        this.postId = postId;
        this.postPicUrl = postPicUrl;
        this.postText = postText;
        this.user = user;
    }

    //Modified Constructor
    public Post(String postPic, String text, String postPicUrl, String postText, User tempUser) {
        this.postPicUrl = postPicUrl;
        this.postText = postText;
    }

}