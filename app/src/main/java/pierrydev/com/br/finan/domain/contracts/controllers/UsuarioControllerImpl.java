package pierrydev.com.br.finan.domain.contracts.controllers;

import pierrydev.com.br.finan.domain.entities.Usuario;

public interface UsuarioControllerImpl extends ControllerBaseImpl<Usuario> {

    Usuario getByUser(String user);
    Usuario getByUserPass(String user, String pass);

}
