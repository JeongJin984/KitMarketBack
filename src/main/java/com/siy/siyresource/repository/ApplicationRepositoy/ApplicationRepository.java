package com.siy.KitMarket.repository.ApplicationRepositoy;

import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.repository.CommonRepository;
import com.siy.KitMarket.repository.PostRepository.PostRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository  extends CommonRepository<Application, Long>, ApplicationRepositoryCustom{

}