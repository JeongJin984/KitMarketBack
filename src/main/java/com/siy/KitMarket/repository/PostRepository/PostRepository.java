package com.siy.KitMarket.repository.PostRepository;

import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CommonRepository<Post, Long>, PostRepositoryCustom {

}
