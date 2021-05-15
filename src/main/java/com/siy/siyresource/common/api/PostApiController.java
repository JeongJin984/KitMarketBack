package com.siy.siyresource.common.api;

import com.siy.siyresource.common.api.request.CreatePostRequest;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.post.Linear.PostLinearDto;
import com.siy.siyresource.domain.dto.post.detail.CarFoolDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.ContestDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.PostDtoDetail;
import com.siy.siyresource.domain.entity.post.CarFull;
import com.siy.siyresource.domain.entity.post.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study;
import com.siy.siyresource.repository.AccountRepository.AccountRepository;
import com.siy.siyresource.service.ApplicationService;
import com.siy.siyresource.service.post.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;
    private final ApplicationService applicationService;
    private final AccountRepository accountRepository;

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
     * carFull 전체 조회
     */
    @GetMapping(value = "/api/carFoolList")
    public Result carFullList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                              @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<CarFullDto> result = postService.findCarFulList(offset, size);

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
    @GetMapping(value = "/api/carFool")
    public PostDtoDetail carFoolOne(@RequestParam(value = "id") Long id) {

        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        CarFoolDtoDetail findPostDetail = postService.findCarFoolById(condition);

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
        Contest post = Contest.CreateContest();
        PostRequestToContestEntity(post, request);

        postService.save(post);
        return "redirect:/";
    }

    private void PostRequestToContestEntity(Contest contest, CreatePostRequest request) {
        PostRequestToPostEntity(contest, request);
    }

    /**
     * CarFool 저장
     */
    @PostMapping(value = "/api/carfool")
    public String saveCarFool(@RequestBody @Valid CreatePostRequest request) {
        CarFull post = CarFull.CreateCarFool();
        PostRequestToCarFoolEntity(post, request);

        postService.save(post);
        return "redirect:/";
    }

    private void PostRequestToCarFoolEntity(CarFull post, CreatePostRequest request) {
        PostRequestToPostEntity(post, request);
    }

    /**
     * Study 저장
     */
    @PostMapping(value = "/api/study")
    public String saveStudy(@RequestBody @Valid CreatePostRequest request) {
        Study post = Study.CreateStudy();
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
    @DeleteMapping(value = "/api/post/{id}")
    public String delete(@PathVariable("id") Long id){
        postService.deleteById(id);

        return "redirect:/";
    }

    /**
     * Post 수정
     */
    @PutMapping(value = "/api/post/{id}")
    public String updatePost(@RequestBody @Valid CreatePostRequest request, @PathVariable("id")Long id){
        postService.updatePost(id, request);

        return "redirect:/";
    }

    /**
     *  Contest 수정
     * */
    @PutMapping(value = "/api/contest/{id}")
    public String updateContest(@RequestBody @Valid CreatePostRequest request, @PathVariable("id")Long id){
        postService.updateContest(id, request);

        return "redirect:/";
    }

    /**
     *  Study 수정
     * */
    @PutMapping(value = "/api/study/{id}")
    public String updateStudy(@RequestBody @Valid CreatePostRequest request, @PathVariable("id")Long id){
        postService.updateStudy(id, request);

        return "redirect:/";
    }
    /**
     *  carFool 수정
     * */
    @PutMapping(value = "/api/carFool/{id}")
    public String updateCarFool(@RequestBody @Valid CreatePostRequest request, @PathVariable("id")Long id){
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
        System.out.println("내가 만든 모임 리스트");
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


        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
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

@Data
class CanclePostRequest{
    private String username;
}






