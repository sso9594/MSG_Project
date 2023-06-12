package com.example.post_server.model;

import lombok.Getter;

@Getter
public class CreateComment {
    //private Integer post_id;
    private String user_id;
    private String comment;
}
