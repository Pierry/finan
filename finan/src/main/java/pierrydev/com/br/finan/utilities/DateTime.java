package pierrydev.com.br.finan.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTime {
	
	private String dia;
	private String mes;
	private String mesAbrv;
	private String ano;
	private String hora;
	private String minuto;
	private String segundo;
	
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	public String getDia() {
		return dia;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public String getMes() {
		return mes;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public String getAno() {
		return ano;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getHora() {
		return hora;
	}
	
	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}
	
	public String getMinuto() {
		return minuto;
	}
	
	public void setSegundo(String segundo) {
		this.segundo = segundo;
	}
	
	public String getSegundo() {
		return segundo;
	}
	
	public void setMesAbrv(String mesAbrv) {
		this.mesAbrv = mesAbrv;
	}
	
	public String getMesAbrv() {
		return mesAbrv;
	}

    public static String getDateAndTime(){

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);

        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));

        if (day.length() < 2) { day = "0" + day; }
        if (month.length() < 2) { month = "0" + month; }
        if (hour.length() < 2) { hour = "0" + hour; }
        if (minute.length() < 2) { minute = "0" + minute; }
        if (second.length() < 2) { second = "0" + second; }
        return day + "-" + month + "-" + year.substring(2, 4) + " " + hour + ":" + minute + ":" + second ;
    }
    
    public static String getDateJrPattern(){

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);

        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));

        if (day.length() < 2) { day = "0" + day; }
        if (month.length() < 2) { month = "0" + month; }
        if (hour.length() < 2) { hour = "0" + hour; }
        if (minute.length() < 2) { minute = "0" + minute; }
        if (second.length() < 2) { second = "0" + second; }
        return year + "-" + month + "-" + day;
    }
    
    public static List<String> diasSemana(){
    	List<String> dias = new ArrayList<String>();
    	dias.add("SEGUNDA");
    	dias.add("TER�A");
    	dias.add("QUARTA");
    	dias.add("QUINTA");
    	dias.add("SEXTA");
    	dias.add("SABADO");
    	dias.add("DOMINGO");
    	return dias;
    }
    
    public static String getMonth(){

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);

        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        //String year = String.valueOf(calendar.get(Calendar.YEAR));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));

        if (day.length() < 2) { day = "0" + day; }
        if (month.length() < 2) { month = "0" + month; }
        if (hour.length() < 2) { hour = "0" + hour; }
        if (minute.length() < 2) { minute = "0" + minute; }
        if (second.length() < 2) { second = "0" + second; }
        return month;
    }

    public static String getDay(){

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);

        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        //String year = String.valueOf(calendar.get(Calendar.YEAR));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));

        if (day.length() < 2) { day = "0" + day; }
        if (month.length() < 2) { month = "0" + month; }
        if (hour.length() < 2) { hour = "0" + hour; }
        if (minute.length() < 2) { minute = "0" + minute; }
        if (second.length() < 2) { second = "0" + second; }
        return day;
    }
    
    public static String getYear(){

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);

        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));

        if (day.length() < 2) { day = "0" + day; }
        if (month.length() < 2) { month = "0" + month; }
        if (hour.length() < 2) { hour = "0" + hour; }
        if (minute.length() < 2) { minute = "0" + minute; }
        if (second.length() < 2) { second = "0" + second; }
        return year;
    }
    
    public static String getDayOfWeek(int dia){

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();        
        calendar.setTime(date);

        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        //String year = String.valueOf(calendar.get(Calendar.YEAR));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));

        if (day.length() < 2) { day = "0" + day; }
        if (month.length() < 2) { month = "0" + month; }
        if (hour.length() < 2) { hour = "0" + hour; }
        if (minute.length() < 2) { minute = "0" + minute; }
        if (second.length() < 2) { second = "0" + second; }
        return day;
    }

    public static String getAbreviacao(int i){
    	switch (i) {
		case 1:
			return "JAN";
		case 2:
			return "FEV";
		case 3:
			return "MAR";
		case 4:
			return "ABR";
		case 5:
			return "MAI";
		case 6:
			return "JUN";
		case 7:
			return "JUL";
		case 8:
			return "AGO";
		case 9:
			return "SET";
		case 10:
			return "OUT";
		case 11:
			return "NOV";
		case 12:
			return "DEZ";
		default:
			break;
		}
		return "";
    }
}
