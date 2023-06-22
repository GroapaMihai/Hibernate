package com.bitwise.hibernatenplusone.dao;

import com.bitwise.hibernatenplusone.entity.PostComment;

import java.util.List;

public interface PostCommentDao {

    List<PostComment> findAllWithNPlusOneQueries();

    List<PostComment> findAllWithJoinFetch();
}
