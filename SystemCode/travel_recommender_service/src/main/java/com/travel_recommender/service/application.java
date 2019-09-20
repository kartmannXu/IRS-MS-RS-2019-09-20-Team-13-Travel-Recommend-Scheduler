package com.travel_recommender.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.travel_recommender.service")
@EnableJpaRepositories("com.travel_recommender.DAO")
@EntityScan(basePackages="com.travel_recommender.model.Spot")
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class, args);
    }
}