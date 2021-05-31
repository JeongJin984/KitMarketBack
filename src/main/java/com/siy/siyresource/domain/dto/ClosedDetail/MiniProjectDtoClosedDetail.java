package com.siy.siyresource.domain.dto.ClosedDetail;

import com.siy.siyresource.domain.entity.post.MiniProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MiniProjectDtoClosedDetail extends PostDtoClosedDetail{
    private String duration;
    private String subject;

    public MiniProjectDtoClosedDetail(MiniProject mini, Set<ParticipantsDetail> participants) {
        super(mini, participants);
        this.duration = mini.getDuration();
        this.subject =  mini.getSubject();
    }

}
