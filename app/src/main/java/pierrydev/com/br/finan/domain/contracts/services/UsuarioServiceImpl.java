package pierrydev.com.br.finan.domain.contracts.services;

import pierrydev.com.br.finan.domain.entities.Usuario;

public interface UsuarioServiceImpl extends ServiceBaseImpl<Usuario>{

    Usuario getByUser(String user);
    Usuario getByUserPass(String user, String pass);

}
