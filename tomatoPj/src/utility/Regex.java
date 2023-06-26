package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public boolean regexPassword(String password) {
		
		Pattern pattern = Pattern.compile("(?=(.*[0-9]))((?=.*[A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z]))^.{8,}$");
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			return true;	
		} else {
			return false;
		}
	}
	
	public boolean regexEmail(String email) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;	
		} else {
			return false;
		}
	}
}
