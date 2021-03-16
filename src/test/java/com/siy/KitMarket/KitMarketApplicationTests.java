package com.siy.KitMarket;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.QAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class KitMarketApplicationTests {

	@Autowired
	JPAQueryFactory queryFactory;

	@Test
	void contextLoads() {
		QAccount acc = new QAccount("acc");

		Account account = queryFactory
				.selectFrom(acc)
				.where(acc.username.eq("user"))
				.fetchOne();

		assertThat(account.getUsername()).isEqualTo("user");
	}

}
