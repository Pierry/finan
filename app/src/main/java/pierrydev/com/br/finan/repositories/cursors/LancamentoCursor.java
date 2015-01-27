package pierrydev.com.br.finan.repositories.cursors;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import pierrydev.com.br.finan.repositories.context.DAO;
import pierrydev.com.br.finan.repositories.context.SQL;
import pierrydev.com.br.finan.repositories.contracts.LancamentoCursorImpl;
import pierrydev.com.br.finan.repositories.entities.LancamentoDbModel;

public class LancamentoCursor extends DAO implements LancamentoCursorImpl {

    public static DAO dao;

    public LancamentoCursor(Context context) {
        dao = new SQL(context);
        db = dao.db;
    }

    @Override
    public Cursor getCursor(int pag) {
        try {
            if (pag > 0) {
                pag = pag * 25;
            }
            return db.query(LANC, LancamentoDbModel.colunas, null, null, null,
                    null, null, pag + ", 25");
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Cursor getCursorById(int id) {
        try {
            return db.query(LANC, LancamentoDbModel.colunas, LancamentoDbModel.IDLANC
                            + " = " + id, null, null, null,
                    null, null);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Cursor getCursorByIdLocal(int idLocal) {
        try {
            return db.query(LANC, LancamentoDbModel.colunas, LancamentoDbModel.LOCAL
                            + " = " + idLocal, null, null, null,
                    null, null);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Cursor getCursor() {
        try {
            return db.query(LANC, LancamentoDbModel.colunasSomandoValorAgrupado, null, null, LancamentoDbModel.LOCAL,
                    null, null);
        } catch (SQLException e) {
            return null;
        }
    }
}
