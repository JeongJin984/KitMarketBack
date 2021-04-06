package com.siy.KitMarket.repository.AccountPostRepository;

import com.siy.KitMarket.domain.condition.AccountPostSearchCondition;
import com.siy.KitMarket.domain.dto.accountPost.AccountPostDto;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface AccountPostRepositoryCustom {
    public Page<AccountPost> findAccountPost(AccountPostSearchCondition condition, Pageable pageable);
    Set<String> findParticipant(AccountPostSearchCondition condition);
}
