package pierrydev.com.br.finan.controllers;

import java.util.List;

import pierrydev.com.br.finan.domain.contracts.controllers.LancamentoControllerImpl;
import pierrydev.com.br.finan.domain.contracts.services.LancamentoServiceImpl;
import pierrydev.com.br.finan.domain.entities.Lancamento;

public class LancamentoController implements LancamentoControllerImpl {

    private LancamentoServiceImpl _lancamentoService;

    public LancamentoController(LancamentoServiceImpl lancamentoService) {
        _lancamentoService = lancamentoService;
    }

    @Override
    public List<Lancamento> get(int pag) {
        return _lancamentoService.get(pag);
    }

    @Override
    public Lancamento getById(int id) {
        return _lancamentoService.getById(id);
    }

    @Override
    public Lancamento add(Lancamento item) {
        return _lancamentoService.add(item);
    }

    @Override
    public boolean update(Lancamento item) {
        return _lancamentoService.update(item);
    }

    @Override
    public boolean remove(int id) {
        return _lancamentoService.remove(id);
    }

    @Override
    public List<Lancamento> getByIdLocal(int idLocal) {
        return _lancamentoService.getByIdLocal(idLocal);
    }

    @Override
    public List<Lancamento> get() {
        return _lancamentoService.get();
    }
}
