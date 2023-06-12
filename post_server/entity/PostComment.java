package com.example.post_server.entity;

import com.example.post_server.model.CreateComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id; // 자동으로 코멘트 1씩 증가
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post_id; // 연관관계 맵핑
    private String user_id;
    private String comment;
    private String write_time;

    public PostComment(CreateComment data, Post PostID){
        comment_id = null;
        post_id = PostID;
        user_id = data.getUser_id();
        comment = data.getComment();
        write_time = new Date().toString();
    }
}
