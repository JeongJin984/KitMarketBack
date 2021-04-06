package com.siy.KitMarket.repository.AccountPostRepository;

import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import com.siy.KitMarket.repository.AccountRepository.AccountRepositoryCustom;
import com.siy.KitMarket.repository.CommonRepository;

public interface AccountPostRepository extends CommonRepository<AccountPost, Long>, AccountPostRepositoryCustom {
}
