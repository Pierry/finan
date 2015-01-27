package pierrydev.com.br.finan.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import pierrydev.com.br.finan.domain.contracts.repositories.LancamentoRepositoryImpl;
import pierrydev.com.br.finan.domain.entities.Lancamento;
import pierrydev.com.br.finan.repositories.cursors.LancamentoCursor;
import pierrydev.com.br.finan.repositories.entities.LancamentoDbModel;

public class LancamentoRepository extends LancamentoCursor implements LancamentoRepositoryImpl {

    public LancamentoRepository(Context context) {
        super(context);
    }

    @Override
    public List<Lancamento> get(int pag) {
        return listaCompleta(getCursor(pag));
    }

    @Override
    public Lancamento getById(int id) {
        List<Lancamento> lista = listaCompleta(getCursorById(id));
        return lista != null && lista.size() > 0 ? lista.get(0) : null;
    }

    @Override
    public Lancamento add(Lancamento item) {
        ContentValues values = new ContentValues();
        values.put(LancamentoDbModel.VALOR, item.getValor());
        values.put(LancamentoDbModel.DESCRICAO, item.getDescricao());
        values.put(LancamentoDbModel.LOCAL, item.getLocal());
        values.put(LancamentoDbModel.TIPO, item.getTipo());
        values.put(LancamentoDbModel.ENVIADO, item.getEnviado());
        values.put(LancamentoDbModel.DATA, item.getData());
        values.put(LancamentoDbModel.IDUSUARIO, item.getIdUsuario());

        long id = db.insert(LANC, "", values);
        item.setIdLancamento(id);
        return item;
    }

    @Override
    public boolean update(Lancamento item) {
        ContentValues values = new ContentValues();
        values.put(LancamentoDbModel.VALOR, item.getValor());
        values.put(LancamentoDbModel.DESCRICAO, item.getDescricao());
        values.put(LancamentoDbModel.LOCAL, item.getLocal());
        values.put(LancamentoDbModel.TIPO, item.getTipo());
        values.put(LancamentoDbModel.ENVIADO, item.getEnviado());
        values.put(LancamentoDbModel.DATA, item.getData());
        values.put(LancamentoDbModel.IDUSUARIO, item.getIdUsuario());

        String _id = String.valueOf(item.getIdLancamento());
        String where = LancamentoDbModel.IDLANC + "=?";
        String[] whereArgs = new String[]{_id};
        int count = db.update(LANC, values, where, whereArgs);
        return count > 0;
    }

    @Override
    public boolean remove(int id) {
        String _id = String.valueOf(id);
        String where = LancamentoDbModel.IDLANC + "=?";
        String[] whereArgs = new String[]{_id};
        int count = db.delete(LANC, where, whereArgs);
        return count > 0;
    }

    private List<Lancamento> listaCompleta(Cursor c) {
        List<Lancamento> lancs = new ArrayList<>();
        if (c.moveToFirst()) {
            int idxIdLancamento = c.getColumnIndex(LancamentoDbModel.IDLANC);
            int idxValor = c.getColumnIndex(LancamentoDbModel.VALOR);
            int idxDescricao = c.getColumnIndex(LancamentoDbModel.DESCRICAO);
            int idxLocal = c.getColumnIndex(LancamentoDbModel.LOCAL);
            int idxTipo = c.getColumnIndex(LancamentoDbModel.TIPO);
            int idxEnviado = c.getColumnIndex(LancamentoDbModel.ENVIADO);
            int idxData = c.getColumnIndex(LancamentoDbModel.DATA);
            int idxIdUsuario = c.getColumnIndex(LancamentoDbModel.IDUSUARIO);
            do {
                Lancamento lanc = new Lancamento();
                lancs.add(lanc);
                lanc.setIdLancamento(c.getLong(idxIdLancamento));
                lanc.setValor(c.getDouble(idxValor));
                lanc.setDescricao(c.getString(idxDescricao));
                lanc.setLocal(c.getInt(idxLocal));
                lanc.setTipo(c.getInt(idxTipo));
                lanc.setEnviado(c.getInt(idxEnviado));
                lanc.setData(c.getString(idxData));
                lanc.setIdUsuario(c.getInt(idxIdUsuario));
            } while (c.moveToNext());
        }
        return lancs;
    }

    @Override
    public List<Lancamento> getByIdLocal(int idLocal){
        return listaCompleta(getCursorByIdLocal(idLocal));
    }

    @Override
    public List<Lancamento> get() {
        return listaCompleta(getCursor());
    }
}
