package pierrydev.com.br.finan.services;

import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import pierrydev.com.br.finan.domain.contracts.repositories.IEntryRepository;
import pierrydev.com.br.finan.domain.contracts.services.IEntryService;
import pierrydev.com.br.finan.domain.entities.Entry;
import pierrydev.com.br.finan.domain.entities.Local;
import pierrydev.com.br.finan.domain.entities.User;
import pierrydev.com.br.finan.repositories.EntryRepository;

@EBean
public class EntryService implements IEntryService {

  @Bean(EntryRepository.class) IEntryRepository entryRepository;

  @Override public List<Entry> getByLocalId(int localId) {
    return entryRepository.getByLocalId(localId);
  }

  @Override public List<Entry> get() {
    return entryRepository.get();
  }

  @Override public Entry getById(int id) {
    return entryRepository.getById(id);
  }

  @Override public void create(double price, String description, int entryType, Local local,
      User user) {
    Entry entry = new Entry(price, description, entryType, local, user);
    entry.isValid();
    entryRepository.create(entry);
  }

  @Override public void updateEntry(int entryId, double price, String description, int entryType, Local local) {
    Entry entry = entryRepository.getById(entryId);
    entry.updateEntry(price, description, entryType, local);
    entry.isValid();
  }

  @Override public void remove(Entry entry) {

  }
}
