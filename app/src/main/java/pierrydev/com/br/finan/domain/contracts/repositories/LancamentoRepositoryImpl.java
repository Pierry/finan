package pierrydev.com.br.finan.domain.contracts.repositories;

import java.util.List;

import pierrydev.com.br.finan.domain.entities.Lancamento;

public interface LancamentoRepositoryImpl extends RepositoryBaseImpl<Lancamento>{

    List<Lancamento> getByIdLocal(int idLocal);
    List<Lancamento> get();

}
