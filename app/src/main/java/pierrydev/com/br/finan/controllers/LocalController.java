package pierrydev.com.br.finan.controllers;

import java.util.List;

import pierrydev.com.br.finan.domain.contracts.controllers.LocalControllerImpl;
import pierrydev.com.br.finan.domain.contracts.services.LocalServiceImpl;
import pierrydev.com.br.finan.domain.entities.Local;

public class LocalController implements LocalControllerImpl{

    private LocalServiceImpl _localService;

    public LocalController(LocalServiceImpl localService) {
        _localService = localService;
    }

    @Override
    public List<Local> get(int pag) {
        return _localService.get(pag);
    }

    @Override
    public Local getById(int id) {
        return _localService.getById(id);
    }

    @Override
    public Local add(Local item) {
        return _localService.add(item);
    }

    @Override
    public boolean update(Local item) {
        return _localService.update(item);
    }

    @Override
    public boolean remove(int id) {
        return _localService.remove(id);
    }
}
