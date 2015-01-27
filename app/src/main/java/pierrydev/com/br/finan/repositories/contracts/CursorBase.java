package pierrydev.com.br.finan.repositories.contracts;

import android.database.Cursor;

public interface CursorBase {

    Cursor getCursor(int pag);
    Cursor getCursorById(int id);

}
