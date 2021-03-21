package com.siy.KitMarket.api;

import com.siy.KitMarket.domain.dto.post.CarFullDto;
import com.siy.KitMarket.domain.dto.post.ContestDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.dto.post.StudyDto;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.domain.entity.post.Study;
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

    //postList
    @GetMapping(value = "/postList")
    public Result postList() {
        List<Post> postList = postService.findPostList();
        List<PostDto> collect = postList.stream()
                .map(p -> new PostDto(p.getTitle(), p.getContent()))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    @GetMapping(value = "/studyList")
    public Result studyList() {
        List<Study> studyList = postService.findStudyList();
        List<StudyDto> collect = studyList.stream()
                .map(s -> new StudyDto(s.getTitle(), s.getContent()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);

    }

    @GetMapping(value = "/carFullList")
    public Result carFullList() {
        List<CarFull> carFullList = postService.findCarFulList();
        List<CarFullDto> collect = carFullList.stream()
                .map(cf -> new CarFullDto(cf.getTitle(), cf.getContent()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }
    @GetMapping(value = "/ContestList")
    public Result ContestList() {
        List<Contest> contestList = postService.findContestList();
        List<ContestDto> collect = contestList.stream()
                .map(ct -> new ContestDto(ct.getTitle(), ct.getContent()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @GetMapping(value = "/study/{id}")
    public StudyDto findStudyById(@PathVariable("id") Long id){
        Study findStudy = postService.findStudyOne(id);
        StudyDto studyDto = new StudyDto(findStudy.getTitle(), findStudy.getContent());
        return studyDto;
    }




}

@Data
@AllArgsConstructor
class Result<T>{
    private int count;
    private T data;
}