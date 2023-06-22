package com.bitwise.hibernatenplusone.service;

import com.bitwise.hibernatenplusone.dao.PostCommentDao;
import com.bitwise.hibernatenplusone.entity.PostComment;
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
    public List<PostComment> findAllWithNPlusOneQueries() {
        return postCommentDao.findAllWithNPlusOneQueries();
    }

    @Override
    public List<PostComment> findAllWithJoinFetch() {
        return postCommentDao.findAllWithJoinFetch();
    }
}
