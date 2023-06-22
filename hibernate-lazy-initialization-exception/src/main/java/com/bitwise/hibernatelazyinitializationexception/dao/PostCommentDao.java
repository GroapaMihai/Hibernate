package com.bitwise.hibernatelazyinitializationexception.dao;

import com.bitwise.hibernatelazyinitializationexception.dto.PostCommentDTO;
import com.bitwise.hibernatelazyinitializationexception.entity.PostComment;

import java.util.List;

public interface PostCommentDao {

    List<PostComment> findAllWithLazyInitializationException();

    List<PostComment> findAllWithJoinFetch();

    List<PostCommentDTO> findAllWithProjection();
}
