package com.example.post_server.service;

import com.example.post_server.entity.Post;
import com.example.post_server.entity.PostComment;
import com.example.post_server.entity.PostLike;
import com.example.post_server.model.*;
import com.example.post_server.repository.comment_interface;
import com.example.post_server.repository.like_interface;
import com.example.post_server.repository.post_interface;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final post_interface postRepository;
    private final comment_interface CommentRepository;
    private final like_interface likeRepository;
    @Transactional
    public Post join(Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        return post;
    }
    public Post AddDbPost(CreatePost data) {
        Post post = new Post(
                null,
                data.getUser_id(),
                data.getPost_content(),
                data.getImage_path(),
                data.getTopic(),
                new Date().toString()
        );
        return postRepository.save(post); //DB에 post를 저장 완료
    }

    public void DeletePost(DeletePost data) {
        postRepository.deleteById(data.getPost_id());
    }
    @Transactional //DB에 자동으로 업데이트를 알려줌 -> 연결점이라고 생각하면 됨
    public void ModifyPost(ModifyPost data) {
        Post post = postRepository.findById(data.getPost_id()).orElse(null);
        post.setPost_content(data.getPost_content());
    }
    @Transactional
    public PostComment AddDbComment(Integer id, CreateComment data) {
        Post post_id = postRepository.findById(id).orElse(null);
        PostComment comment = new PostComment(
                data,
                post_id
        );
        return CommentRepository.save(comment);
    }

    public void DeleteComment(DeleteComment data) {
        CommentRepository.deleteById((data.getComment_id()));
    }
    @Transactional
    public PostLike ChangePostLike(Integer PostId, ChangeLike data){
        String userid = data.getUser_id();
        System.out.println(PostId);
        Post post = postRepository.findById(PostId).orElse(null);

        PostLike check_like = likeRepository.findByPostAndUserId(post, userid);
//        System.out.println("여기");
//        System.out.println(check_like);

        if(check_like == null) {
            PostLike like =new PostLike(
                    null,
                    post,
                    data.getUser_id(),
                    true
            );
            return likeRepository.save(like);
        }
        else{
            check_like.setCheckLike(!check_like.isCheckLike());
            return check_like;
        }
    }
    @Transactional
    public ReturnPostLike check(Post post, String user_id) {
        PostLike postLike = likeRepository.findByPostAndUserId(post,user_id);
        ReturnPostLike return_like = new ReturnPostLike(postLike.isCheckLike());
        return return_like;
    }

    @Transactional
    public void add_img(MultipartFile post_img, Integer post_id) {
        MultipartFile image = post_img; // 이미지 데이터 가져오기
        try {
            byte[] imageBytes = image.getBytes(); // 이미지 데이터를 byte 배열로 변환
            String fileName = image.getOriginalFilename(); // 저장할 파일명 생성
            String filePath = "images/" + fileName; // 저장할 파일 경로 생성
            File dest = new File(filePath);
            FileUtils.writeByteArrayToFile(dest, imageBytes); // 파일 시스템에 이미지 저장
            Post post = postRepository.findById(post_id).orElse(null);
            post.setImage_path(filePath);

        } catch (IOException e) {
            // 이미지 저장 실패 시 예외 처리
            e.printStackTrace();
        }
  }
}

