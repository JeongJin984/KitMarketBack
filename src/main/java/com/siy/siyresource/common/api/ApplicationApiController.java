package com.siy.siyresource.common.api;

import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.account.Account;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.repository.AccountRepository.AccountRepository;
import com.siy.siyresource.service.ApplicationService;
import com.siy.siyresource.service.post.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
public class ApplicationApiController {
    private final ApplicationService applicationService;
    private final PostService postService;
    private final AccountRepository accountRepository;
    /**
     * 함께하기
     */
    @PostMapping("/api/app/join")
    public void JoinPost(@RequestBody @Valid PostRequest request, @RequestParam(value = "postId") Long id){
        System.out.println("id = " + id);
        System.out.println("request = " + request);

        // protected로 바꾸기
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post findPost = postService.getPostEntity(condition);
        System.out.println("findPost = " + findPost);

        Account findAccount = accountRepository.findByUsername(request.getUsername());


        Application application = new Application(request.getContent(), findAccount, findPost);
        Long save = applicationService.save(application);

        System.out.println("save = " + save);
    }

    /**
     * 취소하기
     */
    @DeleteMapping("/api/app/cancle")
    public String JoinPost(@RequestBody @Valid CanclePostRequest request, @RequestParam(value = "postId") Long id){
        applicationService.deleteById(id);

        return "redirect:/";
    }

    /**
     * 수정하기
     */
    @PutMapping("/api/app/update/{id}")
    public String UpdateApp(@RequestBody @Valid UpdateAppRequest request, @PathVariable Long postId){
        Application findApp = applicationService.findByUsernameAndPostId(request.getUsername(), postId);
        updateApp(findApp, request);

        return "redirect:/";
    }

    private void updateApp(Application findApp, UpdateAppRequest request) {
        findApp.setContent(request.getContent());
    }

    @Data
    private class UpdateAppRequest {
        String content;
        String username;
    }
}
