package pierrydev.com.br.finan.repositories.cursors;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import pierrydev.com.br.finan.repositories.context.DAO;
import pierrydev.com.br.finan.repositories.context.SQL;
import pierrydev.com.br.finan.repositories.contracts.LocalCursorImpl;
import pierrydev.com.br.finan.repositories.entities.LocalDbModel;

public class LocalCursor extends DAO implements LocalCursorImpl {

    public static DAO dao;

    public LocalCursor(Context context) {
        dao = new SQL(context);
        db = dao.db;
    }

    @Override
    public Cursor getCursor(int pag) {
        try {
            if (pag > 0) {
                pag = pag * 25;
            }
            return db.query(LOCAL, LocalDbModel.colunas, null, null, null,
                    null, null, pag + ", 25");
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Cursor getCursorById(int id) {
        try {
            return db.query(LOCAL, LocalDbModel.colunas, LocalDbModel.ID_LOCAL
                            + " = " + id, null, null, null,
                    null, null);
        } catch (SQLException e) {
            return null;
        }
    }
}
