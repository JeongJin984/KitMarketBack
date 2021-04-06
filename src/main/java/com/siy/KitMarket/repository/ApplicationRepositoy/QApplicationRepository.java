package com.siy.KitMarket.repository.ApplicationRepositoy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.QApplication;
import com.siy.KitMarket.domain.entity.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.*;

@Repository
public class QApplicationRepository {
    @Autowired
    JPAQueryFactory queryFactory;
}
