package Aufgabe3;

import Aufgabe3.Exception.DateFormatException;
import Aufgabe3.Exception.DateRangeException;
import Aufgabe3.Exception.DayRangeException;
import Aufgabe3.Exception.MonthRangeException;
import Model.MainModel;

public class Model extends MainModel {
	
	private int day, month, year, century;
	private static int[] MONTHS = {31,28,31,30,31,30,31,31,30,31,30,31};
	private static final String[] DAY_OF_WEEK	=	{"Sonntag","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag"};
	
	public void calculateDayOfWeek(){
		int schaltjahr = 0;
		if(this.isLeapyear((century*100)+year)){
			schaltjahr = 6;
			Model.MONTHS[1] = 29;
		}
	
		day = day%7;
		int[] months = this.calculateMonthRemainder(Model.MONTHS);
		year = (year+year/4)%7;
		century = (3-(century%4))*2;
		
		int rest = (day+months[month-1]+century+year+schaltjahr)%7;
		
		this.setChanged();
		this.notifyObservers(Model.DAY_OF_WEEK[rest]);
	}
	
	private boolean isLeapyear(int year){
		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			return true;
		else
			return false;
	}
	
	private int[] calculateMonthRemainder(int[] month){
		int[] monthRemainder = new int[month.length];
		monthRemainder[0] = 0;
		monthRemainder[1] = month[0]%7 + monthRemainder[0];
		for(int i=1;i<month.length-1;i++){
			monthRemainder[i+1] = ( month[i] % 7 + monthRemainder[i])%7;
		}
		return monthRemainder;
	}
	
	/**
	 * 
	 * @param date
	 * @throws DayRangeException
	 * @throws MonthRangeException
	 * @throws DateRangeException
	 * @throws DateFormatException
	 */
	public void parseDate(String date) throws DayRangeException, MonthRangeException, DateRangeException, DateFormatException{
		if(date.matches("[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,4}")){
			String[] dateArray = date.split("\\.");
			System.out.println(date + " - "+dateArray.length);
			if(Integer.parseInt(dateArray[1]) >12 || Integer.parseInt(dateArray[1]) <= 0){
				throw new MonthRangeException("Es gibt nur Monate im Bereich von 1 bis 12");
			}else if(Integer.parseInt(dateArray[0]) > Model.MONTHS[Integer.parseInt(dateArray[1])-1] ||
					Integer.parseInt(dateArray[0]) <= 0){
				throw new DayRangeException("Ein Monat kann nicht mehr Tage als "+Model.MONTHS[Integer.parseInt(dateArray[1])]+" und nicht weniger als 1 Tag haben");
			}
			if(	(Integer.parseInt(dateArray[2]) < 1700 ) ||
				(Integer.parseInt(dateArray[2]) > 2100)){
				throw new DateRangeException("Datum liegt auﬂerhalb des definierten Bereichs (1. 1. 1700 und dem 31.12. 2100)");
			}
			this.day 		= Integer.parseInt(dateArray[0]);
			this.month 		= Integer.parseInt(dateArray[1]);
			this.century	= Integer.parseInt(dateArray[2].substring(0, 2));
			this.year		= Integer.parseInt(dateArray[2].substring(2, 4));
			System.out.print("-"+this.year+" - "+this.century);
		}else{
			throw new DateFormatException("Das Format muss dd.mm.yyyy \n oder d.m.yyyy sein");
		}
		
	}
}
