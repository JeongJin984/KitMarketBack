package com.siy.siyresource.repository.ApplicationRepositoy;

import com.siy.siyresource.common.api.PostApiController;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.post.Linear.PostLinearDto;
import com.siy.siyresource.domain.dto.post.PostDto;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.repository.PostRepository.PostRepository;
import com.siy.siyresource.service.ApplicationService;
import com.siy.siyresource.service.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Commit
@WebAppConfiguration
class ApplicationRepositoryImplTest {




    ApplicationRepository applicationRepository;
    PostService postService;
    ApplicationService applicationService;
    PostRepository postRepository;
    EntityManager em;
    PostApiController controller;

    @Autowired
    public ApplicationRepositoryImplTest(ApplicationRepository applicationRepository, PostService postService, ApplicationService applicationService, PostRepository postRepository, EntityManager em, PostApiController controller) {
        this.applicationRepository = applicationRepository;
        this.postService = postService;
        this.applicationService = applicationService;
        this.postRepository = postRepository;
        this.em = em;
        this.controller = controller;
    }

    @Test
    @Transactional
    public void findApplcationTest(){
        Application findApplication = applicationRepository.findByUserName("admin", 10L);
        System.out.println("findApplication = " + findApplication);
        Long id = 10L;

        PostSearchCondition condition = new PostSearchCondition(id, "", "");
        Post before = postRepository.findPostWithAppById(condition);
        System.out.println("before = " + before);

        em.flush();
        em.clear();

        applicationRepository.delete(findApplication);

        Post after = postRepository.findPostWithAppById(condition);
        System.out.println("after = " + after);
    }

    @Test
    public void findMakePost(){
        PostSearchCondition condition = new PostSearchCondition(null, "user", "");
        Page<PostLinearDto> result = postService.findPostListByUsername(condition, 0, 10);
        System.out.println("result = " + result.getContent());
    }
    @Test
    public void findPostListByApplicationUserName(){
        PostSearchCondition condition = new PostSearchCondition(null, "admin", "");
        Page<PostLinearDto> result = postService.findPostListByApplicationUserName(condition, 0, 10);
        System.out.println("result = " + result.getContent());
    }


    @Test
    public void findPostListByParticipants(){
        PostSearchCondition condition = new PostSearchCondition(null, "", "user");
        Page<PostLinearDto> result = postService.findPostListByParticipants(condition, 0, 10);
        System.out.println("result = " + result.getContent());
    }

    @Test
    public void delete(){
        postRepository.deleteById(10L);
    }

    @Test
    public void select(){
        PageRequest page = PageRequest.of(0, 10);
        Page<PostDto> result = postRepository.findPostListWithPaging(page);
        System.out.println("result = " + result);
    }

}