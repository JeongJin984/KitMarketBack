package com.siy.KitMarket.api;

import com.siy.KitMarket.domain.dto.post.CarFullDto;
import com.siy.KitMarket.domain.dto.post.ContestDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.dto.post.StudyDto;
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
    @GetMapping(value = "/api/carFullList")
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
     * Post 저장
     */
    @PostMapping(value = "/api/Post")
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