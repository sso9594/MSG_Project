package com.example.post_server.repository;

import com.example.post_server.entity.Post;
import com.example.post_server.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface like_interface  extends JpaRepository<PostLike,Integer> {
    PostLike findByPostAndUserId(Post post_id, String user_id);
//    @Query("SELECT pl FROM PostLike pl WHERE pl.postId.post_id = :postId AND pl.userId = :userId")
//    PostLike findByPostIdAndUserId(@Param("postId") Integer postId, @Param("userId") String userId);
}

