package pierrydev.com.br.finan.repositories;

import com.activeandroid.query.Select;
import java.util.List;
import org.androidannotations.annotations.EBean;
import pierrydev.com.br.finan.domain.contracts.repositories.IUserRepository;
import pierrydev.com.br.finan.domain.entities.User;

@EBean
public class UserRepository implements IUserRepository {

  @Override public List<User> get() {
    return new Select().from(User.class).execute();
  }

  @Override public User getById(int id) {
    return new Select().from(User.class).where("User = ?", id).executeSingle();
  }

  @Override public User getByUsernamePassword(String username, String password) {
    return
        new Select()
            .from(User.class)
            .where("username = ?", username)
            .where("password = ?", password)
            .executeSingle();
  }

  @Override public void create(User user) {
    user.save();
  }

  @Override public void update(User user) {
    user.save();
  }

  @Override public void remove(User user) {
    User.delete(User.class, user.getId());
  }
}

