package com.siy.KitMarket.domain.entity.post;

import com.siy.KitMarket.domain.entity.Chat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Commit
class PostTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    public void before(){
        Post post1 = new Study("Study1", "I'm Study1");
        Post post2 = new CarFull("CarFull1", "I'm CarFull1");
        Post post3 = new Study("Study2", "I'm Study2");
        Post post4 = new CarFull("CarFull2", "I'm CarFull2");

        Chat chat1 = new Chat("댓글 1입니다.", post1);
        Chat chat2 = new Chat("댓글 2입니다.", post2);
        Chat chat3 = new Chat("댓글 3입니다.", post3);
        Chat chat4 = new Chat("댓글 4입니다.", post4);
        Chat chat5 = new Chat("댓글 5입니다.", post1);

        em.persist(chat1);
        em.persist(chat2);
        em.persist(chat3);
        em.persist(chat4);
        em.persist(chat5);

        em.persist(post1);
        em.persist(post2);
        em.persist(post3);
        em.persist(post4);

        em.flush();
        em.clear();
    }

    /**
     * 기본 post 찾기
     */
    @Test
    public void findTest(){
        List<Post> posts = em.createQuery("select p from Post p", Post.class)
                .getResultList();

        for(Post post : posts){
            System.out.println("post = " + post);
            for(Chat chat : post.getChats()){
                System.out.println("chat = " + chat);
            }
        }
    }


}