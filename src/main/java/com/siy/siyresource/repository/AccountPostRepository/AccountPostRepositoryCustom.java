package com.siy.siyresource.repository.AccountPostRepository;

import com.siy.siyresource.domain.dto.accountPost.AccountPostDto;
import com.siy.siyresource.domain.condition.AccountPostSearchCondition;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface AccountPostRepositoryCustom {
    public Page<AccountPost> findAccountPost(AccountPostSearchCondition condition, Pageable pageable);
    Set<String> findParticipant(AccountPostSearchCondition condition);
}
