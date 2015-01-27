package pierrydev.com.br.finan.domain.contracts.services;

import java.util.List;

import pierrydev.com.br.finan.domain.entities.Lancamento;

public interface LancamentoServiceImpl extends ServiceBaseImpl<Lancamento> {

    List<Lancamento> getByIdLocal(int idLocal);
    List<Lancamento> get();

}
