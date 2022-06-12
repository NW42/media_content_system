package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.failures.RepositoryFailure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория пользователя с ложными данными.
 */
@Component
public class FakeUserRepository implements UserRepository {
  private static final Faker faker = Faker.instance(new Locale("ru"));

  private static final List<User> users = new ArrayList<>();

  static {
    var count = new Random().nextInt(10);
    var roles = Role.values();
    while (count-- > 0) {
      users.add(
              User.add(
                      new Random().nextInt(1, 32),
                      faker.name().fullName(),
                      "gTeggstiag" + count,
                      faker.internet().emailAddress(),
                      faker.lorem().characters(40) + "==",
                      roles[new Random().nextInt(roles.length)]
              ).get()
      );
    }
  }

  @Override
  public Future<Either<Failure, User>> add(User user) {
    return null;
  }

  @Override
  public Future<Either<Failure, User>> get(int id) {
    if (new Random().nextBoolean()) {
      return EitherHelper.badLeft(RepositoryFailure.DEFAULT_MESSAGE);
    }
    var usersResult = users.stream().filter(us -> us.getId() == id).toList();
    if (usersResult.size() == 0) {
      return EitherHelper.badLeft(NotFound.DEFAULT_MESSAGE);
    }
    return EitherHelper.goodRight(usersResult.get(0));
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> getAll() {
    // тестовая реализация бизнес-логики
    return new Random().nextBoolean()
            ? EitherHelper.goodRight(users)
            : EitherHelper.badLeft(RepositoryFailure.DEFAULT_MESSAGE);
  }

  @Override
  public Future<Either<Failure, User>> update(User user) {
    return null;
  }

  @Override
  public Future<Either<Failure, Void>> delete(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> search(String query) {
    return null;
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> search(String query, Role role) {
    return null;
  }
}
