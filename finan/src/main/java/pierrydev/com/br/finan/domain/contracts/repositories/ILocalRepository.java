package pierrydev.com.br.finan.domain.contracts.repositories;

import java.util.List;
import pierrydev.com.br.finan.domain.entities.Local;

public interface ILocalRepository {

  List<Local> get();

  Local getById(long id);

  void create(Local user);

  void update(Local user);

  void remove(Local user);

}
