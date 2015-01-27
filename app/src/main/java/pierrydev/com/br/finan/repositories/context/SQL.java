package pierrydev.com.br.finan.repositories.context;

import android.content.Context;
import android.util.Log;

public class SQL extends DAO {

	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table usuario (idusuario integer primary key autoincrement, usuario text, senha text, salvo integer);",
			"create table lancamento (idlanc integer primary key autoincrement, idlocal integer, tipo integer," +
                    "descricao text, valor real, data text, idusuario integer, enviado integer, " +
                    "foreign key (idlocal) references local(idlocal)," +
                    "foreign key (idusuario) references usuario(idusuario)" +
                    ");",
            "create table local (idlocal integer primary key autoincrement, descricao text);",
	};

	private SQLiteHelper dbHelper;

	public SQL(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, super.NOME_BANCO, super.VERSAO_BANCO,
				SCRIPT_DATABASE_CREATE);
		db = dbHelper.getWritableDatabase();
		Log.i("Inserido", "Inserido dados");
	}
	
	@Override
	public void closeDB() {
		super.closeDB();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}