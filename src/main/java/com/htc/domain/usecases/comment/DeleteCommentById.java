package com.htc.domain.usecases.comment;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления комментария по идентификатору.
 */
@AllArgsConstructor
public final class DeleteCommentById implements UseCase<DeleteCommentById.Params, Void> {
  /**
   * Параметры сценария удаления комментария.
   *
   * @param id идентификатор комментария
   */
  public record Params(Id id) {}

  private final CommentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    return repository.delete(params.id());
  }
}
