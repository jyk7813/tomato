package pnl;

import java.time.LocalDate;

import utility.CalendarData;

public class DateTest {
	public static void main(String[] args) {
		CalendarData c = new CalendarData();
		
//		System.out.println(c.getCurrentDate());
//		System.out.println(c.getCurrentSelDate("YEAR"));
//		System.out.println(c.selectCurrentDate(LocalDate.of(2023, 05, 20), "dayofmonth"));
		
		c.printCal(2023, 06);
	}
}
