package domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.entities.failures.NotFound;
import domain.entities.user.User;
import domain.entities.user.UserRole;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserByIdTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetUserById useCase = new GetUserById(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
   void shouldGetUserFromTheRepository() {
    // Arrange
    var userId = new Random().nextInt();

    // Act
    useCase.execute(userId);

    // Assert
    Mockito.verify(mockUserRepository).get(userId);
  }

  @Test
  void userExists_ShouldReturnUser() throws ExecutionException, InterruptedException {
    // Arrange
    var userId = new Random().nextInt();
    var user = new User(
            userId,
            "user@example.com",
            "Passw0rd!",
            "Иванов Иван",
            new byte[] {},
            new UserRole(1, UserRole.RoleType.ADMIN)
    );

    Mockito.when(mockUserRepository.get(userId))
        .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

    // Act
    var result = useCase.execute(userId)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var userId = new Random().nextInt();
    var failure = new NotFound();

    Mockito
            .when(mockUserRepository.get(userId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    // Act
    var result = useCase.execute(userId)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}