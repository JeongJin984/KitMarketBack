package com.siy.KitMarket.service.post;

import com.siy.KitMarket.domain.dto.post.StudyDto;
import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Study;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    EntityManager em;

    Study post1 = new Study("Study1", "I'm Study1", "study1111");
    CarFull post2 = new CarFull("CarFull1", "I'm CarFull1", "carfull");
    Study post3 = new Study("Study2", "I'm Study2", "study22222");
    CarFull post4 = new CarFull("CarFull2", "I'm CarFull2", "carfull");

    Application application1 = new Application("댓글 1입니다.", post1);
    Application application2 = new Application("댓글 2입니다.", post2);
    Application application3 = new Application("댓글 3입니다.", post3);
    Application application4 = new Application("댓글 4입니다.", post4);
    Application application5 = new Application("댓글 5입니다.", post1);

    @BeforeEach
    public void before(){
        postService.save(post1);
        postService.save(post2);
        postService.save(post3);
        postService.save(post4);


        em.flush();
        em.clear();
    }


    @Test
    @Commit
    public void selectStudyWithAppbyId() throws Exception{
        //when
        StudyDto findStudyDto = postService.findStudyOne(post1.getId());
        //then
        System.out.println("findStudyDto = " + findStudyDto);

    }
}