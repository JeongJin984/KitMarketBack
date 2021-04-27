package com.siy.siyresource.common;

import com.siy.siyresource.domain.entity.post.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.account.*;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
//@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    private EntityManagerFactory entityManagerFactory;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SimpleListener(EntityManagerFactory entityManagerFactory, PasswordEncoder passwordEncoder) {
        this.entityManagerFactory = entityManagerFactory;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleManager = new Role("ROLE_MANAGER");
        Role roleUser = new Role("ROLE_USER");

        em.persist(roleAdmin);
        em.persist(roleManager);
        em.persist(roleUser);

        RoleHierarchy roleHierarchyAdmin = new RoleHierarchy("ROLE_ADMIN");
        RoleHierarchy roleHierarchyManager = new RoleHierarchy("ROLE_MANAGER", roleHierarchyAdmin);
        RoleHierarchy roleHierarchyUser = new RoleHierarchy("ROLE_USER", roleHierarchyManager);

        em.persist(roleHierarchyAdmin);
        em.persist(roleHierarchyManager);
        em.persist(roleHierarchyUser);

        Account accountAdmin = new Account("admin",
                passwordEncoder.encode("asdf"),
                "1111@1111.1111",
                30);

        Account accountManager = new Account("manager",
                passwordEncoder.encode("asdf"),
                "2222@2222.2222",
                20);

        Account accountUser = new Account("user",
                passwordEncoder.encode("asdf"),
                "3333@3333.3333",
                10);

        em.persist(accountAdmin);
        em.persist(accountManager);
        em.persist(accountUser);

        AccountRole accountRoleAdmin = new AccountRole();
        accountRoleAdmin.setRole(roleAdmin);
        accountRoleAdmin.setAccount(accountAdmin);

        AccountRole accountRoleManager = new AccountRole();
        accountRoleManager.setRole(roleManager);
        accountRoleManager.setAccount(accountManager);

        AccountRole accountRoleUser = new AccountRole();
        accountRoleUser.setRole(roleUser);
        accountRoleUser.setAccount(accountUser);


        for(int i = 0; i<10; i++){
            Post post = new Post("Study" + i, "I'm Study" + i, accountUser.getUsername(), 1, 5, LocalDate.of(2022,3,25));
            Study study = new Study(post, "study");

            AccountPost accountPost = new AccountPost();
            accountPost.setAccount(accountUser);
            accountPost.setPost(study);
            accountPost.setCode(AccountCode.WRITER);
            accountPost.setUsername(accountUser.getUsername());

            Application application1 = new Application("댓글 1입니다.", study);
            Application application2 = new Application("댓글 2입니다.", study);
            Application application3 = new Application("댓글 3입니다.", study);

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
            Post post = new Post("Study" + i, "I'm Study" + i, accountUser.getUsername(), 1, 5, LocalDate.of(2022,1,2));
            Contest contest = new Contest(post, "contest");

            Application application1 = new Application("댓글 1입니다.", contest);
            Application application2 = new Application("댓글 2입니다.", contest);
            Application application3 = new Application("댓글 3입니다.", contest);

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

        em.persist(accountRoleAdmin);
        em.persist(accountRoleManager);
        em.persist(accountRoleUser);

        em.getTransaction().commit();
    }
}