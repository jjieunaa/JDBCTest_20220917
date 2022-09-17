package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	public static Connection getConnection() {
		try {
			// JDBC 드라이버 클래스를 메모리에 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			// 클래스가 없으면 익셉션
			cnfe.printStackTrace();
		}
		// 2. Connection 획득
		// JDBC URL: 데이터베이스 연결할 경로
		String JDBC_URL = "jdbc:mysql://localhost:3306/northwind?characterEncoding=UTF-8&serverTimezone=UTC";
		String JDBC_USER = "jieun";
		String JDBC_PASS = "1234";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
