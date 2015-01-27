package pierrydev.com.br.finan.utilities;

import android.app.Activity;
import android.app.ProgressDialog;

public class Progress {

	private static ProgressDialog dialog;

	public static ProgressDialog getDialog(String msg, Activity ac) {
		dialog = ProgressDialog.show(ac, "", msg);
		dialog.setCancelable(true);
		return dialog;
	}

	public static ProgressDialog getShow(Activity ac) {
		dialog = ProgressDialog.show(ac, "", "Carregando...");
		dialog.setCancelable(true);
		return dialog;
	}

    public static ProgressDialog get(Activity ac) {
        dialog = new ProgressDialog(ac);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(true);
        return dialog;
    }

}
