package com.siy.KitMarket.api;

import com.siy.KitMarket.domain.dto.post.CarFullDto;
import com.siy.KitMarket.domain.dto.post.ContestDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.dto.post.StudyDto;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.domain.entity.post.Study;
import com.siy.KitMarket.repository.QPostRepository;
import com.siy.KitMarket.service.post.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    /**
     * Post 전체 조회
     */
    @GetMapping(value = "/postList")
    public Result postList() {
        List<PostDto> postDtoList = postService.findPostList();
        return new Result(postDtoList.size(), postDtoList);
    }

    /**
     * Study 전체 조회
     */
    @GetMapping(value = "/studyList")
    public Result studyList() {
        List<StudyDto> studyDtoList = postService.findStudyList();

        return new Result(studyDtoList.size(), studyDtoList);
    }

    /**
     * carFull 전체 조회
     */
    @GetMapping(value = "/carFullList")
    public Result carFullList() {
        List<CarFullDto> carFullDtoList = postService.findCarFulList();

        return new Result(carFullDtoList.size(), carFullDtoList);
    }

    /**
     * Contest 전체 조회
     */
    @GetMapping(value = "/ContestList")
    public Result ContestList() {
        List<ContestDto> contestDtoList = postService.findContestList();
        return new Result(contestDtoList.size(), contestDtoList);
    }

    /**
     * Id 검색
     */
    @GetMapping(value = "/study/{id}")
    public StudyDto findStudyById(@PathVariable("id") Long id){
        StudyDto findStudyDto = postService.findStudyOne(id);
        System.out.println("findStudyDto = " + findStudyDto);
        return findStudyDto;
    }


}

@Data
@AllArgsConstructor
class Result<T>{
    private int count;
    private T data;
}