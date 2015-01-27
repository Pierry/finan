package pierrydev.com.br.finan.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import pierrydev.com.br.finan.domain.contracts.repositories.UsuarioRepositoryImpl;
import pierrydev.com.br.finan.domain.entities.Usuario;
import pierrydev.com.br.finan.repositories.cursors.UsuarioCursor;
import pierrydev.com.br.finan.repositories.entities.UsuarioDbModel;

public class UsuarioRepository extends UsuarioCursor implements UsuarioRepositoryImpl {

    public UsuarioRepository(Context context) {
        super(context);
    }

    @Override
    public List<Usuario> get(int pag) {
        return listaCompleta(getCursor(pag));
    }

    @Override
    public Usuario getById(int id) {
        List<Usuario> lista = listaCompleta(getCursorById(id));
        return lista != null && lista.size() > 0 ? lista.get(0) : null;
    }

    @Override
    public Usuario add(Usuario item) {
        ContentValues values = new ContentValues();
        values.put(UsuarioDbModel.USUARIO, item.getUsuario());
        values.put(UsuarioDbModel.SENHA, item.getSenha());
        values.put(UsuarioDbModel.SALVO, item.getSalvo());

        long id = db.insert(USUARIO, "", values);
        item.setIdUsuario(id);
        return item;
    }

    @Override
    public boolean update(Usuario item) {
        ContentValues values = new ContentValues();
        values.put(UsuarioDbModel.USUARIO, item.getUsuario());
        values.put(UsuarioDbModel.SENHA, item.getSenha());
        values.put(UsuarioDbModel.SALVO, item.getSalvo());

        String _id = String.valueOf(item.getIdUsuario());
        String where = UsuarioDbModel.IDUSUARIO + "=?";
        String[] whereArgs = new String[]{_id};
        int count = db.update(USUARIO, values, where, whereArgs);
        return count > 0 ? true : false;
    }

    @Override
    public boolean remove(int id) {
        String _id = String.valueOf(id);
        String where = UsuarioDbModel.IDUSUARIO + "=?";
        String[] whereArgs = new String[]{_id};
        int count = db.delete(USUARIO, where, whereArgs);
        return count > 0 ? true : false;
    }

    private List<Usuario> listaCompleta(Cursor c) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (c.moveToFirst()) {
            int idxIdUsuario = c.getColumnIndex(UsuarioDbModel.IDUSUARIO);
            int idxUsuario = c.getColumnIndex(UsuarioDbModel.USUARIO);
            int idxSenha = c.getColumnIndex(UsuarioDbModel.SENHA);
            int idxSalvo = c.getColumnIndex(UsuarioDbModel.SALVO);
            do {
                Usuario cliente = new Usuario();
                usuarios.add(cliente);
                cliente.setIdUsuario(c.getLong(idxIdUsuario));
                cliente.setUsuario(c.getString(idxUsuario));
                cliente.setSenha(c.getString(idxIdUsuario));
                cliente.setSalvo(c.getInt(idxSalvo));
            } while (c.moveToNext());
        }
        return usuarios;
    }

    @Override
    public Usuario getByUser(String user) {
        List<Usuario> lista = listaCompleta(getCursorByUser(user));
        return lista != null && lista.size() > 0 ? lista.get(0) : null;
    }

    @Override
    public Usuario getByUserPass(String user, String pass) {
        List<Usuario> lista = listaCompleta(getCursorByUserPass(user, pass));
        return lista != null && lista.size() > 0 ? lista.get(0) : null;
    }
}

