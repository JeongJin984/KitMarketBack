//package com.siy.siyresource.common;
//
//import com.siy.siyresource.domain.entity.post.Contest.Contest;
//import com.siy.siyresource.domain.entity.post.Contest.ContestCategory;
//import com.siy.siyresource.domain.entity.post.Post;
//import com.siy.siyresource.domain.entity.post.PostStatus;
//import com.siy.siyresource.domain.entity.post.Study.Study;
//import com.siy.siyresource.domain.entity.Application;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationStartedEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@NoArgsConstructor
//@Component
//public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {
//
//    private EntityManagerFactory entityManagerFactory;
//    private static Long count = 0L;
//
//    @Autowired
//    public SimpleListener(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    @Override
//    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
//        if (count != 0L) {
//            return;
//        }
//        count = 1L;
//
//        System.out.println("count = " + count);
//
//        EntityManager em = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//
//
//        for (int i = 0; i < 10; i++) {
//            Post post = new Post("Study" + i, "I'm Study" + i, "admin", 2, 5,
//                    LocalDateTime.of(2022, 3, 25, 18, 19, 03), PostStatus.POSTING, );
//            Study study =
//
//
//            Application application1 = new Application("user", study.getTitle()+" 참여하고싶어요", study);
//            Application application2 = new Application("admin", study.getTitle()+" 참여하고싶어요", study);
//
//            study.plusParticipants("user");
//            study.plusParticipants("admin");
//
//            em.persist(study);
//            em.persist(application1);
//            em.persist(application2);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            Post post = new Post("Contest" + i, "I'm Study" + i, "admin", 2, 5,
//                    LocalDateTime.of(2022, 3, 25, 18, 19, 03), PostStatus.POSTING);
//            Contest contest = new Contest(post, ContestCategory.CHARACTER);
//
//
//            Application application1 = new Application("user", contest.getTitle()+" 참여하고싶어요", contest);
//            Application application2 = new Application("admin", contest.getTitle()+" 참여하고싶어요", contest);
//
//            contest.plusParticipants("user");
//            contest.plusParticipants("admin");
//
//
//            em.persist(contest);
//            em.persist(application1);
//            em.persist(application2);
//
//        }
//        em.getTransaction().commit();
//    }
//}