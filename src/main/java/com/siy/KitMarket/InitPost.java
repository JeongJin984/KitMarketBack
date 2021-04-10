package com.siy.KitMarket;

import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
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
import java.time.LocalDate;

/**
 * 더미데이터 만들기
 */
@Profile("local")
@Component
@RequiredArgsConstructor
public class InitPost {
    private final InitPostService initPostService;

    @PostConstruct
    public void init(){
        initPostService.init1();
        initPostService.init2();

    }

    @Component
    static class InitPostService{
        @PersistenceContext
        private EntityManager em;
        Account writer[] = new Account[5];
        Account participants[] = new Account[3];

        @Transactional
        public void init1(){
            for(int j = 0 ; j<5; j++){
                writer[j] = new Account("Account" + j, "pass" + j, "email" + j, j + 1);
                em.persist(writer[j]);
            }

            for(int j = 0 ; j<3; j++){
                participants[j] = new Account("participant" + j, "pass" + j, "partEmail" + j, j + 1);
                em.persist(participants[j]);

            }


        }

        @Transactional
        public void init2(){
            for(int i = 0; i<30; i++){
                Study study = new Study("Study" + i, "I'm Study" + i, writer[i%5],"i love study" + i , 0, i%5+3, LocalDate.of(2021,05,i%10+1));

                Application application1 = new Application("댓글 1입니다.", study);
                Application application2 = new Application("댓글 2입니다.", study);
                Application application3 = new Application("댓글 3입니다.", study);


                AccountPost accountPost1 = new AccountPost(study, participants[0]);
                AccountPost accountPost2 = new AccountPost(study, participants[1]);
                AccountPost accountPost3 = new AccountPost(study, participants[2]);


                em.persist(study);



                em.persist(application1);
                em.persist(application2);
                em.persist(application3);

                em.persist(accountPost1);
                em.persist(accountPost2);
                em.persist(accountPost3);

            }

            for(int i = 0; i<30; i++){
                CarFull carFull = new CarFull("CarFull" + i, "I'm CarFull" + i, writer[i%5],"i love carFull" + i,
                        0, i%5+3, LocalDate.of(2021,05,i%10+1));
                Application application1 = new Application("댓글 1입니다.", carFull);
                Application application2 = new Application("댓글 2입니다.", carFull);
                Application application3 = new Application("댓글 3입니다.", carFull);


                AccountPost accountPost1 = new AccountPost(carFull, participants[0]);
                AccountPost accountPost2 = new AccountPost(carFull, participants[1]);
                AccountPost accountPost3 = new AccountPost(carFull, participants[2]);


                em.persist(carFull);



                em.persist(application1);
                em.persist(application2);
                em.persist(application3);

                em.persist(accountPost1);
                em.persist(accountPost2);
                em.persist(accountPost3);

            }

            for(int i = 0; i<30; i++){
                Contest contest = new Contest("Contest" + i, "I'm Contest" + i, writer[i%5],"i love contest" + i, 0, i%5+3, LocalDate.of(2021,05,i%10+1));

                Application application1 = new Application("댓글 1입니다.", contest);
                Application application2 = new Application("댓글 2입니다.", contest);
                Application application3 = new Application("댓글 3입니다.", contest);


                AccountPost accountPost1 = new AccountPost(contest, participants[0]);
                AccountPost accountPost2 = new AccountPost(contest, participants[1]);
                AccountPost accountPost3 = new AccountPost(contest, participants[2]);


                em.persist(contest);



                em.persist(application1);
                em.persist(application2);
                em.persist(application3);

                em.persist(accountPost1);
                em.persist(accountPost2);
                em.persist(accountPost3);

            }
        }
    }
}
