package pierrydev.com.br.finan.domain.contracts.services;

import java.util.List;
import pierrydev.com.br.finan.domain.entities.Local;

public interface ILocalService {

  List<Local> get();

  Local getById(long id);

  void create(String description);

  void updateDescription(int localId, String description);

  void remove(Local local);
}
