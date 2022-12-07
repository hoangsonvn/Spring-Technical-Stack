package com.example.replication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Component;

@SpringBootApplication
//(exclude = { //
//        DataSourceAutoConfiguration.class, //
//        DataSourceTransactionManagerAutoConfiguration.class, //
//        HibernateJpaAutoConfiguration.class })

public class ReplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReplicationApplication.class, args);
    }

}
