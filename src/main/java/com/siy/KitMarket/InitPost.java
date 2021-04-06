package com.siy.KitMarket;

import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
import com.siy.KitMarket.domain.entity.post.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 더미데이터 만들기
 */
@Profile("local")
@Component
@RequiredArgsConstructor
public class InitPost {/*
    private final InitPostService initPostService;

    @PostConstruct
    public void init(){
        initPostService.init();
    }

    @Component
    static class InitPostService{
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            Account account[] = new Account[5];
            for(int j = 0 ; j<5; j++){
                account[j] = new Account("Account" + j, "pass" + j, "email" + j, j + 1);
                em.persist(account[j]);
            }

            for(int i = 0; i<30; i++){
                Study study = new Study("Study" + i, "I'm Study" + i, account[i%5],"i love study" + i);

                Application application1 = new Application("댓글 1입니다.", study);
                Application application2 = new Application("댓글 2입니다.", study);
                Application application3 = new Application("댓글 3입니다.", study);

                em.persist(study);
                em.persist(application1);
                em.persist(application2);
                em.persist(application3);

            }

            for(int i = 0; i<30; i++){
                CarFull carFull = new CarFull("CarFull" + i, "I'm CarFull" + i, account[i%5],"i love carFull" + i);
                Application application1 = new Application("댓글 1입니다.", carFull);
                Application application2 = new Application("댓글 2입니다.", carFull);
                Application application3 = new Application("댓글 3입니다.", carFull);

                em.persist(carFull);
                em.persist(application1);
                em.persist(application2);
                em.persist(application3);
            }

            for(int i = 0; i<30; i++){
                Contest contest = new Contest("Contest" + i, "I'm Contest" + i, account[i%5],"i love contest" + i);

                Application application1 = new Application("댓글 1입니다.", contest);
                Application application2 = new Application("댓글 2입니다.", contest);
                Application application3 = new Application("댓글 3입니다.", contest);

                em.persist(contest);
                em.persist(application1);
                em.persist(application2);
                em.persist(application3);
            }
        }
    }*/
}
