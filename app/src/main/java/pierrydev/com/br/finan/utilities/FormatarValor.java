package pierrydev.com.br.finan.utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FormatarValor {

	private static DecimalFormat myFormatter;
	
	public static String getValor(Double valor){
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setGroupingSeparator('.');
		formatSymbols.setDecimalSeparator('.');
		
		myFormatter = new DecimalFormat("00.00");
		myFormatter.setDecimalFormatSymbols(formatSymbols);
		String v = myFormatter.format(valor);
		return "R$" + v;
	}
	
}
