package pierrydev.com.br.finan.repositories.contracts;

import android.database.Cursor;

public interface UsuarioCursorImpl extends CursorBase {

    Cursor getCursorByUser(String user);
    Cursor getCursorByUserPass(String user, String pass);

}
