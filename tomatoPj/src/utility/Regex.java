package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPasswordField;

public class Regex {

	public String pwdToString(JPasswordField jPasswordField) {
		String pwd="";
		char[] a = jPasswordField.getPassword();
		for(int i=0;i<jPasswordField.getPassword().length;i++) {
			pwd += a[i];
		}
		
		return pwd;
	}
	
	public boolean regexName(String name) {
		Pattern pattern = Pattern.compile("^[가-힣]{2,10}$");
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			return true;	
		} else {
			return false;
		}
	}
	
	public boolean regexId(String id) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]{5,15}$");
		Matcher matcher = pattern.matcher(id);
		if (matcher.matches()) {
			return true;	
		} else {
			return false;
		}
	}
	
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
//	public static void main(String[] args) {
//		Regex regex = new Regex();
//		System.out.println(regex.regexName("황금독수리세상을놀라"));
//	}
}
