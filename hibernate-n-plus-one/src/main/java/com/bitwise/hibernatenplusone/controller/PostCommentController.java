package com.bitwise.hibernatenplusone.controller;

import com.bitwise.hibernatenplusone.entity.PostComment;
import com.bitwise.hibernatenplusone.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post_comments")
public class PostCommentController {

    private PostCommentService postCommentService;

    @Autowired
    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    @GetMapping("/n-plus-one")
    public ResponseEntity<List<PostComment>> findAllWithNPlusOneQueries() {
        List<PostComment> postComments = postCommentService.findAllWithNPlusOneQueries();

        return new ResponseEntity<>(postComments, HttpStatus.OK);
    }

    @GetMapping("/join-fetch")
    public ResponseEntity<List<PostComment>> findAllWithJoinFetch() {
        List<PostComment> postComments = postCommentService.findAllWithJoinFetch();

        return new ResponseEntity<>(postComments, HttpStatus.OK);
    }
}
