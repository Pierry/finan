package pierrydev.com.br.finan.services;

import java.util.List;

import pierrydev.com.br.finan.domain.contracts.repositories.UsuarioRepositoryImpl;
import pierrydev.com.br.finan.domain.contracts.services.UsuarioServiceImpl;
import pierrydev.com.br.finan.domain.entities.Usuario;

public class UsuarioService implements UsuarioServiceImpl {

    private UsuarioRepositoryImpl _usuarioRepository;

    public UsuarioService(UsuarioRepositoryImpl usuarioRepository) {
        _usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> get(int pag) {
        return _usuarioRepository.get(pag);
    }

    @Override
    public Usuario getById(int id) {
        return _usuarioRepository.getById(id);
    }

    @Override
    public Usuario add(Usuario item) {
        return _usuarioRepository.add(item);
    }

    @Override
    public boolean update(Usuario item) {
        return _usuarioRepository.update(item);
    }

    @Override
    public boolean remove(int id) {
        return _usuarioRepository.remove(id);
    }

    @Override
    public Usuario getByUser(String user) {
        return _usuarioRepository.getByUser(user);
    }

    @Override
    public Usuario getByUserPass(String user, String pass) {
        return _usuarioRepository.getByUserPass(user, pass);
    }
}
