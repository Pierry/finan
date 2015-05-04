package pierrydev.com.br.finan.domain.contracts.repositories;

import java.util.List;
import pierrydev.com.br.finan.domain.entities.User;

public interface IUserRepository {

  List<User> get();

  User getById(int id);

  User getByUsernamePassword(String username, String password);

  void create(User user);

  void update(User user);

  void remove(User user);
}
