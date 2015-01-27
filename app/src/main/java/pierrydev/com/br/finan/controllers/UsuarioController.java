package pierrydev.com.br.finan.controllers;

import java.util.List;

import pierrydev.com.br.finan.domain.contracts.controllers.UsuarioControllerImpl;
import pierrydev.com.br.finan.domain.contracts.services.UsuarioServiceImpl;
import pierrydev.com.br.finan.domain.entities.Usuario;

public class UsuarioController implements UsuarioControllerImpl {

    private UsuarioServiceImpl _usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        _usuarioService = usuarioService;
    }


    @Override
    public List<Usuario> get(int pag) {
        return _usuarioService.get(pag);
    }

    @Override
    public Usuario getById(int id) {
        return _usuarioService.getById(id);
    }

    @Override
    public Usuario add(Usuario item) {
        return _usuarioService.add(item);
    }

    @Override
    public boolean update(Usuario item) {
        return _usuarioService.update(item);
    }

    @Override
    public boolean remove(int id) {
        return _usuarioService.remove(id);
    }

    @Override
    public Usuario getByUser(String user) {
        return _usuarioService.getByUser(user);
    }

    @Override
    public Usuario getByUserPass(String user, String pass) {
        return _usuarioService.getByUserPass(user, pass);
    }
}
