package pierrydev.com.br.finan.repositories.cursors;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import pierrydev.com.br.finan.repositories.context.DAO;
import pierrydev.com.br.finan.repositories.context.SQL;
import pierrydev.com.br.finan.repositories.contracts.UsuarioCursorImpl;
import pierrydev.com.br.finan.repositories.entities.UsuarioDbModel;

public class UsuarioCursor extends DAO implements UsuarioCursorImpl{

    public static DAO dao;

    public UsuarioCursor(Context context) {
        dao = new SQL(context);
        db = dao.db;
    }

    @Override
    public Cursor getCursor(int pag) {
        try {
            if (pag > 0){
                pag = pag * 25;
            }
            return db.query(USUARIO, UsuarioDbModel.colunas, null, null, null,
                    null, UsuarioDbModel.USUARIO, pag + ", 25");
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Cursor getCursorById(int id) {
        try {
            return db.query(USUARIO, UsuarioDbModel.colunas, UsuarioDbModel.IDUSUARIO
                            + " = " + id, null, null, null,
                    UsuarioDbModel.USUARIO, null);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Cursor getCursorByUser(String user) {
        try {
            return db.query(USUARIO, UsuarioDbModel.colunas, UsuarioDbModel.USUARIO
                            + " = '" + user + "'", null, null, null,
                    UsuarioDbModel.USUARIO, null);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Cursor getCursorByUserPass(String user, String pass) {
        try {
            return db.query(USUARIO, UsuarioDbModel.colunas, UsuarioDbModel.USUARIO
                            + " = '" + user + "' and " + UsuarioDbModel.SENHA + " = '" + pass + "'", null, null, null,
                    UsuarioDbModel.USUARIO, null);
        } catch (SQLException e) {
            return null;
        }
    }
}
