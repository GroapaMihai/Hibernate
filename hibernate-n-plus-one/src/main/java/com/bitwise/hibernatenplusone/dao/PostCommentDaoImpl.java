package com.bitwise.hibernatenplusone.dao;

import com.bitwise.hibernatenplusone.entity.PostComment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostCommentDaoImpl implements PostCommentDao {

    private EntityManager entityManager;

    @Autowired
    public PostCommentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Due to the N + 1 queries problem this will print the following output:
     *
     * org.hibernate.SQL: SELECT pc.* FROM post_Comments pc
     * org.hibernate.SQL: select p1_0.id,p1_0.title from posts p1_0 where p1_0.id=?
     * org.hibernate.SQL: select p1_0.id,p1_0.title from posts p1_0 where p1_0.id=?
     * org.hibernate.SQL: select p1_0.id,p1_0.title from posts p1_0 where p1_0.id=?
     * org.hibernate.SQL: select p1_0.id,p1_0.title from posts p1_0 where p1_0.id=?
     *
     * @return the list of post comments
     */
    @Override
    public List<PostComment> findAllWithNPlusOneQueries() {
        TypedQuery<PostComment> query = entityManager.createQuery("SELECT pc FROM PostComment pc", PostComment.class);

        return query.getResultList();
    }

    /**
     * Using JOIN FETCH we avoid the N + 1 queries problem.
     *
     * org.hibernate.SQL: select p1_0.id,p1_0.post_id,p2_0.id,p2_0.title,p1_0.review from post_comments p1_0 join posts p2_0 on p2_0.id=p1_0.post_id
     *
     * @return the list of post comments
     */
    @Override
    public List<PostComment> findAllWithJoinFetch() {
        TypedQuery<PostComment> query = entityManager.createQuery("SELECT pc FROM PostComment pc JOIN FETCH pc.post p", PostComment.class);

        return query.getResultList();
    }
}
