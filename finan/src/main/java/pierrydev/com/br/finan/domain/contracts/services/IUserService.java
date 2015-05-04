package pierrydev.com.br.finan.domain.contracts.services;

import java.util.List;
import pierrydev.com.br.finan.domain.entities.User;

public interface IUserService {

  List<User> get();

  User getById(int id);

  void create(String user, String pass);

  User auth(String username, String password);

  void changePassword(int userId, String password, String newPassword, String confirmPassword);

  void saveProfile(User user);

  void remove(User user);
}
