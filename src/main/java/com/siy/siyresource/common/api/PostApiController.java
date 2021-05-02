package com.siy.siyresource.common.api;



import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.post.Linear.PostLinearDto;
import com.siy.siyresource.domain.dto.post.detail.CarFoolDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.ContestDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.PostDtoDetail;
import com.siy.siyresource.domain.entity.*;
import com.siy.siyresource.domain.entity.post.Post;
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


    /**
     * Post 전체 조회
     */
    @GetMapping(value = "/api/postList")
    public Result postList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                           @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostDto> result = (Page<PostDto>) postService.findPostList(offset, size);

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
        Post post = PostRequestToPostEntity(request);

        postService.save(post);
        return "redirect:/";
    }

    @PostMapping("/api/post/join")
    public void JoinPost(@RequestBody @Valid JoinPostRequest request, @RequestParam(value = "postId") Long id){
        System.out.println("id = " + id);
        System.out.println("request = " + request);


        // protected로 바꾸기
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post findPost = postService.getPostEntity(condition);
        System.out.println("findPost = " + findPost);


        Application application = new Application(request.getUsername(), findPost);
        Long save = applicationService.save(application);
        System.out.println("save = " + save);

    }
    @DeleteMapping("/api/post/cancle")
    public void JoinPost(@RequestBody @Valid CanclePostRequest request, @RequestParam(value = "postId") Long id){
        System.out.println("id = " + id);
        System.out.println("request = " + request);


        // protected로 바꾸기
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post findPost = postService.getPostEntity(condition);
        System.out.println("findPost = " + findPost);







    }

    private Post PostRequestToPostEntity(CreatePostRequest request) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime deadLine = LocalDateTime.parse(request.getDeadLine(), format);

        // 쓰고 생성자 protected로 바꿀것!
        Post post = new Post();

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
class CreatePostRequest{
    private String writer;
    private String title;
    private String content;
    private String deadLine;
    private String createdAt;
    private Integer maxNum;
    private Integer curNum;
    private String category;
}
@Data
class JoinPostRequest{
    private String username;
}

@Data
class CanclePostRequest{
    private String username;
}






