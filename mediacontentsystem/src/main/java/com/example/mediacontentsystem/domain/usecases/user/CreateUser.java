package com.example.mediacontentsystem.domain.usecases.user;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCaseUsingParams;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Создает пользователя.
 */
@AllArgsConstructor
public class CreateUser implements UseCaseUsingParams<User, User> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, User>> execute(User param) {
    return repository.create(param);
  }
}
