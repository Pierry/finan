package pierrydev.com.br.finan.domain.contracts.controllers;

import java.util.List;

import pierrydev.com.br.finan.domain.entities.Lancamento;

public interface LancamentoControllerImpl extends ControllerBaseImpl<Lancamento> {

    List<Lancamento> getByIdLocal(int idLocal);
    List<Lancamento> get();

}
