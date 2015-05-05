package pierrydev.com.br.finan.domain.contracts.repositories;

import java.util.List;

import pierrydev.com.br.finan.domain.entities.Entry;

public interface IEntryRepository {

  List<Entry> get();

  Entry getById(int id);

  List<Entry> getByLocalId(long localId);

  void create(Entry user);

  void update(Entry user);

  void remove(Entry user);
}
