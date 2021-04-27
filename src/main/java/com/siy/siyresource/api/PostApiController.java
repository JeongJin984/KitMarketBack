package com.siy.siyresource.api;

import com.siy.siyresource.domain.dto.post.CarFullDto;
import com.siy.siyresource.domain.dto.post.ContestDto;
import com.siy.siyresource.domain.dto.post.PostDto;
import com.siy.siyresource.domain.dto.post.StudyDto;
import com.siy.siyresource.domain.dto.post.detail.CarFoolDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.ContestDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.PostDtoDetail;
import com.siy.siyresource.domain.entity.post.Post;
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

    /**
     * Post 전체 조회
     */
    @GetMapping(value = "/api/postList")
    public Result postList(@RequestParam(value = "offset",defaultValue = "0",required = false)int offset,
                           @RequestParam(value = "size",defaultValue = "8",required = false)int size) {
        Page<PostDto> postDtoList = postService.findPostList(offset, size);

        return new Result(postDtoList.getContent().size(), postDtoList.getNumber(), postDtoList.getTotalPages(), postDtoList.getContent());
    }

    /**
     * Study 전체 조회
     */
    @GetMapping(value = "/api/studyList")
    public Result studyList(@RequestParam(value = "offset",defaultValue = "0",required = false)int offset,
                            @RequestParam(value = "size",defaultValue = "8",required = false)int size) {
        Page<StudyDto> postDtoList = postService.findStudyList(offset, size);

        return new Result(postDtoList.getContent().size(),  postDtoList.getNumber(), postDtoList.getTotalPages(),postDtoList.getContent());
    }

    /**
     * carFull 전체 조회
     */
    @GetMapping(value = "/api/carFoolList")
    public Result carFullList(@RequestParam(value = "offset",defaultValue = "0",required = false)int offset,
                              @RequestParam(value = "size",defaultValue = "8",required = false)int size) {
        Page<CarFullDto> carFullDtoList = postService.findCarFulList(offset, size);

        return new Result(carFullDtoList.getContent().size(), carFullDtoList.getNumber(), carFullDtoList.getTotalPages(), carFullDtoList.getContent());
    }

    /**
     * Contest 전체 조회
     */
    @GetMapping(value = "/api/contestList")
    public Result ContestList(@RequestParam(value = "offset",defaultValue = "0",required = false)int offset,
                              @RequestParam(value = "size",defaultValue = "8",required = false)int size) {
        Page<ContestDto> contestDtoList = postService.findContestList(offset, size);
        return new Result(contestDtoList.getContent().size(),  contestDtoList.getNumber(), contestDtoList.getTotalPages(),contestDtoList.get());
    }


    /**
     * post 한개 조회
     */
    @GetMapping(value = "/api/post")
    public PostDtoDetail PostOne(@RequestParam(value = "id")Long id){
        PostDtoDetail findPostDetail = postService.findPostById(id);

        return findPostDetail;
    }

    /**
     * study 한개 조회
     */
    @GetMapping(value = "/api/study")
    public PostDtoDetail StudyOne(@RequestParam(value = "id")Long id){
        PostDtoDetail findPostDetail = postService.findStudyById(id);

        return findPostDetail;
    }
    /**
     * contest 한개 조회
     */
    @GetMapping(value = "/api/contest")
    public PostDtoDetail contestOne(@RequestParam(value = "id")Long id){
        ContestDtoDetail findPostDetail = postService.findContestById(id);

        return findPostDetail;
    }

    /**
     * carFool 한개 조회
     */
    @GetMapping(value = "/api/carFool")
    public PostDtoDetail carFoolOne(@RequestParam(value = "id")Long id){
        CarFoolDtoDetail findPostDetail = postService.findCarFoolById(id);

        return findPostDetail;
    }



    /**
     * Post 저장
     */
    @PostMapping(value = "/api/post")
    public Long saveStudy(@RequestBody @Valid Post post){
        Long saveId = postService.save(post);
        return saveId;
    }


}

@Data
@AllArgsConstructor
class Result<T>{
    private int size;
    private int currentPage;
    private int maxPage;
    private T data;
}