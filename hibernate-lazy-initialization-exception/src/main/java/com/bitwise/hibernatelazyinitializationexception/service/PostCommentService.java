package com.bitwise.hibernatelazyinitializationexception.service;

import com.bitwise.hibernatelazyinitializationexception.dto.PostCommentDTO;
import com.bitwise.hibernatelazyinitializationexception.entity.PostComment;

import java.util.List;

public interface PostCommentService {

    List<PostComment> findAllWithLazyInitializationException();

    List<PostComment> findAllWithJoinFetch();

    List<PostCommentDTO> findAllWithProjection();
}
