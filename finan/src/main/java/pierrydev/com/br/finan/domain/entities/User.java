package pierrydev.com.br.finan.domain.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "User")
public class User extends Model {

  @Column public String username;
  @Column public String password;
  @Column public boolean saved;

  public User(){
    super();
  }

  public User(String user, String pass) {
    username = user;
    password = pass;
    saved = false;
  }

  public void changePassword(String password, String newPassword, String confirmPassword) {
    if (password.equals(this.password) && newPassword.equals(confirmPassword)) {
      this.password = newPassword;
    }
  }

  public void saveProfile() {
    this.saved = true;
  }

  public void isValid(){

  }

}
