package com.siy.KitMarket.api;

import com.siy.KitMarket.domain.dto.post.*;
import com.siy.KitMarket.domain.dto.post.Linear.PostLinearDto;
import com.siy.KitMarket.domain.dto.post.detail.CarFoolDtoDetail;
import com.siy.KitMarket.domain.dto.post.detail.ContestDtoDetail;
import com.siy.KitMarket.domain.dto.post.detail.PostDtoDetail;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.service.post.PostService;
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


    /**
     * Post 전체 조회
     */
    @GetMapping(value = "/api/postList")
    public Result postList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                           @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostDto> postDtoList = postService.findPostList(offset, size);

        return new Result(postDtoList.getContent().size(), postDtoList.getNumber(), postDtoList.getTotalPages(), postDtoList.getContent());
    }

    /**
     * Study 전체 조회
     */
    @GetMapping(value = "/api/studyList")
    public Result studyList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                            @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<StudyDto> postDtoList = postService.findStudyList(offset, size);

        return new Result(postDtoList.getContent().size(), postDtoList.getNumber(), postDtoList.getTotalPages(), postDtoList.getContent());
    }

    /**
     * carFull 전체 조회
     */
    @GetMapping(value = "/api/carFoolList")
    public Result carFullList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                              @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<CarFullDto> carFullDtoList = postService.findCarFulList(offset, size);

        return new Result(carFullDtoList.getContent().size(), carFullDtoList.getNumber(), carFullDtoList.getTotalPages(), carFullDtoList.getContent());
    }

    /**
     * Contest 전체 조회
     */
    @GetMapping(value = "/api/contestList")
    public Result ContestList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                              @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<ContestDto> contestDtoList = postService.findContestList(offset, size);

        return new Result(contestDtoList.getContent().size(), contestDtoList.getNumber(), contestDtoList.getTotalPages(), contestDtoList.get());
    }


    /**
     * post 한개 조회
     */
    @GetMapping(value = "/api/post")
    public PostDtoDetail PostOne(@RequestParam(value = "id") Long id) {
        PostDtoDetail findPostDetail = postService.findPostById(id);

        return findPostDetail;
    }

    /**
     * study 한개 조회
     */
    @GetMapping(value = "/api/study")
    public PostDtoDetail StudyOne(@RequestParam(value = "id") Long id) {
        PostDtoDetail findPostDetail = postService.findStudyById(id);

        return findPostDetail;
    }

    /**
     * contest 한개 조회
     */
    @GetMapping(value = "/api/contest")
    public PostDtoDetail contestOne(@RequestParam(value = "id") Long id) {
        ContestDtoDetail findPostDetail = postService.findContestById(id);

        return findPostDetail;
    }

    /**
     * carFool 한개 조회
     */
    @GetMapping(value = "/api/carFool")
    public PostDtoDetail carFoolOne(@RequestParam(value = "id") Long id) {
        CarFoolDtoDetail findPostDetail = postService.findCarFoolById(id);

        return findPostDetail;
    }


    /**
     * Post 저장
     */
    @PostMapping(value = "/api/post")
    public Long saveStudy(@RequestBody @Valid Post post) {
        Long saveId = postService.save(post);
        return saveId;
    }

    @GetMapping(value = "/api/participating")
    public Page<PostLinearDto> findParticipating(@RequestParam String username, @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                 @RequestParam(value = "size", defaultValue = "8", required = false) int size){
        return postService.findParticipatingList(username, offset, size);
    }

    /**
     * PostLinearList
     */
    @GetMapping(value = "/api/postLinear")
    public Result postLinearList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                 @RequestParam(value = "size", defaultValue = "8", required = false) int size) {
        Page<PostLinearDto> postLinearList = postService.findPostLinearList(offset, size);


        return new Result(postLinearList.getContent().size(), postLinearList.getNumber(), postLinearList.getTotalPages(), postLinearList.get());
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