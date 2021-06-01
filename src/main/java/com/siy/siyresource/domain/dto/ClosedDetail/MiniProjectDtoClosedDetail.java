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
    private String projectDuration;
    private String topic;

    public MiniProjectDtoClosedDetail(MiniProject mini, Set<ParticipantsDetail> participants) {
        super(mini, participants);
        this.projectDuration = mini.getProjectDuration();
        this.topic =  mini.getTopic();
    }

}
