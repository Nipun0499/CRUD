package com.institution.crud;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConnectionPoolTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void givenHikariConnectionPoolInstance_whenCheckedDataSourceProperties_thenCorrect() {
        HikariDataSource ds = (HikariDataSource)dataSource;
        assertThat(dataSource.getClass().getName())
                .isEqualTo("com.zaxxer.hikari.HikariDataSource");
        Assertions.assertEquals(5,ds.getMaximumPoolSize());
    }
}