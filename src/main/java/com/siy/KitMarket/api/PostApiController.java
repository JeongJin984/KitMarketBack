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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Result postList(@RequestParam(value = "offset",defaultValue = "0",required = false)int offset, @RequestParam(value = "size",defaultValue = "10",required = false)int size) {
        Page<PostDto> postDtoList = postService.findPostList(offset, size);

        return new Result(postDtoList.getContent().size(), postDtoList.getNumber(), postDtoList.getTotalPages(), postDtoList.getContent());
    }

    /**
     * Study 전체 조회
     */
    @GetMapping(value = "/studyList")
    public Result studyList(@RequestParam(value = "offset",defaultValue = "0",required = false)int offset, @RequestParam(value = "size",defaultValue = "10",required = false)int size) {
        Page<StudyDto> postDtoList = postService.findStudyList(offset, size);

        return new Result(postDtoList.getContent().size(),  postDtoList.getNumber(), postDtoList.getTotalPages(),postDtoList.getContent());
    }

    /**
     * carFull 전체 조회
     */
    @GetMapping(value = "/carFullList")
    public Result carFullList(@RequestParam(value = "offset",defaultValue = "0",required = false)int offset, @RequestParam(value = "size",defaultValue = "10",required = false)int size) {
        Page<CarFullDto> carFullDtoList = postService.findCarFulList(offset, size);

        return new Result(carFullDtoList.getContent().size(), carFullDtoList.getNumber(), carFullDtoList.getTotalPages(), carFullDtoList.getContent());
    }

    /**
     * Contest 전체 조회
     */
    @GetMapping(value = "/ContestList")
    public Result ContestList(@RequestParam(value = "offset",defaultValue = "0",required = false)int offset, @RequestParam(value = "size",defaultValue = "10",required = false)int size) {
        Page<Contest> contestDtoList = postService.findContestList(offset, size);
        return new Result(contestDtoList.getContent().size(),  contestDtoList.getNumber(), contestDtoList.getTotalPages(),contestDtoList.get());
    }

    /**
     * Id 검색
     */
    @GetMapping(value = "/study/{id}")
    public StudyDto findStudyById(@PathVariable(value = "id") Long id){
        StudyDto findStudyDto = postService.findStudyOne(id);
        System.out.println("findStudyDto = " + findStudyDto);
        return findStudyDto;
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