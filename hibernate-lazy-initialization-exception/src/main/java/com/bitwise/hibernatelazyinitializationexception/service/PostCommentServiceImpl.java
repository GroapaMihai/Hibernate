package com.bitwise.hibernatelazyinitializationexception.service;

import com.bitwise.hibernatelazyinitializationexception.dao.PostCommentDao;
import com.bitwise.hibernatelazyinitializationexception.dto.PostCommentDTO;
import com.bitwise.hibernatelazyinitializationexception.entity.PostComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentServiceImpl implements PostCommentService {

    private PostCommentDao postCommentDao;

    @Autowired
    public PostCommentServiceImpl(PostCommentDao postCommentDao) {
        this.postCommentDao = postCommentDao;
    }

    @Override
    public List<PostComment> findAllWithLazyInitializationException() {
        return postCommentDao.findAllWithLazyInitializationException();
    }

    @Override
    public List<PostComment> findAllWithJoinFetch() {
        return postCommentDao.findAllWithJoinFetch();
    }

    @Override
    public List<PostCommentDTO> findAllWithProjection() {
        return postCommentDao.findAllWithProjection();
    }
}
