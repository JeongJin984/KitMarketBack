package com.siy.KitMarket.domain.entity.post;

import com.siy.KitMarket.domain.entity.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class PostTest {

    @Autowired
    EntityManager em;

    Study post1 = new Study("Study1", "I'm Study1", "study");
    CarFull post2 = new CarFull("CarFull1", "I'm CarFull1", "carfull");
    Study post3 = new Study("Study2", "I'm Study2", "study");
    CarFull post4 = new CarFull("CarFull2", "I'm CarFull2", "carfull");

    Application application1 = new Application("댓글 1입니다.", post1);
    Application application2 = new Application("댓글 2입니다.", post2);
    Application application3 = new Application("댓글 3입니다.", post3);
    Application application4 = new Application("댓글 4입니다.", post4);
    Application application5 = new Application("댓글 5입니다.", post1);

    @BeforeEach
    public void before(){
        em.persist(post1);
        em.persist(post2);
        em.persist(post3);
        em.persist(post4);

        em.persist(application1);
        em.persist(application2);
        em.persist(application3);
        em.persist(application4);
        em.persist(application5);


        em.flush();
        em.clear();
    }

    /**
     * 기본 post 찾기
     */
    @Test
    public void findTest(){
        Post findPost = em.find(Post.class, post1.getId());

        assertThat(findPost.getId()).isEqualTo(post1.getId());
    }
    @Test
    public void findStudy(){
        Study findStudy = em.find(Study.class, post1.getId());
        assertThat(findStudy.getStudy()).isEqualTo(post1.getStudy());
    }


}