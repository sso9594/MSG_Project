package com.example.post_server.repository;

import com.example.post_server.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface post_interface extends JpaRepository<Post,Integer> {

}
//    public Post findByUserId(String user_id); //사용자 닉네임을 가져오는 함수
//    public Post findByUserCommentNum(Integer comment_num); // 사용자 댓글 수 가져오는 함수
//    public Post findByPostContent(String post_content); //게시물 내용을 가져오는 함수
//    public Post findByImagePath(String[] image_path); // 게시물 이미지 경로를 가져오는 함수
//    public Post findByTopic(String topic);// 이번주 주제를 가져오는 함수
//    public Post findByLikeNum(Integer like_num); // 게시물 좋아요 수를 가져오는 함수
//}

