package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalendarData {
	/**
	 * (달력)날짜 관련 메소드
	 * 
	 * 현재 날짜 구해서 포맷 적용된 String타입으로 결과 반환 (2023년 06월 28일 수요일)
	 * 
	 * @return String타입의 현재 날짜
	 */
	public String getCurrentDate() {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		String month = now.getMonth().toString();
		int monthValue = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
		int dayOfYear = now.getDayOfYear();
		String dayOfWeek = now.getDayOfWeek().toString();
		int dayOfWeekValue = now.getDayOfWeek().getValue();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일");
		String formatedNow = now.format(formatter);

		return formatedNow;
	}

	/**
	 * (달력)날짜 관련 메소드
	 * 
	 * 현재 날짜를 기준으로 년, 월(문자열, 숫자), 일, 요일, 일(Year 기준) 추출
	 * 
	 * @param String 원하는날짜값 (메소드 내 변수명 참고)
	 * @return String타입의 원하는 날짜값
	 */
	public String getCurrentSelDate(String str) {
		String select = str.toLowerCase();
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		String month = now.getMonth().toString(); 
		int monthValue = now.getMonthValue(); // 달을 숫자로 표현
		int dayOfMonth = now.getDayOfMonth();
		int dayOfYear = now.getDayOfYear();
		String dayOfWeek = now.getDayOfWeek().toString();
		int dayOfWeekValue = now.getDayOfWeek().getValue(); // 요일을 숫자로 표현 (ex. 월-1)
		
		if(select.equals("year")) {
			return String.valueOf(year);
		} else if(select.equals("month")) {
			return month;
		} else if(select.equals("monthvalue")) {
			return String.valueOf(monthValue);
		} else if(select.equals("dayofmonth")) {
			return String.valueOf(dayOfMonth);
		} else if(select.equals("dayofyear")) {
			return String.valueOf(dayOfYear);
		} else if(select.equals("dayofweek")) {
			return dayOfWeek;
		} else {
			return String.valueOf(dayOfWeekValue);
		}
	}
	
	public String setCurrentDate(LocalDate sel) {
		LocalDate now = sel;
		int year = now.getYear();
		String month = now.getMonth().toString();
		int monthValue = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
		int dayOfYear = now.getDayOfYear();
		String dayOfWeek = now.getDayOfWeek().toString();
		int dayOfWeekValue = now.getDayOfWeek().getValue();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일");
		String formatedNow = now.format(formatter);

		return formatedNow;
	}
}
