package pierrydev.com.br.finan.repositories;

import com.activeandroid.query.Select;
import java.util.List;
import org.androidannotations.annotations.EBean;
import pierrydev.com.br.finan.domain.contracts.repositories.ILocalRepository;
import pierrydev.com.br.finan.domain.entities.Local;

@EBean
public class LocalRepository implements ILocalRepository {

  @Override public List<Local> get() {
    return new Select()
        .from(Local.class)
        .execute();
  }

  @Override public Local getById(long id) {
    return new Select()
        .from(Local.class)
        .where("Id = ?", id)
        .executeSingle();
  }

  @Override public void create(Local local) {
    local.save();
  }

  @Override public void update(Local local) {
    local.save();
  }

  @Override public void remove(Local local) {
    Local.delete(Local.class, local.getId());
  }
}


