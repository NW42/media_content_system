package com.htc.domain.usecases.task;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;

/**
 * Сценарий добавления новой задачи.
 */
@AllArgsConstructor
public final class AddTask implements UseCase<AddTask.Params, Task> {
  /**
   * Параметры сценария добавления задачи.
   *
   * @param name наименование
   * @param type тип
   * @param description описание
   * @param authorId идентификатор автора
   * @param executorId идентификатор исполнителя
   * @param dateExpired дата выполнения
   */
  public record Params(EntityName name, Type type, String description,
                       Id authorId, Id executorId, DateCreated dateExpired) {}

  private final TaskRepository repository;
  private final UserRepository userRepository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    var failure = new InvalidValues();
    var dateCreated = DateCreated.create();
    User author = null;
    try {
      author = userRepository.get(params.authorId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found (executor)");
    }
    User executor = null;
    try {
      executor = userRepository.get(params.executorId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "executor not found (executor)");
    }
    //TODO description - тип данных / проверка на null и empty
    return failure.getValues().size() == 0
            ? repository.add(params.name(), params.type(), params.description(), author, executor,
                          dateCreated.get(), params.dateExpired())
            : EitherHelper.badLeft(failure);
  }
}
