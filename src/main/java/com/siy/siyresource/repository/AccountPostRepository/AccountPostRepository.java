package com.siy.siyresource.repository.AccountPostRepository;

import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import com.siy.siyresource.repository.CommonRepository;

public interface AccountPostRepository extends CommonRepository<AccountPost, Long>, AccountPostRepositoryCustom {
}
