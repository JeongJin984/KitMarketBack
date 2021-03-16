package com.siy.KitMarket.common;

import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.AccountRole;
import com.siy.KitMarket.domain.entity.account.Role;
import com.siy.KitMarket.domain.entity.account.RoleHierarchy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

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

        em.persist(accountRoleAdmin);
        em.persist(accountRoleManager);
        em.persist(accountRoleUser);

        em.getTransaction().commit();
    }
}