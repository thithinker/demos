package zgl.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DatabaseOperateTest {
	public static void main(String[] args) {
		Connection conn = new DatabaseOperateTest().getDBCPConnection2();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from employees limit 5";
			try {
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 由BasicDataSourceFactory获取Connection
	 * 2014年8月8日
	 * @return Connection
	 */
	public Connection getDBCPConnection() {
		FileInputStream in = null;
		try {
			in = new FileInputStream("config.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties p = new Properties();
		Connection conn = null;

		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			DataSource dataSource = BasicDataSourceFactory.createDataSource(p);
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 由BasicDataSource获取Connection
	 * 2014年8月8日
	 * @return Connection
	 */
	public Connection getDBCPConnection2() {
		Connection conn = null;
		try {
			DataSource dataSource = setupDataSource();
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 获取DataSource
	 * 2014年8月8日
	 * @return DataSource
	 */
	public static DataSource setupDataSource() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(p.getProperty("driverClassName"));
		ds.setUsername(p.getProperty("username"));
		ds.setPassword(p.getProperty("password"));
		ds.setUrl(p.getProperty("url"));
		return ds;
	}
}
