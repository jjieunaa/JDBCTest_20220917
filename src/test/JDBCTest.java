package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
	public static void main(String[] args) {
		
		/* JDBC 프로그래밍 기본 절차 */
		// 0. 벤더 별/ 버전 별 라이브러리를 클래스 패스에 추가
		// 1. JDBC 드라이버 로딩 (벤더 별로 다름)
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
		// 3. Statement 획득 (Statement, PreparedStatement, CallableStatement)
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 4. 쿼리 수행 (결과가 반환되는 경우는 ResultSet 필요)
		// select: executeQuery 메소드 사용
		// insert, update, delete: executeUptdate 메소드 사용
		// 4-1) select 쿼리인 경우 (결과 반환이 있는 경우)
		ResultSet resultSet = null;
		try {
			String selectQuery = " select * from Region ";
			resultSet = statement.executeQuery(selectQuery);	// ResultSet: 결과집합행들에 대한 커서
			// 결과집합행이 있는 동안 반복
			while (resultSet.next()) {
				// 컬럼의 값을 저장: 컬럼의 데이터타입과 자바의 데이터타입과 호환성을 고려해서 데이터타입을 설정
				int regionID = resultSet.getInt("RegionID");
				String regionDescription = resultSet.getString("RegionDescription");
				System.out.println(regionID + " : " + regionDescription);
		}
			
		// 4-2) insert, update, delete, DDL 쿼리인 경우 (결과 반환이 있는 경우)
		String insertQuery = " insert into Region values(100, 'hello') ";
		// insert, update, delete, DDL의 영향을 받은 행의 수를 리턴
		int updateResult = statement.executeUpdate(insertQuery);
		System.out.println("업데이트결과: " + updateResult);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// 5. 관련된 객체 메모리 해제 ResultSet > Statement > Connection 순서로 close
		// 사용한 JDBC 관련 객체들은 반드시 close 해줘야 한다!!
		try {
			try {
				if (resultSet!=null) resultSet.close();
				if (statement!=null) statement.close();
				if (conn!=null) conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		
	} // main
} // class
