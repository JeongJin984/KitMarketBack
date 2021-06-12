package com.siy.siyresource.domain.dto.PostingDetail;

import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.entity.post.MiniProject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class MiniProjectDtoPostingDetail extends PostDtoPostingDetail {
    private String projectDuration;
    private String topic;

    public MiniProjectDtoPostingDetail(MiniProject mini, Set<ApplicationDto> applications) {
        super(mini, applications);
        this.projectDuration = mini.getProjectDuration();
        this.topic =  mini.getTopic();
    }
}
