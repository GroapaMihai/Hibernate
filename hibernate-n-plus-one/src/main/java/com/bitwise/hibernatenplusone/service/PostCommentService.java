package com.bitwise.hibernatenplusone.service;

import com.bitwise.hibernatenplusone.entity.PostComment;

import java.util.List;

public interface PostCommentService {

    List<PostComment> findAllWithNPlusOneQueries();

    List<PostComment> findAllWithJoinFetch();
}
