package com.example.post_server.model;

import lombok.Getter;

import java.util.List;

@Getter
public class CreatePost {    //createpost-> post entity가 되어야 함 model은 앱과 서버가 통신을 하는 것
    private String user_id;
    private String post_content;
    private String image_path;
    private String topic;
}
