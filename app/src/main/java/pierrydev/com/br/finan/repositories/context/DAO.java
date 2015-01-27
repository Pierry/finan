package pierrydev.com.br.finan.repositories.context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO {

    protected final String NOME_BANCO = "finanappdatabase14";
    protected final int VERSAO_BANCO = 1;

    public static final String USUARIO = "usuario";
    public static final String LANC = "lancamento";
    public static final String LOCAL = "local";

    public SQLiteDatabase db;

    public DAO(Context ctx) {
        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
    }

    protected DAO() {

    }

    public void closeDB() {
        if (db != null) {
            db.close();
        }
    }

}
