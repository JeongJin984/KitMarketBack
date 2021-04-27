package com.siy.siyresource.service.user;

import com.siy.siyresource.domain.condition.AccountPostSearchCondition;
import com.siy.siyresource.domain.condition.AccountSearchCondition;
import com.siy.siyresource.domain.dto.account.FullAccountDto;
import com.siy.siyresource.domain.dto.accountPost.AccountPostDto;
import com.siy.siyresource.domain.dto.post.PostDto;
import com.siy.siyresource.domain.entity.account.Account;
import com.siy.siyresource.domain.entity.account.AccountCode;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.repository.AccountPostRepository.AccountPostRepository;
import com.siy.siyresource.repository.AccountRepository.AccountRepository;
import com.siy.siyresource.repository.PostRepository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountSearchService {

    private AccountRepository accountRepository;
    private PostRepository postRepository;
    private AccountPostRepository accountPostRepository;

    @Autowired
    public AccountSearchService(AccountRepository accountRepository, PostRepository postRepository, AccountPostRepository accountPostRepository) {
        this.accountRepository = accountRepository;
        this.postRepository = postRepository;
        this.accountPostRepository = accountPostRepository;
    }

    public FullAccountDto findFullAccountDto(Account account, AccountSearchCondition condition, int offset, int size) {
        if(account == null) account = accountRepository.findAccount(condition);
        FullAccountDto fullAccountDto = new FullAccountDto(account.getUsername(), account.getEmail(), account.getAge());

        List<PostDto> createdPosts;
        List<PostDto> participatedPosts;

        AccountPostSearchCondition apCondition = new AccountPostSearchCondition();

        /*
        * 자신의 코드가 WRITER 인 모든 POST
        * */
        apCondition.setUsername(condition.getUserName());
        apCondition.setCode(AccountCode.WRITER);
        Page<AccountPost> accountPosts = accountPostRepository.findAccountPost(apCondition, PageRequest.of(offset, size));

        createdPosts = collectPostDtoList(accountPosts, true);

        /*
         * 자신의 코드가 PARTICIPANT 인 모든 POST
         * */
        apCondition.setCode(AccountCode.PARTICIPANT);
        accountPosts = accountPostRepository.findAccountPost(apCondition, PageRequest.of(offset, size));

        participatedPosts = collectPostDtoList(accountPosts, false);

        fullAccountDto.setCreatedPost(createdPosts);
        fullAccountDto.setParticipatingPost(participatedPosts);

        return fullAccountDto;
    }

    private List<PostDto> collectPostDtoList(Page<AccountPost> accountPostPage, boolean isWriter) {
        HashMap<Long, String> writerMap = new HashMap<>();
        HashMap<Long, Set<String>> participantMap = new HashMap<>();

        accountPostPage.getContent().forEach(accountPost -> {
            Long id = accountPost.getPost().getId();

            if(accountPost.getCode().equals(AccountCode.WRITER)){
                writerMap.put(id ,accountPost.getUsername());

                AccountPostSearchCondition condition = new AccountPostSearchCondition();
                condition.setPostId(accountPost.getPost().getId());
                condition.setCode(AccountCode.PARTICIPANT);

                participantMap.computeIfAbsent(id, k -> new HashSet<>());
                participantMap.get(id).addAll(accountPostRepository.findParticipant(condition));
            }
            else {
                writerMap.put(id, accountPost.getPost().getWriter());
                participantMap.computeIfAbsent(id, k -> new HashSet<>());
                participantMap.get(id).add(accountPost.getUsername());
            }
        });

        return accountPostPage.getContent().stream().map(accountPost -> {
            Long id = accountPost.getPost().getId();
            PostDto postDto = new PostDto(accountPost.getPost(), participantMap.get(id));
            return postDto;
        }).collect(Collectors.toList());
    }
}
