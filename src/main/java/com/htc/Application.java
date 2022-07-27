package com.htc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Основной класс проекта.
 */
@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
