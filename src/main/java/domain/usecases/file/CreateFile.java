package domain.usecases.file;

import domain.entities.failures.Failure;
import domain.entities.files.File;
import domain.repositories.FileRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий создания файла.
 */
@AllArgsConstructor
public final class CreateFile implements UseCase<File, File> {

  private final FileRepository repository;

  @Override
  public Future<Either<Failure, File>> execute(File file) {
    return repository.create(file);
  }
}
