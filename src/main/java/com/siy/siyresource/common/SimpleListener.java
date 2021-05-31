package com.siy.siyresource.common;

import com.siy.siyresource.domain.entity.Participants;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Contest.ContestCategory;
import com.siy.siyresource.domain.entity.post.Contest.Qualification;
import com.siy.siyresource.domain.entity.post.Gender;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.PostStatus;
import com.siy.siyresource.domain.entity.post.Study.Study;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.post.Study.StudyCategory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    private EntityManagerFactory entityManagerFactory;
    private static Long count = 0L;

    @Autowired
    public SimpleListener(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        if (count != 0L) {
            return;
        }
        count = 1L;

        System.out.println("count = " + count);

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();


        for (int i = 0; i < 10; i++) {
            Study study = new Study("user", "Study_Title" + i, "본문 입니다.", 5, 3, LocalDateTime.of(2022, 3, 25, 18, 19, 03),
                    "Study", PostStatus.POSTING, StudyCategory.NCS, "daegu", "3");


            Application application1 = new Application("user", study.getTitle()+" 참여하고싶어요", study);
            Application application2 = new Application("admin", study.getTitle()+" 참여하고싶어요", study);

            Participants newParticipant1 = new Participants("user", study);
            Participants newParticipant2 = new Participants("admin", study);


            em.persist(study);
            em.persist(application1);
            em.persist(application2);

            em.persist(newParticipant1);
            em.persist(newParticipant2);
        }

        for (int i = 0; i < 10; i++) {
            Contest contest = new Contest("user", "Contest_title" + i, "본문 입니다.", 5, 3, LocalDateTime.of(2022, 3, 25, 18, 19, 03),
                    "Study", PostStatus.POSTING, ContestCategory.DESIGN, "대구시 주최", Qualification.HIGHSCHOOL, "www.Daegu.co.kr" );


            Application application1 = new Application("user", contest.getTitle()+" 참여하고싶어요", contest);
            Application application2 = new Application("admin", contest.getTitle()+" 참여하고싶어요", contest);

            Participants newParticipant1 = new Participants("user", contest);
            Participants newParticipant2 = new Participants("admin", contest);


            em.persist(contest);
            em.persist(application1);
            em.persist(application2);

            em.persist(newParticipant1);
            em.persist(newParticipant2);

        }

        for (int i = 0; i < 10; i++) {
            CarPool carPool = new CarPool("user", "CarPool_Title" + i, "본문 입니다.", 5, 3, LocalDateTime.of(2020, 3, 25, 18, 19, 03),
                    "Study", PostStatus.CLOSE, Gender.NONE, "금오공대", "구미역",
                    LocalDateTime.of(2021, 5, 25, 18, 19, 03), 0L);


            Application application1 = new Application("user", carPool.getTitle()+" 참여하고싶어요", carPool);
            Application application2 = new Application("admin", carPool.getTitle()+" 참여하고싶어요", carPool);

            Participants newParticipant1 = new Participants("user", carPool);
            Participants newParticipant2 = new Participants("admin", carPool);


            em.persist(carPool);
            em.persist(application1);
            em.persist(application2);

            em.persist(newParticipant1);
            em.persist(newParticipant2);

        }
        em.getTransaction().commit();
    }
}