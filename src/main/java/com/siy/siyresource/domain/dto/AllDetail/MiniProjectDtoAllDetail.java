package com.siy.siyresource.domain.dto.AllDetail;

import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDetail;
import com.siy.siyresource.domain.dto.ClosedDetail.PostDtoClosedDetail;
import com.siy.siyresource.domain.entity.post.MiniProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MiniProjectDtoAllDetail extends PostDtoAllDetail {
    private String projectDuration;
    private String topic;

    public MiniProjectDtoAllDetail(MiniProject mini, Set<ApplicationDto> applications , Set<ParticipantsDetail> participants) {
        super(mini, applications, participants);
        this.projectDuration = mini.getProjectDuration();
        this.topic =  mini.getTopic();
    }

}