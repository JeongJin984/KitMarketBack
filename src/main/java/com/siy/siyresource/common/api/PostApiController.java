package com.siy.siyresource.common.api;

import com.siy.siyresource.common.api.request.*;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.PostingDetail.*;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.Linear.PostLinearDto;
import com.siy.siyresource.service.ApplicationService;
import com.siy.siyresource.service.post.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;
    private final ApplicationService applicationService;
    /**
     * 1. Post 전체 조회 o
     */
    @GetMapping(value = "/api/postList")
    public Result postList(@RequestParam(value = "status", defaultValue = "POSTING", required = false)String status,
                           @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                           @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostDto> result = postService.findPostList(status, offset, size);


        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 2. Study 전체 조회 o
     */
    @GetMapping(value = "/api/studyList")
    public Result studyList(@RequestParam(value = "status", defaultValue = "POSTING", required = false)String status,
                            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                            @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostDto> result = postService.findStudyList(status, offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 3. carPool 전체 조회 o
     */
    @GetMapping(value = "/api/carPoolList")
    public Result carPoolList(
                                @RequestParam(value = "status", defaultValue = "POSTING", required = false)String status,
                                @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostDto> result = postService.findCarPoolList(status, offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 4. Contest 전체 조회 0
     */
    @GetMapping(value = "/api/contestList")
    public Result ContestList(
                                @RequestParam(value = "status", defaultValue = "POSTING", required = false)String status,
                                @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostDto> result = postService.findContestList(status, offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.get());
    }

    /**
     * 5. post 한개 조회 o
     */
    @GetMapping(value = "/api/post")
    public PostDtoPostingDetail PostOne(@RequestParam(value = "id") Long id) {

        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        PostDtoPostingDetail findPostDetail = postService.findPostById(condition);

        return findPostDetail;
    }

    /**
     * 6. study 한개 조회 o
     */
    @GetMapping(value = "/api/study")
    public StudyDtoPostingDetail StudyOne(@RequestParam(value = "id") Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        StudyDtoPostingDetail findPostDetail = postService.findStudyById(condition);

        return findPostDetail;
    }

    /**
     * 7.carFool 한개 조회 o
     */
    @GetMapping(value = "/api/carPool")
    public PostDtoPostingDetail carFoolOne(@RequestParam(value = "id") Long id) {

        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        CarPoolDtoPostingDetail findPostDetail = postService.findCarFoolById(condition);

        return findPostDetail;
    }

    /**
     * 8. contest 한개 조회 o
     */
    @GetMapping(value = "/api/contest")
    public PostDtoPostingDetail contestOne(@RequestParam(value = "id") Long id) {

        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        ContestDtoPostingDetail findPostDetail = postService.findContestById(condition);

        return findPostDetail;
    }

    /**
     * 9. miniProject 한개 조회
     */
    @GetMapping(value = "/api/miniProject")
    public PostDtoPostingDetail miniProjectOne(@RequestParam(value = "id") Long id) {

        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        MiniProjectDtoPostingDetail findPostDetail = postService.findMiniProjectById(condition);

        return findPostDetail;
    }

    /**
     * 10. Post 저장 o
     */
    @PostMapping(value = "/api/post")
    public String savePost(@RequestBody @Valid CreatePostRequest request) {
        System.out.println("request = " + request);
        postService.postSave(request);
        return "redirect:/";
    }

    /**
     * 11. Contest 저장 0
     */
    @PostMapping(value = "/api/contest")
    public String saveContest(@RequestBody @Valid CreateContestRequest request) {
        System.out.println("request = " + request);

        postService.contestSave(request);
        return "redirect:/";
    }


    /**
     * 12. CarPool 저장 0
     */
    @PostMapping(value = "/api/carPool")
    public String saveCarFool(@RequestBody @Valid CreateCarPoolRequest request) {

        postService.carPoolSave(request);
        return "redirect:/";
    }

    /**
     * 13. Study 저장 0
     */
    @PostMapping(value = "/api/study")
    public String saveStudy(@RequestBody @Valid CreateStudyRequest request) {
        System.out.println("request = " + request);
        postService.studySave(request);
        return "redirect:/";
    }

    /**
     * 14. MiniProject 저장
     */
    @PostMapping(value = "/api/miniProject")
    public String saveStudy(@RequestBody @Valid CreateMiniProjectRequest request) {
        System.out.println("request = " + request);
        postService.studyMiniProject(request);
        return "redirect:/";
    }


    /**
     * 13. 내가 만든 모임 리스트 0
     * */
    @GetMapping("/api/post/my")
    public Result findPostMyMakeByUsername(@RequestParam(value = "username") @Valid String request,
                                           @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                           @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        System.out.println("내가 만든 모임 리스트");
        PostSearchCondition condition = new PostSearchCondition(null, request, null);

        Page<PostLinearDto> result = postService.findPostListByUsername(condition, offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 15. 내가 참여중인 post 보기 0
     */
    @GetMapping(value = "/api/post/participant")
    public Result findParticipating(@RequestParam String username,
                                    @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                    @RequestParam(value = "size", defaultValue = "8", required = false) int size){
        Page<PostLinearDto> result = postService.findParticipatingList(username, offset, size);


        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.get());
    }


    /**
     * 16 . 내가 신청한 모임 리스트 0
     * */
    @GetMapping("/api/post/application")
    public Result findPostApplicatingByUsername(@RequestParam(value = "username") @Valid String request,
                                                @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        System.out.println("내가 신청한 모임 리스트");

        PostSearchCondition condition = new PostSearchCondition(null, request, null);

        Page<PostLinearDto> result = postService.findPostListByApplicationUserName(condition, offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 17. 포스트 삭제 0
     */
    @DeleteMapping(value = "/api/post")
    public String delete(@RequestParam("id") Long id){
        postService.deleteById(id);

        return "redirect:/";
    }


    /**
     * 18. Post 수정 0
     */
    @PutMapping(value = "/api/post")
    public String updatePost(@RequestBody @Valid CreatePostRequest request, @PathVariable("id")Long id){
        postService.updatePost(id, request);

        return "redirect:/";
    }

    /**
     *  19. Contest 수정 0
     * */
    @PutMapping(value = "/api/contest")
    public String updateContest(@RequestBody @Valid CreateContestRequest request, @RequestParam("id")Long id){
        postService.updateContest(id, request);

        return "redirect:/";
    }

    /**
     *  20. Study 수정 0
     * */
    @PutMapping(value = "/api/study")
    public String updateStudy(@RequestBody @Valid CreateStudyRequest request, @RequestParam("id")Long id){
        postService.updateStudy(id, request);

        return "redirect:/";
    }
    /**
     *  21. carPool 수정 0
     * */
    @PutMapping(value = "/api/carPool")
    public String updateCarFool(@RequestBody @Valid CreateCarPoolRequest request, @RequestParam("id")Long id){
        postService.updateCarFool(id, request);

        return "redirect:/";
    }
    /**
     * MiniProject 수정
     */
    @PutMapping(value = "/api/miniProject")
    public String updateCarFool(@RequestBody @Valid CreateMiniProjectRequest request, @RequestParam("id")Long id){
        postService.updateMiniProject(id, request);

        return "redirect:/";
    }


    /**
     *  22. 검색기능 0
     * @param title
     * @param username
     */
    @GetMapping("/api/post/search")
    public Result  searchPostByKeyword( @RequestParam(value = "title",required = false) String title,
                                        @RequestParam(value = "username", required = false) String username,
                                        @RequestParam(value = "status",  required = false, defaultValue = "POSTING") String status,
                                        @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                        @RequestParam(value = "size", defaultValue = "8", required = false) int size ){

        Page<PostDto> result = postService.findSearchList(title, username, status, offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }



    /**
     *  23. 포스트 운영 마감하기 0
     * */
    @GetMapping(value = "/api/post/closed")
    public String closedPost(@RequestParam(required = true) @Valid Long id){
        postService.closedPost(id);

        return "redirect:/";
    }
    /**
     * 참석자 보기
     */



}

@Data
@AllArgsConstructor
class Result<T> {
    private int size;
    private int currentPage;
    private int maxPage;
    private T data;
}


@Data
class PostRequest{
    private String username;
    private String content;
}

@Data
class MyRequest{
    private String username;
}





