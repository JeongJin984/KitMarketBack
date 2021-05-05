package com.siy.siyresource.repository.PostRepository;

import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CommonRepository<Post,Long>, PostRepositoryCustom {

}
