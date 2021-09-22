package com.revature.feed.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {

    @Id
    @Column(name="commentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name="commText")
    private String commText;

    @ManyToOne
    @JoinColumn(name="postId", nullable = false)
    private Post post;

    @Column(name="userId", nullable = false)
    private Integer userId;

}
