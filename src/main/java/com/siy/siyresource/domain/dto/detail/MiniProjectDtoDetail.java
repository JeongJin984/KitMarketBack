package com.siy.siyresource.domain.dto.detail;

import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.MiniProject;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class MiniProjectDtoDetail extends PostDtoDetail{
    private String duration;
    private String subject;

    public MiniProjectDtoDetail(MiniProject mini, Set<ParticipantsDto> participants, Set<ApplicationDto> applications) {
        super(mini, participants, applications);
        this.duration = mini.getDuration();
        this.subject =  mini.getSubject();
    }
}
