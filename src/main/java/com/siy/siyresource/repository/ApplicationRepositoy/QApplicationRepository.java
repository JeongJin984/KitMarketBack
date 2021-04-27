package com.siy.siyresource.repository.ApplicationRepositoy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.*;

@Repository
public class QApplicationRepository {
    @Autowired
    JPAQueryFactory queryFactory;
}
