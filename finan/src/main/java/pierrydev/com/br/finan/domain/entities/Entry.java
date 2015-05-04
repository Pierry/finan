package pierrydev.com.br.finan.domain.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import pierrydev.com.br.finan.utilities.DateTime;

public class Entry extends Model {

  @Column public double price;
  @Column public String description;
  @Column public int entryType;
  @Column public String dateTime;
  @Column public Local local;
  @Column public User user;

  public Entry() {
    super();
  }

  public Entry(double price, String description, int entryType, Local local, User user) {
    this.price = price;
    this.description = description;
    this.entryType = entryType;
    this.local = local;
    this.user = user;
    this.dateTime = DateTime.getDateAndTime();
  }

  public void updateEntry(double price, String description,
      int entryType, Local local){
    this.price = price;
    this.description = description;
    this.entryType = entryType;
    this.local = local;
  }

  public void isValid(){

  }
}
