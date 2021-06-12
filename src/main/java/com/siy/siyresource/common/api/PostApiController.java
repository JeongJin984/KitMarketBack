package com.siy.siyresource.common.api;

import com.siy.siyresource.common.api.request.*;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.AllDetail.PostDtoAllDetail;
import com.siy.siyresource.domain.dto.ClosedDetail.*;
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
    private final ApplicationService       applicationService;
    /**
     * 1. Post 전체 조회 o
     */
    @GetMapping(value = "/api/postList")
    public Result postList(@RequestParam(value = "status", defaultValue = "POSTING", required = false)String status,
                           @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                           @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        System.out.println("/api/postList?stauts="+status);
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
        System.out.println("/api/studyList?stauts="+status);
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
        System.out.println("/api/carPool?stauts="+status);
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
        System.out.println("/api/contestList?stauts="+status);
        Page<PostDto> result = postService.findContestList(status, offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.get());
    }

    /**
     * 5. MiniProject 전체 조회 0
     */
    @GetMapping(value = "/api/miniProjectList")
    public Result MiniProjectList(
            @RequestParam(value = "status", defaultValue = "POSTING", required = false)String status,
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
            @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        System.out.println("/api/miniProject?stauts="+status);
        Page<PostDto> result = postService.findMiniProjectList(status, offset, size);

        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.get());
    }

    /**
     * 6. post 한개 조회 o
     */
    @GetMapping(value = "/api/post")
    public PostDtoPostingDetail PostOne(@RequestParam(value = "id") Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        System.out.println("/api/post?id" + id);
        PostDtoPostingDetail findPostDetail = postService.findPostById(condition);

        return findPostDetail;
    }

    /**
     * 7. study 한개 조회 o
     */
    @GetMapping(value = "/api/study")
    public StudyDtoPostingDetail StudyOne(@RequestParam(value = "id") Long id) {
        System.out.println("/api/study?id" + id);
        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        StudyDtoPostingDetail findPostDetail = postService.findStudyById(condition);

        return findPostDetail;
    }

    /**
     *  8.carFool 한개 조회 o
     */
    @GetMapping(value = "/api/carPool")
    public CarPoolDtoPostingDetail carFoolOne(@RequestParam(value = "id") Long id) {
        System.out.println("/api/carPool?id" + id);
        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        CarPoolDtoPostingDetail findPostDetail = postService.findCarFoolById(condition);

        return findPostDetail;
    }

    /**
     * 9. contest 한개 조회 o
     */
    @GetMapping(value = "/api/contest")
    public ContestDtoPostingDetail contestOne(@RequestParam(value = "id") Long id) {
        System.out.println("/api/contest?id" + id);
        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        ContestDtoPostingDetail findPostDetail = postService.findContestById(condition);

        return findPostDetail;
    }

    /**
     * 10. miniProject 한개 조회
     */
    @GetMapping(value = "/api/miniProject")
    public MiniProjectDtoPostingDetail miniProjectOne(@RequestParam(value = "id") Long id) {
        System.out.println("/api/miniProject?id" + id);
        PostSearchCondition condition = new PostSearchCondition(id, null, null);

        MiniProjectDtoPostingDetail findPostDetail = postService.findMiniProjectById(condition);

        return findPostDetail;
    }

    /**
     * 11. Post 저장 o
     */
    @PostMapping(value = "/api/post")
    public String savePost(@RequestBody @Valid CreatePostRequest request) {
        System.out.println("/api/post");
        System.out.println("request = " + request);
        postService.postSave(request);
        return "redirect:/";
    }

    /**
     * 11. Contest 저장 0
     */
    @PostMapping(value = "/api/contest")
    public String saveContest(@RequestBody @Valid CreateContestRequest request) {
        System.out.println("/api/contest");
        System.out.println("request = " + request);
        postService.contestSave(request);
        return "redirect:/";
    }


    /**
     * 13. CarPool 저장 0
     */
    @PostMapping(value = "/api/carPool")
    public String saveCarFool(@RequestBody @Valid CreateCarPoolRequest request) {
        System.out.println("/api/carPool");
        System.out.println("request = " + request);
        postService.carPoolSave(request);
        return "redirect:/";
    }

    /**
     * 14. Study 저장 0
     */
    @PostMapping(value = "/api/study")
    public String saveStudy(@RequestBody @Valid CreateStudyRequest request) {
        System.out.println("/api/study");
        System.out.println("request = " + request);
        postService.studySave(request);
        return "redirect:/";
    }

    /**
     * 15. MiniProject 저장
     */
    @PostMapping(value = "/api/miniProject")
    public String saveStudy(@RequestBody @Valid CreateMiniProjectRequest request) {
        System.out.printf("/api/miniProject");
        System.out.println("request = " + request);
        postService.studyMiniProject(request);
        return "redirect:/";
    }


    /**
     * 16. 내가 만든 모임 리스트 0
     * */
    @GetMapping("/api/post/my")
    public Result findPostMyMakeByUsername(@RequestParam(value = "username") @Valid String request,
                                           @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                           @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        System.out.printf("/api/post/my");
        PostSearchCondition condition = new PostSearchCondition(null, request, null);

        Page<PostLinearDto> result = postService.findPostListByUsername(condition, offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 17. 내가 참여중인 post 보기 0
     */
    @GetMapping(value = "/api/post/participant")
    public Result findParticipating(@RequestParam String username,
                                    @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                    @RequestParam(value = "size", defaultValue = "8", required = false) int size){
        System.out.printf("/api/post/participant");
        Page<PostLinearDto> result = postService.findParticipatingList(username, offset, size);


        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.get());
    }


    /**
     * 18 . 내가 신청한 모임 리스트 0
     * */
    @GetMapping("/api/post/application")
    public Result findPostApplicatingByUsername(@RequestParam(value = "username") @Valid String username,
                                                @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        System.out.printf("/api/post/application?username="+username);
        System.out.println("내가 신청한 모임 리스트");

        PostSearchCondition condition = new PostSearchCondition(null, username, null);

        Page<PostLinearDto> result = postService.findPostListByApplicationUserName(condition, offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    /**
     * 19. 포스트 삭제 0
     */
    @DeleteMapping(value = "/api/post")
    public String delete(@RequestParam("id") Long id){
        System.out.printf("삭제 /api/post?id"+id);
        postService.deleteById(id);

        return "redirect:/";
    }


    /**
     * 20. Post 수정 0
     */
    @PutMapping(value = "/api/post")
    public String updatePost(@RequestBody @Valid CreatePostRequest request, @PathVariable("id")Long id){
        System.out.printf("수정 /api/post?id"+id);
        postService.updatePost(id, request);

        return "redirect:/";
    }

    /**
     *  21. Contest 수정 0
     * */
    @PutMapping(value = "/api/contest")
    public String updateContest(@RequestBody @Valid CreateContestRequest request, @RequestParam("id")Long id){
        System.out.printf("삭제 /api/contest?id"+id);
        postService.updateContest(id, request);

        return "redirect:/";
    }

    /**
     *  22. Study 수정 0
     * */
    @PutMapping(value = "/api/study")
    public String updateStudy(@RequestBody @Valid CreateStudyRequest request, @RequestParam("id")Long id){
        System.out.printf("삭제 /api/study?id"+id);
        postService.updateStudy(id, request);

        return "redirect:/";
    }
    /**
     *  23. carPool 수정 0
     * */
    @PutMapping(value = "/api/carPool")
    public String updateCarFool(@RequestBody @Valid CreateCarPoolRequest request, @RequestParam("id")Long id){
        System.out.printf("삭제 /api/carPool?id"+id);
        postService.updateCarFool(id, request);

        return "redirect:/";
    }
    /**
     * 24. MiniProject 수정
     */
    @PutMapping(value = "/api/miniProject")
    public String updateCarFool(@RequestBody @Valid CreateMiniProjectRequest request, @RequestParam("id")Long id){
        System.out.printf("삭제 /api/miniProject?id"+id);
        postService.updateMiniProject(id, request);

        return "redirect:/";
    }


    /**
     *  25. 검색기능 0
     */
    @GetMapping("/api/post/search/title")
    public Result  searchPostByTitleKeyword( @RequestParam(value = "key",required = false) String title,
                                        @RequestParam(value = "status",  required = false, defaultValue = "POSTING") String status,
                                        @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                        @RequestParam(value = "size", defaultValue = "8", required = false) int size ){

        System.out.println("/api/post/search/title = " + title);

        Page<PostDto> result = postService.findSearchList(title, null, status, offset, size);
        System.out.println("result.getContent() = " + result.getContent());
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }

    @GetMapping("/api/post/search/username")
    public Result  searchPostByUsernameKeyword(
                                        @RequestParam(value = "key", required = false) String username,
                                        @RequestParam(value = "status",  required = false, defaultValue = "POSTING") String status,
                                        @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                        @RequestParam(value = "size", defaultValue = "8", required = false) int size ){
        System.out.println("username = " + username);

        Page<PostDto> result = postService.findSearchList(null, username, status, offset, size);
        return new Result(result.getContent().size(), result.getNumber(), result.getTotalPages(), result.getContent());
    }



    /**
     *  26. 포스트 운영 마감하기 0
     * */
    @GetMapping(value = "/api/post/closed")
    public String closedPost(@RequestParam(required = true) @Valid Long id){
        System.out.println("/api/post/closed?id="+id);
        postService.closedPost(id);

        return "redirect:/";
    }

    /**
     * 31. 참석자 보기
     */
    @GetMapping(value = "/api/post/participanting")
    public PostDtoClosedDetail postWithParticipants(@RequestParam @Valid Long id){
        System.out.println("/api/post/participanting?id = " + id);
        PostDtoClosedDetail postDtoClosedDetail = postService.findPostWithParticipants(id);

        return postDtoClosedDetail;
    }

    /**
     * 32. 스터디 참석자 보기
     */
    @GetMapping(value = "/api/study/participanting")
    public StudyDtoClosedDetail studyWithParticipants(@RequestParam @Valid Long id){
        System.out.println("/api/study/participanting?id = " + id);
        StudyDtoClosedDetail postDtoClosedDetail = postService.findStudyWithParticipants(id);

        return postDtoClosedDetail;
    }

    /**
     * 33. 카풀 참석자 보기
     */
    @GetMapping(value = "/api/carPool/participanting")
    public CarPoolDtoClosedDetail carPoolWithParticipants(@RequestParam @Valid Long id){
        System.out.println("/api/carPool/participanting?id = " + id);
        CarPoolDtoClosedDetail postDtoClosedDetail = postService.findcarPoolWithParticipants(id);

        return postDtoClosedDetail;
    }

    /**
     * 34. 콘테스트 참석자 보기
     */
    @GetMapping(value = "/api/contest/participanting")
    public ContestDtoClosedDetail contestWithParticipants(@RequestParam @Valid Long id){
        System.out.println("/api/contest/participanting?id = " + id);
        ContestDtoClosedDetail postDtoClosedDetail = postService.findContestWithParticipants(id);

        return postDtoClosedDetail;
    }

    /**
     * 35. 미니프로젝트 참석자 보기
     */
    @GetMapping(value = "/api/miniProject/participanting")
    public MiniProjectDtoClosedDetail miniWithParticipants(@RequestParam @Valid Long id){
        System.out.println("/api/contest/participanting?id = " + id);
        MiniProjectDtoClosedDetail postDtoClosedDetail = postService.findMiniProjectWithParticipants(id);

        return postDtoClosedDetail;
    }

    /**
     * 36 Post, app, part 전부
     */
    @GetMapping(value = "/api/post/all")
    public PostDtoAllDetail postDtoAllDetail(@RequestParam @Valid Long id){
        System.out.println("api/post/all?id = " + id);
        return postService.findPostWithAppAndPart(id);
    }
    /**
     * 37 CarPool app, part 전부
     */
    @GetMapping(value = "/api/carPool/all")
    public PostDtoAllDetail carPoolDtoAllDetail(@RequestParam @Valid Long id){
        System.out.println("api/carPool/all?id = " + id);
        return postService.findCarPoolWithAppAndPart(id);
    }
    /**
     * 38 app, part 전부
     */
    @GetMapping(value = "/api/study/all")
    public PostDtoAllDetail studyDtoAllDetail(@RequestParam @Valid Long id){
        System.out.println("api/study/all?id = " + id);
        return postService.findStudyWithAppAndPart(id);
    }
    /**
     * 39 app, part 전부
     */
    @GetMapping(value = "/api/contest/all")
    public PostDtoAllDetail contestDtoAllDetail(@RequestParam @Valid Long id){
        System.out.println("api/contest/all?id = " + id);
        return postService.findContestWithAppAndPart(id);
    }

    /**
     * 40 app, part 전부
     */
    @GetMapping(value = "/api/miniProject/all")
    public PostDtoAllDetail miniProjectDtoAllDetail(@RequestParam @Valid Long id){
        System.out.println("api/miniProject/all?id = " + id);
        return postService.findMiniProjectWithAppAndPart(id);
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





