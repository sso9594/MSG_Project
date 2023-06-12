package com.example.post_server.controller;

import com.example.post_server.entity.Post;
import com.example.post_server.entity.PostComment;
import com.example.post_server.entity.PostLike;
import com.example.post_server.model.*;
import com.example.post_server.service.PostService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.FileSystem;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService service;
    @GetMapping("post/get/{id}") //-> detail/check 주소를 뜻함,->
    // 프론트가 요청:데이터베이스로부터 {id}값의 Post 가져와주세요
    public ResponseEntity<Post> get_post_id(@PathVariable Integer id) {
        return ResponseEntity.ok(service.join(id));
    }
// get은 조회, post는 추가 느낌임
    @PostMapping("post")
    public ResponseEntity<Post> add_post(@RequestBody CreatePost post) { //앱에서 넘길때는 'post'
        return ResponseEntity.ok(service.AddDbPost(post));
    }

    @DeleteMapping("post")
    public String delete_post(@RequestBody DeletePost post) {
        service.DeletePost(post);
        return "게시물을 삭제했습니다";
    }
    @PatchMapping("post")
    public String modify_post(@RequestBody ModifyPost post) {
        service.ModifyPost(post);
        return "게시물을 수정했습니다";
   }
   @PostMapping("post/comment/{id}")
   public ResponseEntity<PostComment> add_comment(@PathVariable Integer id, @RequestBody CreateComment comment) {

       return ResponseEntity.ok(service.AddDbComment(id, comment));
   }
   @DeleteMapping("post/comment")
    public String delete_comment(@RequestBody DeleteComment comment) {
        service.DeleteComment(comment);
        return "댓글을 삭제했습니다";
   }

    @PostMapping("post/like/{id}")
    public boolean change_like(@PathVariable Integer id, @RequestBody ChangeLike like) {
        return service.ChangePostLike(id,like).isCheckLike();
   }

   @PostMapping("post/img")
   public String add_img(@RequestPart("file") MultipartFile file, @RequestPart("id") Integer id) {
        service.add_img(file, id);
        return "이미지 추가 완료";
   }
   @GetMapping("post/img")
    public ResponseEntity<FileSystemResource> return_img(@RequestParam("dir") String dir) throws IOException {
       FileSystemResource img = new FileSystemResource(dir);
       HttpHeaders httpHeaders = new HttpHeaders();
       Path path = Paths.get(dir);
       httpHeaders.add("Content-Type", Files.probeContentType(path));
       return new ResponseEntity<>(img, httpHeaders, HttpStatus.OK);
   }
}
