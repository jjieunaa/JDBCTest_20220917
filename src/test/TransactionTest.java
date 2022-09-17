package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTest {

	static ConnectionManager cm = null;
	static Connection conn = null;
	
	public static void main(String[] args) {
		
		conn = ConnectionManager.getConnection();
		
		// 1. setAutoCommit(false)로 설정: 트랜잭션이 끝날 때까지 commit하지 않는다.
		if (conn!=null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		// 2. 트랜잭션에 포함되는 쿼리문을 수행
		String query1 = " insert into region(regionid, regiondescription) values (200, 'query1') ";
		String query2 = " insert into region(regionid, regiondescription) values (300, 'query2') ";
		String query3 = " into region(regionid, regiondescription) values (400, 'query3') ";
		
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		
		try {
		stmt1 = conn.createStatement();
		stmt2 = conn.createStatement();
		stmt3 = conn.createStatement();
		
		// 2개 이상의 쿼리를 수행
		if (stmt1.executeUpdate(query1)>0) System.out.println("stmt1 수행됨");
		if (stmt2.executeUpdate(query2)>0) System.out.println("stmt2 수행됨");
		if (stmt3.executeUpdate(query3)>0) System.out.println("stmt3 수행됨");
		
		// 3. commit(), rollback()을 수행
		
		conn.commit();
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			try {
				conn.rollback();
			} catch(SQLException sqle2) {
				sqle2.printStackTrace();
			}
			
		} finally {
			try {
				if (stmt1!=null) stmt1.close();
				if (stmt2!=null) stmt2.close();
				if (stmt3!=null) stmt3.close();
				if (conn!=null) conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	} // main
} // class
