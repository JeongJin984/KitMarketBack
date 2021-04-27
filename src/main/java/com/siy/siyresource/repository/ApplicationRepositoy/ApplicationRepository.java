package com.siy.siyresource.repository.ApplicationRepositoy;

import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.repository.CommonRepository;
import com.siy.siyresource.repository.PostRepository.PostRepositoryCustom;

public interface ApplicationRepository  extends CommonRepository<Application, Long>, ApplicationRepositoryCustom {

}
