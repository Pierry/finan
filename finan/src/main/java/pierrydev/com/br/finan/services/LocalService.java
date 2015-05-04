package pierrydev.com.br.finan.services;

import java.util.List;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import pierrydev.com.br.finan.domain.contracts.repositories.ILocalRepository;
import pierrydev.com.br.finan.domain.contracts.services.ILocalService;
import pierrydev.com.br.finan.domain.entities.Local;
import pierrydev.com.br.finan.repositories.LocalRepository;

@EBean
public class LocalService implements ILocalService {

  @Bean(LocalRepository.class) ILocalRepository localRepository;

  @Override public List<Local> get() {
    return localRepository.get();
  }

  @Override public Local getById(long id) {
    return localRepository.getById(id);
  }

  @Override public void create(String description) {
    Local local = new Local(description);
    local.isValid();
    localRepository.create(local);
  }

  @Override public void updateDescription(int localId, String description) {
    Local local = localRepository.getById((long) localId);
    local.updateDescription(description);
    local.isValid();
    localRepository.update(local);
  }

  @Override public void remove(Local local) {
    localRepository.remove(local);
  }
}
