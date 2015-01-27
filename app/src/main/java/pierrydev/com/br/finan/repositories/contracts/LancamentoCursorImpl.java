package pierrydev.com.br.finan.repositories.contracts;

import android.database.Cursor;

public interface LancamentoCursorImpl extends CursorBase {

    Cursor getCursorByIdLocal(int idLocal);
    Cursor getCursor();

}
