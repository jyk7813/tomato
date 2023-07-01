package dbutil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import tomatoPj.Task;

public class DBUtil {
	private static final Properties PROPS = new Properties();
	private static DataSource dataSource;
	static {
		try {
			PROPS.load(DBUtil.class.getClassLoader().getResourceAsStream("mysql.properties"));
//			Class.forName(PROPS.getProperty("jdbc.DRIVER"));
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName(PROPS.getProperty("jdbc.DRIVER"));
			ds.setUrl(PROPS.getProperty("jdbc.URL"));
			ds.setUsername(PROPS.getProperty("jdbc.USER"));
			ds.setPassword(PROPS.getProperty("jdbc.PASSWORD"));
			
			ds.setInitialSize(0);
			ds.setMaxTotal(8);
			
			dataSource = ds;
		} 
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("스태틱 블럭입니다.");
		System.out.println("이 문장은 언제 수행 되나요?");
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
//		return DriverManager.getConnection(
//				PROPS.getProperty("jdbc.URL")
//				, PROPS.getProperty("jdbc.USER")
//				, PROPS.getProperty("jdbc.PASSWORD"));
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Task throwTask(Task task) {
		return task;
	}
}
