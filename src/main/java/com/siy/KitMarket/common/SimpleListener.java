package com.siy.KitMarket.common;

import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.account.*;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.domain.entity.post.Study;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
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
            Post post = new Post("Study" + i, "I'm Study" + i, accountUser.getUsername(), 1, 5);

            AccountPost accountPost = new AccountPost();
            accountPost.setAccount(accountUser);
            accountPost.setPost(post);
            accountPost.setCode(AccountCode.WRITER);
            accountPost.setUsername(accountUser.getUsername());

            Application application1 = new Application("댓글 1입니다.", post);
            Application application2 = new Application("댓글 2입니다.", post);
            Application application3 = new Application("댓글 3입니다.", post);

            em.persist(post);
            em.persist(application1);
            em.persist(application2);
            em.persist(application3);
            em.persist(accountPost);

            for(int j = 0; j<5; j++) {
                AccountPost accountPost2 = new AccountPost();
                accountPost2.setAccount(accountManager);
                accountPost2.setPost(post);
                accountPost2.setCode(AccountCode.PARTICIPANT);
                accountPost2.setUsername(accountManager.getUsername());

                em.persist(accountPost2);
            }
        }

        for(int i = 0; i<10; i++){
            Post post = new Post("Study" + i, "I'm Study" + i, accountManager.getUsername(),1, 5);

            Application application1 = new Application("댓글 1입니다.", post);
            Application application2 = new Application("댓글 2입니다.", post);
            Application application3 = new Application("댓글 3입니다.", post);

            AccountPost accountPost = new AccountPost();
            accountPost.setAccount(accountManager);
            accountPost.setPost(post);
            accountPost.setCode(AccountCode.WRITER);
            accountPost.setUsername(accountUser.getUsername());

            em.persist(post);
            em.persist(application1);
            em.persist(application2);
            em.persist(application3);
            em.persist(accountPost);

            for(int j = 0; j<5; j++) {
                AccountPost accountPost2 = new AccountPost();
                accountPost2.setAccount(accountUser);
                accountPost2.setPost(post);
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