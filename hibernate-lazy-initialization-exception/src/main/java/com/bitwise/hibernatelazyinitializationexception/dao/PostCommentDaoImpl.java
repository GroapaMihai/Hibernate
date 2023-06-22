package com.bitwise.hibernatelazyinitializationexception.dao;

import com.bitwise.hibernatelazyinitializationexception.dto.PostCommentDTO;
import com.bitwise.hibernatelazyinitializationexception.entity.PostComment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@Repository
public class PostCommentDaoImpl implements PostCommentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostCommentDaoImpl.class);

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public PostCommentDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Hibernate is going to throw a LazyInitializationException because the PostComment entity did not fetch the Post
     * association while the EntityManager was still opened, and the Post relationship was marked with FetchType.LAZY.
     *
     * @return the list of post comments
     */
    @Override
    public List<PostComment> findAllWithLazyInitializationException() {
        List<PostComment> postComments;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            TypedQuery<PostComment> query = entityManager.createQuery("SELECT pc FROM PostComment pc", PostComment.class);
            postComments = query.getResultList();

            transaction.commit();
        } catch (Throwable e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw e;
        } finally {
            entityManager.close();
        }

        try {
            for(PostComment comment : postComments) {
                LOGGER.info("The post title is '{}'", comment.getPost().getTitle());
            }
        } catch (LazyInitializationException expected) {
            LOGGER.info("LazyInitializationException caught: " + expected.getMessage());
            assertTrue(expected.getMessage().startsWith("could not initialize proxy"));
        }

        return postComments;
    }

    /**
     * The JOIN FETCH directive instructs Hibernate to issue an INNER JOIN so that Post entities are fetched along with the PostComment records
     *
     * @return the list of post comments
     */
    @Override
    public List<PostComment> findAllWithJoinFetch() {
        List<PostComment> postComments;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            TypedQuery<PostComment> query = entityManager.createQuery("SELECT pc FROM PostComment pc JOIN FETCH pc.post", PostComment.class);
            postComments = query.getResultList();

            transaction.commit();
        } catch (Throwable e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw e;
        } finally {
            entityManager.close();
        }

        for(PostComment comment : postComments) {
            LOGGER.info("The post title is '{}'", comment.getPost().getTitle());
        }

        return postComments;
    }

    /**
     * A DTO projection allows you to fetch fewer columns, and you won’t risk any LazyInitializationException.
     *
     * @return the list of post comments
     */
    @Override
    public List<PostCommentDTO> findAllWithProjection() {
        List<PostCommentDTO> postCommentDTOList;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            TypedQuery<PostCommentDTO> query = entityManager.createQuery("""
                SELECT NEW com.bitwise.hibernatelazyinitializationexception.dto.PostCommentDTO(pc.id, pc.review, p.title)
                FROM PostComment pc
                JOIN pc.post p
            """, PostCommentDTO.class);
            postCommentDTOList = query.getResultList();

            transaction.commit();
        } catch (Throwable e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw e;
        } finally {
            entityManager.close();
        }

        for(PostCommentDTO commentDTO : postCommentDTOList) {
            LOGGER.info("The post title is '{}'", commentDTO.getTitle());
        }

        return postCommentDTOList;
    }
}
