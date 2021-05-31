package com.siy.siyresource.repository.ParticipantRepository;

import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.Participants;
import com.siy.siyresource.repository.ApplicationRepositoy.ApplicationRepositoryCustom;
import com.siy.siyresource.repository.CommonRepository;

public interface ParticipantRepository extends CommonRepository<Participants, Long>, ParticipantRepositoryCustom {

}
