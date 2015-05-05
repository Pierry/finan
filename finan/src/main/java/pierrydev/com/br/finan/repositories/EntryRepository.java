package pierrydev.com.br.finan.repositories;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import java.util.List;
import org.androidannotations.annotations.EBean;
import pierrydev.com.br.finan.domain.contracts.repositories.IEntryRepository;
import pierrydev.com.br.finan.domain.entities.Entry;

@EBean
public class EntryRepository implements IEntryRepository {

  @Override public List<Entry> get() {
    return new Select().from(Entry.class).execute();
  }

  @Override public Entry getById(int id) {
    return new Select().from(Entry.class).where("Entry = ?", id).executeSingle();
  }

  @Override public List<Entry> getByLocalId(long localId) {
    return new Select().from(Entry.class).where("local = ?", localId).execute();
  }

  @Override public void create(Entry entry) {
    entry.save();
  }

  @Override public void update(Entry entry) {
    entry.save();
  }

  @Override public void remove(Entry entry) {
    new Delete().from(Entry.class).where("Id = ?", entry.getId()).execute();
  }
}
