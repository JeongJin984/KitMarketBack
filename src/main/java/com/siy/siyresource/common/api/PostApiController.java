package com.siy.siyresource.common.api;

import com.siy.siyresource.common.api.request.CreatePostRequest;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.account.UserDto;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.post.Linear.PostLinearDto;
import com.siy.siyresource.domain.dto.post.detail.CarPoolDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.ContestDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.PostDtoDetail;
import com.siy.siyresource.domain.entity.post.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study.Study;
import com.siy.siyresource.feign.AccountServiceClient;
import com.siy.siyresource.service.ApplicationService;
import com.siy.siyresource.service.account.AccountService;
import com.siy.siyresource.service.post.PostService;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostApiController {
    private final PostService postService;
    private final AccountService accountService;
    private final ApplicationService applicationService;

    @GetMapping("hello/hi")
    @Timed(value = "post.hi", longTask = true)
    public UserDto getProfile() {
        return accountService.getUser("user");
    }

    /**
     * Post 전체 조회
     */
    @GetMapping(value = "/api/postList")
    public Result postList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                           @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostDto> result = postService.findPostList(offset, size);


        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * Study 전체 조회
     */
    @GetMapping(value = "/api/studyList")
    public Result studyList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                            @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<StudyDto> result = postService.findStudyList(offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * carPool 전체 조회
     */
    @GetMapping(value = "/api/carPoolList")
    public Result carPoolList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                              @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<CarPoolDto> result = postService.findCarPoolList(offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * Contest 전체 조회
     */
    @GetMapping(value = "/api/contestList")
    public Result ContestList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                              @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<ContestDto> result = postService.findContestList(offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.get());
    }


    /**
     * 참여중인 post 보기
     */
    @GetMapping(value = "/api/participating")
    public Result findParticipating(@RequestParam String username,
                                    @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                    @RequestParam(value = "size", defaultValue = "8", required = false) int size){

        Page<PostLinearDto> result = postService.findParticipatingList(username, offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.get());
    }



    /**
     * post Linear 버전 출력
     */
    @GetMapping(value = "/api/postLinear")
    public Result postLinearList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                 @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostLinearDto> result = postService.findPostLinearList(offset, size);


        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.get());
    }


    /**
     * post 한개 조회
     */
    @GetMapping(value = "/api/post")
    public PostDtoDetail PostOne(@RequestParam(value = "id") Long id) {

        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        PostDtoDetail findPostDetail = postService.findPostById(condition);

        return findPostDetail;
    }

    /**
     * study 한개 조회
     */
    @GetMapping(value = "/api/study")
    public PostDtoDetail StudyOne(@RequestParam(value = "id") Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        PostDtoDetail findPostDetail = postService.findStudyById(condition);

        return findPostDetail;
    }

    /**
     * contest 한개 조회
     */
    @GetMapping(value = "/api/contest")
    public PostDtoDetail contestOne(@RequestParam(value = "id") Long id) {

        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        ContestDtoDetail findPostDetail = postService.findContestById(condition);

        return findPostDetail;
    }

    /**
     * carFool 한개 조회
     */
    @GetMapping(value = "/api/carPool")
    public PostDtoDetail carFoolOne(@RequestParam(value = "id") Long id) {

        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        CarPoolDtoDetail findPostDetail = postService.findCarFoolById(condition);

        return findPostDetail;
    }

    /**
     * Post 저장
     */
    @PostMapping(value = "/api/post")
    public String savePost(@RequestBody @Valid CreatePostRequest request) {
        System.out.println("request = " + request);
        Post post = new Post();
        PostRequestToPostEntity(post, request);

        postService.save(post);
        return "redirect:/";
    }

    /**
     * Contest 저장
     */
    @PostMapping(value = "/api/contest")
    public String saveContest(@RequestBody @Valid CreatePostRequest request) {
        System.out.println("request = " + request);
        Contest post = new Contest();
        PostRequestToContestEntity(post, request);

        postService.save(post);
        return "redirect:/";
    }

    private void PostRequestToContestEntity(Contest contest, CreatePostRequest request) {
        PostRequestToPostEntity(contest, request);
    }

    /**
     * CarPool 저장
     */
    @PostMapping(value = "/api/carPool")
    public String saveCarFool(@RequestBody @Valid CreatePostRequest request) {
        CarPool post = new CarPool();
        PostRequestToCarFoolEntity(post, request);

        postService.save(post);
        return "redirect:/";
    }

    private void PostRequestToCarFoolEntity(CarPool post, CreatePostRequest request) {
        PostRequestToPostEntity(post, request);
    }

    /**
     * Study 저장
     */
    @PostMapping(value = "/api/study")
    public String saveStudy(@RequestBody @Valid CreatePostRequest request) {
        System.out.println("request = " + request);
        Study post = new Study();
        PostRequestToStudyEntity(post, request);

        postService.save(post);
        return "redirect:/";
    }

    private void PostRequestToStudyEntity(Study post, CreatePostRequest request) {
        PostRequestToPostEntity(post, request);
    }

    /**
     * Post 삭제
     */
    @DeleteMapping(value = "/api/post")
    public String delete(@RequestParam("id") Long id){
        postService.deleteById(id);

        return "redirect:/";
    }

    /**
     * Post 수정
     */
    @PutMapping(value = "/api/post")
    public String updatePost(@RequestBody @Valid CreatePostRequest request, @PathVariable("id")Long id){
        postService.updatePost(id, request);

        return "redirect:/";
    }

    /**
     *  Contest 수정
     * */
    @PutMapping(value = "/api/contest")
    public String updateContest(@RequestBody @Valid CreatePostRequest request, @RequestParam("id")Long id){
        postService.updateContest(id, request);

        return "redirect:/";
    }

    /**
     *  Study 수정
     * */
    @PutMapping(value = "/api/study")
    public String updateStudy(@RequestBody @Valid CreatePostRequest request, @RequestParam("id")Long id){
        postService.updateStudy(id, request);

        return "redirect:/";
    }
    /**
     *  carPool 수정
     * */
    @PutMapping(value = "/api/carPool")
    public String updateCarFool(@RequestBody @Valid CreatePostRequest request, @RequestParam("id")Long id){
        postService.updatecarFool(id, request);

        return "redirect:/";
    }


    /**
     * 내가 만든 모임 리스트
     * */
    @GetMapping("/api/post/my")
    public Result findPostMyMakeByUsername(@RequestParam(value = "username") @Valid String request,
                                                           @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                           @RequestParam(value = "size", defaultValue = "8", required = false) int size) {

        PostSearchCondition condition = new PostSearchCondition(null, request, null);

        Page<PostLinearDto> result = postService.findPostListByUsername(condition, offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }


    /**
     * 내가 신청한 모임 리스트
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
     * 내가 참여하고 있는 모임 리스트
     */
    @GetMapping("/api/post/participants")
    public Result findPostparticipantsByUsername(@RequestParam(value = "username") @Valid String request,
                                                 @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                 @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        System.out.println("내가 참여하고 있는 모임 리스트");
        PostSearchCondition condition = new PostSearchCondition(null, null, request);
        Page<PostLinearDto> result = postService.findPostListByParticipants(condition, offset, size);

        Result result1 = new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
        return result1;
    }


    /**
     * 포스팅 중인 모든 포스트 리스트
     */
    @GetMapping("/api/post/postingList")
    public Result findPostingList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                  @RequestParam(value = "size", defaultValue = "8", required = false) int size ){

        Page<PostDto> result = postService.findPostingList(offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 종료된 모든 포스트 리스트
     */
    @GetMapping("/api/post/closedList")
    public Result findClosedList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                  @RequestParam(value = "size", defaultValue = "8", required = false) int size ){

        Page<PostDto> result = postService.findClosedList(offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     *  검색기능
     * @param title
     * @param username
     */
    @GetMapping("/api/post/search")
    public Result  searchPostByKeyword( @RequestParam(value = "title",required = false) String title,
                                        @RequestParam(value = "username", required = false) String username,
                                        @RequestParam(value = "status",  required = true) String status,
                                        @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                       @RequestParam(value = "size", defaultValue = "8", required = false) int size ){
//
//        System.out.println("title = " + title);
//        System.out.println("username = " + username);

        Page<PostDto> result = postService.findSearchList(title, username, status, offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 포스팅 마감하기
     */
    @GetMapping(value = "/api/post/operating")
    public String operatingPost(@RequestParam(required = true) @Valid Long id) {
        postService.operatingPost(id);

        return "redirect:/";
    }

    /**
     *  포스트 운영 종료하기
     * */
    @GetMapping(value = "/api/post/closed")
    public String closedPost(@RequestParam(required = true) @Valid Long id){
        postService.closedPost(id);

        return "redirect:/";
    }



    private Post PostRequestToPostEntity(Post post, CreatePostRequest request) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime deadLine = LocalDateTime.parse(request.getDeadLine(), format);

        post.setWriter(request.getWriter());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setDeadLine(deadLine);
        post.setMaxNumber(request.getMaxNum());
        post.setCurrentNumber(request.getCurNum());
        post.setCategory(request.getCategory());
        System.out.println("post = " + post);

        return post;
    }
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





