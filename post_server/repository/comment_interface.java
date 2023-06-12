package com.example.post_server.repository;

import com.example.post_server.entity.Post;
import com.example.post_server.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;

public interface comment_interface extends JpaRepository<PostComment,Integer> {

}
