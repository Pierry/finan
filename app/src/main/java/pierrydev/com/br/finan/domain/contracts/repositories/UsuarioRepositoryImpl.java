package pierrydev.com.br.finan.domain.contracts.repositories;

import pierrydev.com.br.finan.domain.entities.Usuario;

public interface UsuarioRepositoryImpl extends RepositoryBaseImpl<Usuario>{

    Usuario getByUser(String user);
    Usuario getByUserPass(String user, String pass);

}
