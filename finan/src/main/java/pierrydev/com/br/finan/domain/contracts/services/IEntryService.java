package pierrydev.com.br.finan.domain.contracts.services;

import java.util.List;
import pierrydev.com.br.finan.domain.entities.Entry;
import pierrydev.com.br.finan.domain.entities.Local;
import pierrydev.com.br.finan.domain.entities.User;

public interface IEntryService {

  List<Entry> getByLocalId(long localId);

  List<Entry> get();

  Entry getById(int id);

  void create(double price, String description, int entryType, Local local, User user);

  void updateEntry(int entryId, double price, String description, int entryType, Local local);

  void remove(Entry entry);
}
