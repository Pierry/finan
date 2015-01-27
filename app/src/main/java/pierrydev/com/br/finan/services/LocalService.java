package pierrydev.com.br.finan.services;

import java.util.List;

import pierrydev.com.br.finan.domain.contracts.repositories.LocalRepositoryImpl;
import pierrydev.com.br.finan.domain.contracts.services.LocalServiceImpl;
import pierrydev.com.br.finan.domain.entities.Local;

public class LocalService implements LocalServiceImpl {

    private LocalRepositoryImpl _localRepository;

    public LocalService(LocalRepositoryImpl localRepository) {
        _localRepository = localRepository;
    }

    @Override
    public List<Local> get(int pag) {
        return _localRepository.get(pag);
    }

    @Override
    public Local getById(int id) {
        return _localRepository.getById(id);
    }

    @Override
    public Local add(Local item) {
        return _localRepository.add(item);
    }

    @Override
    public boolean update(Local item) {
        return _localRepository.update(item);
    }

    @Override
    public boolean remove(int id) {
        return _localRepository.remove(id);
    }
}
