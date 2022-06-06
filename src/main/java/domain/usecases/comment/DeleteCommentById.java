package domain.usecases.comment;

import domain.entities.failures.Failure;
import domain.repositories.CommentRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления комментария по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteCommentById implements UseCase<Integer, Void> {
  private final CommentRepository repository;

  @Override
  public Future<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
