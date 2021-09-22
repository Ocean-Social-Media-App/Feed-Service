package com.revature.feed.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="likes")
public class Like {

    @Id
    @Column(name="likeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;

    @Column(name="userId", nullable = false)
    private Integer userId;

    @ManyToOne
    @JoinColumn(name="postId", nullable = false)
    private Post post;
}
