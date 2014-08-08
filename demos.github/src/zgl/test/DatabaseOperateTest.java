package zgl.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DatabaseOperateTest {
	public static void main(String[] args) {		
		new DatabaseOperateTest().getDBCPConnection();
		
	}
	
	public Connection getDBCPConnection(){
		FileInputStream in = null;
		try {
			in = new FileInputStream("config.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties p = new Properties();
		Connection conn = null;
		Statement stmt = null;
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			DataSource dataSource = BasicDataSourceFactory.createDataSource(p);
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select * from employees limit 5";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString(1) + " " + rs.getShort(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static DataSource setupDataSource() {
	        BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("com.mysql.jdbc.Driver");
	        ds.setUsername("root");
	        ds.setPassword("123456");
	        ds.setUrl("jdbc:mysql://192.168.1.121:3306/employees");
	        return ds;
	    }
}
