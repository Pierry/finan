package pierrydev.com.br.finan.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import pierrydev.com.br.finan.domain.contracts.repositories.LocalRepositoryImpl;
import pierrydev.com.br.finan.domain.entities.Local;
import pierrydev.com.br.finan.repositories.cursors.LocalCursor;
import pierrydev.com.br.finan.repositories.entities.LocalDbModel;

public class LocalRepository extends LocalCursor implements LocalRepositoryImpl {

    public LocalRepository(Context context) {
        super(context);
    }

    @Override
    public List<Local> get(int pag) {
        return listaCompleta(getCursor(pag));
    }

    @Override
    public Local getById(int id) {
        List<Local> lista = listaCompleta(getCursorById(id));
        return lista != null && lista.size() > 0 ? lista.get(0) : null;
    }

    @Override
    public Local add(Local item) {
        ContentValues values = new ContentValues();
        values.put(LocalDbModel.DESCRICAO, item.getDescricao());

        long id = db.insert(LOCAL, "", values);
        item.setIdLocal(id);
        return item;
    }

    @Override
    public boolean update(Local item) {
        ContentValues values = new ContentValues();
        values.put(LocalDbModel.DESCRICAO, item.getDescricao());

        String _id = String.valueOf(item.getIdLocal());
        String where = LocalDbModel.ID_LOCAL + "=?";
        String[] whereArgs = new String[]{_id};
        int count = db.update(USUARIO, values, where, whereArgs);
        return count > 0;
    }

    @Override
    public boolean remove(int id) {
        String _id = String.valueOf(id);
        String where = LocalDbModel.ID_LOCAL + "=?";
        String[] whereArgs = new String[]{_id};
        int count = db.delete(LOCAL, where, whereArgs);
        return count > 0;
    }

    private List<Local> listaCompleta(Cursor c) {
        List<Local> locais = new ArrayList<>();
        if (c.moveToFirst()) {
            int idxIdLocal = c.getColumnIndex(LocalDbModel.ID_LOCAL);
            int idxDescricao = c.getColumnIndex(LocalDbModel.DESCRICAO);
            do {
                Local local = new Local();
                locais.add(local);
                local.setIdLocal(c.getLong(idxIdLocal));
                local.setDescricao(c.getString(idxDescricao));
            } while (c.moveToNext());
        }
        return locais;
    }
}


