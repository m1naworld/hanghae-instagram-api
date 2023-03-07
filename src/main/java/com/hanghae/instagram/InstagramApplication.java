package com.hanghae.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class InstagramApplication {
	public static void main(String[] args) { SpringApplication.run(InstagramApplication.class, args);}
}
