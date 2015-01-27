package pierrydev.com.br.finan.services;

import java.util.List;

import pierrydev.com.br.finan.domain.contracts.repositories.LancamentoRepositoryImpl;
import pierrydev.com.br.finan.domain.contracts.services.LancamentoServiceImpl;
import pierrydev.com.br.finan.domain.entities.Lancamento;

public class LancamentoService implements LancamentoServiceImpl {

    private LancamentoRepositoryImpl _lancamentoRepository;

    public LancamentoService(LancamentoRepositoryImpl lancamentoRepository) {
        _lancamentoRepository = lancamentoRepository;
    }

    @Override
    public List<Lancamento> get(int pag) {
        return _lancamentoRepository.get(pag);
    }

    @Override
    public Lancamento getById(int id) {
        return _lancamentoRepository.getById(id);
    }

    @Override
    public Lancamento add(Lancamento item) {
        return _lancamentoRepository.add(item);
    }

    @Override
    public boolean update(Lancamento item) {
        return _lancamentoRepository.update(item);
    }

    @Override
    public boolean remove(int id) {
        return _lancamentoRepository.remove(id);
    }

    @Override
    public List<Lancamento> getByIdLocal(int idLocal){
        return _lancamentoRepository.getByIdLocal(idLocal);
    }

    @Override
    public List<Lancamento> get() {
        return _lancamentoRepository.get();
    }
}
