package pierrydev.com.br.finan.repositories.context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper {

    private String[] scriptSQLCreate;

    SQLiteHelper(Context context, String nomeBanco, int versaoBanco,
                 String[] scriptSQLCreate) {
        super(context, nomeBanco, null, versaoBanco);
        this.scriptSQLCreate = scriptSQLCreate;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        int qtdeScripts = scriptSQLCreate.length;

        // Executa cada sql passado como parâmetro
        for (int i = 0; i < qtdeScripts; i++) {
            String sql = scriptSQLCreate[i];
            // Cria o banco de dados executando o script de criaçăo
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        // Deleta as tabelas...
        //db.execSQL(scriptSQLDelete);
        // Cria novamente...
        //onCreate(db);
    }
}