package domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllUsersTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetAllUsers useCase = new GetAllUsers(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetAllUsersFromTheRepository() {
    useCase.execute(null);
    Mockito.verify(mockUserRepository).getAll();
  }

  @Test
  void usersExist_ShouldReturnAllUsers() throws ExecutionException, InterruptedException {
    var users = List.of(
            User.createTestUser()
    );
    Mockito
            .when(mockUserRepository.getAll())
            .thenReturn(CompletableFuture.completedFuture(Either.right(users)));
    var result = useCase.execute(null)
            .get()
            .get();
    assertThat(result).isEqualTo(users);
  }

  @Test
  void usersNotExist_ShouldReturnEmptyList() throws ExecutionException, InterruptedException {
    var users = List.<User>of();
    Mockito
            .when(mockUserRepository.getAll())
            .thenReturn(CompletableFuture.completedFuture(Either.right(users)));
    var result = useCase.execute(null)
            .get()
            .get();
    assertThat(result).isEqualTo(users);
  }
}
