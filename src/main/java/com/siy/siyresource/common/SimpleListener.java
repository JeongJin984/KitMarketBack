package com.siy.siyresource.common;

import com.siy.siyresource.domain.condition.AccountSearchCondition;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Contest.ContestCategory;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.account.*;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import com.siy.siyresource.repository.AccountRepository.AccountRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
//@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    private EntityManagerFactory entityManagerFactory;
    private PasswordEncoder passwordEncoder;
    private static Long count = 0L;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public SimpleListener(EntityManagerFactory entityManagerFactory, PasswordEncoder passwordEncoder) {
        this.entityManagerFactory = entityManagerFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        if(count != 0L){
            return;
        }
        count = 1L;

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Account accountAdmin = accountRepository.findAccount(new AccountSearchCondition("admin"));
        Account accountManager = accountRepository.findAccount(new AccountSearchCondition("manager"));
        Account accountUser = accountRepository.findAccount(new AccountSearchCondition("user"));

        for(int i = 0; i<10; i++){
            Post post = new Post("Study" + i, "I'm Study" + i, accountUser.getUsername(), 1, 5,
                    LocalDateTime.of(2022,3,25,18,19,03));
            Study study = new Study(post, "study");

            AccountPost accountPost = new AccountPost();
            accountPost.setAccount(accountUser);
            accountPost.setPost(study);
            accountPost.setCode(AccountCode.WRITER);
            accountPost.setUsername(accountUser.getUsername());

            Application application1 = new Application(accountUser.getUsername()+" 참여하고싶어요", accountUser, study);
            Application application2 = new Application(accountManager.getUsername()+" 참여하고싶어요", accountManager, study);
            Application application3 = new Application(accountAdmin.getUsername() + " 참여하고싶어요", accountAdmin, study);

            em.persist(study);
            em.persist(application1);
            em.persist(application2);
            em.persist(application3);
            em.persist(accountPost);

            for(int j = 0; j<5; j++) {
                AccountPost accountPost2 = new AccountPost();
                accountPost2.setAccount(accountManager);
                accountPost2.setPost(study);
                accountPost2.setCode(AccountCode.PARTICIPANT);
                accountPost2.setUsername(accountManager.getUsername());

                em.persist(accountPost2);
            }
        }

        for(int i = 0; i<10; i++){
            Post post = new Post("Study" + i, "I'm Study" + i, accountUser.getUsername(), 1, 5, LocalDateTime.of(2022,1,2,14,05,02));
            Contest contest = new Contest(post, ContestCategory.CHARACTER);
            Application application1 = new Application(accountUser.getUsername()+" 참여하고싶어요", accountUser, contest);
            Application application2 = new Application(accountManager.getUsername()+" 참여하고싶어요", accountManager, contest);
            Application application3 = new Application(accountAdmin.getUsername() + " 참여하고싶어요", accountAdmin, contest);

            AccountPost accountPost = new AccountPost();

            accountPost.setAccount(accountManager);
            accountPost.setPost(contest);
            accountPost.setCode(AccountCode.WRITER);
            accountPost.setUsername(accountUser.getUsername());

            em.persist(contest);
            em.persist(application1);
            em.persist(application2);
            em.persist(application3);
            em.persist(accountPost);

            for(int j = 0; j<5; j++) {
                AccountPost accountPost2 = new AccountPost();
                accountPost2.setAccount(accountUser);
                accountPost2.setPost(contest);
                accountPost2.setCode(AccountCode.PARTICIPANT);
                accountPost2.setUsername(accountUser.getUsername());

                em.persist(accountPost2);
            }
        }
        em.getTransaction().commit();
    }
}