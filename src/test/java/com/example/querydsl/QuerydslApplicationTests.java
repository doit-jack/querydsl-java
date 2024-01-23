package com.example.querydsl;

import com.example.querydsl.domain.Hello;
import com.example.querydsl.domain.QHello;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        Hello helloJack = new Hello();
        em.persist(helloJack);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QHello qHello = QHello.hello;
        Hello result = query.selectFrom(qHello).fetchOne();

        Assertions.assertThat(result).isEqualTo(helloJack);
        Assertions.assertThat(result.getId()).isEqualTo(helloJack.getId());
    }
}
