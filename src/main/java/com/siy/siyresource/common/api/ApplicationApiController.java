package com.siy.siyresource.common.api;

import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.Participant;
import com.siy.siyresource.domain.entity.post.Post;
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
    /**
     * 함께하기
     */
    @PostMapping("/api/app/join")
    public void JoinPost(@RequestBody @Valid ApplicationRequest request, @RequestParam(value = "postId") Long id){
        System.out.println("JoinPost Application");
        System.out.println("request = " + request);

        // protected로 바꾸기
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post findPost = postService.getPostEntity(condition);
        System.out.println("findPost = " + findPost);



        Application application = new Application(request.getContent(), request.getUsername(), findPost);
        Long save = applicationService.save(application);
    }

    /**
     * 취소하기
     */
    @DeleteMapping("/api/app/cancle")
    public String JoinPost(@RequestBody @Valid CancleAppRequest request, @RequestParam(value = "postId") Long id){
        applicationService.deleteByPostIdAndUserName(id, request.getUsername());
        return "redirect:/";
    }

    /**
     * 수정하기
     */
    @PutMapping("/api/app/update/{id}")
    public String UpdateApp(@RequestBody @Valid ApplicationRequest request, @PathVariable Long postId){
        Application findApp = applicationService.findByUsernameAndPostId(request.getUsername(), postId);
        applicationService.updateApp(findApp, request.getContent());

        return "redirect:/";
    }

}
@Data
class ApplicationRequest{
    private String username;
    private String content;
}

@Data
class CancleAppRequest{
    String username;
}

