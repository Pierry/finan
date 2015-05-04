package pierrydev.com.br.finan.services;

import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import pierrydev.com.br.finan.domain.contracts.repositories.IUserRepository;
import pierrydev.com.br.finan.domain.contracts.services.IUserService;
import pierrydev.com.br.finan.domain.entities.User;
import pierrydev.com.br.finan.repositories.UserRepository;

@EBean
public class UserService implements IUserService {

  @Bean(UserRepository.class) IUserRepository userRepository;

  @Override public List<User> get() {
    return userRepository.get();
  }

  @Override public User getById(int id) {
    return userRepository.getById(id);
  }

  @Override public void create(String username, String pass) {
    User user = new User(username, pass);
    user.isValid();
    userRepository.create(user);
  }

  @Override public User auth(String username, String password) {
    return userRepository.getByUsernamePassword(username, password);
  }

  @Override public void changePassword(int userId, String password, String newPassword,
      String confirmPassword) {
    User user = userRepository.getById(userId);
    user.changePassword(password, newPassword, confirmPassword);
    user.isValid();
    userRepository.update(user);
  }

  @Override public void saveProfile(User user) {
    user.saveProfile();
    user.isValid();
    userRepository.update(user);
  }

  @Override public void remove(User user) {
    userRepository.remove(user);
  }
}
