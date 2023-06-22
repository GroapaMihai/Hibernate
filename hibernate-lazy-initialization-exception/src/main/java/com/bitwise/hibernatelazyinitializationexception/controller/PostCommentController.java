package com.bitwise.hibernatelazyinitializationexception.controller;

import com.bitwise.hibernatelazyinitializationexception.dto.PostCommentDTO;
import com.bitwise.hibernatelazyinitializationexception.entity.PostComment;
import com.bitwise.hibernatelazyinitializationexception.service.PostCommentService;
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

    @GetMapping("/lazy-initialization-exception")
    public ResponseEntity<List<PostComment>> findAllWithLazyInitializationException() {
        List<PostComment> postComments = postCommentService.findAllWithLazyInitializationException();

        return new ResponseEntity<>(postComments, HttpStatus.OK);
    }

    @GetMapping("/join-fetch")
    public ResponseEntity<List<PostComment>> findAllWithJoinFetch() {
        List<PostComment> postComments = postCommentService.findAllWithJoinFetch();

        return new ResponseEntity<>(postComments, HttpStatus.OK);
    }

    @GetMapping("/projection")
    public ResponseEntity<List<PostCommentDTO>> findAllWithProjection() {
        List<PostCommentDTO> postCommentDTOList = postCommentService.findAllWithProjection();

        return new ResponseEntity<>(postCommentDTOList, HttpStatus.OK);
    }
}
