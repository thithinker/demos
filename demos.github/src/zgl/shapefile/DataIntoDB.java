package zgl.shapefile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataIntoDB {
	public static void main(String[] args) {
		//readDataToDB();
		
		//System.out.println(String.valueOf('\x09'));
		//System.out.println(String.valueOf('\00'));
		System.out.println(Integer.parseInt("09", 16));
		String s = "\u0048\u0045\u004C\u004C\u004F\u0020\u0057\u0047\u0052\u004C\u0044";

		System.out.println(s);
		//readDataFromDB();
	}
	
	private static void readDataToDB(){
		String src = "./data.txt";
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:buildPoint.db");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("create table if not exists build(_id integer primary key, type integer, "
					+ "typename text, ename text, cname text, pointX double, pointY double)");
			String sql = "insert into build values(null, ?, ?, ?,?,?,?)";
			PreparedStatement prep = conn.prepareStatement(sql);
			
			BufferedReader br = new BufferedReader(new FileReader(new File(src)));
			String line = null;
			String[] fields = null;
			while((line = br.readLine()) != null){
				fields = line.split(",");
				prep.setString(1, fields[3]);
				prep.setString(2, fields[4]);
				prep.setString(3, fields[5]);
				prep.setString(4, fields[6]);
				prep.setString(5, fields[7]);
				prep.setString(6, fields[8]);
				prep.addBatch();
			}
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
			br.close();
			stmt.close();
			conn.close();
			System.out.println("finish");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}
	
	private static void readDataFromDB(){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:buildPoint.db");
			Statement stmt = connection.createStatement();
			String sql = "select * from build where cname like '%主楼%'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getString(5) + "|" + rs.getString(6) + "|" + rs.getString(7));
				
			}
			stmt.close();
			connection.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
