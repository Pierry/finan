package pierrydev.com.br.finan.domain.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Local")
public class Local extends Model {

  @Column public String description;

  public Local() {
    super();
  }

  public Local(String description) {
    this.description = description;
  }

  public void updateDescription(String description){
    this.description = description;
  }

  public void isValid() {

  }
}
