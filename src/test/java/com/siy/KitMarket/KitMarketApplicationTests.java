package com.siy.KitMarket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@SpringBootTest
class KitMarketApplicationTests {


	@PersistenceContext
	EntityManager entityManager;

	@Test
	void contextLoads() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.commit();

	}

}
