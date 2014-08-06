package zgl.test.nav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GaussCalc {
	private Connection conn;
	private Statement stmt;
	
	private static final List<String> positionList = new ArrayList<>();
	private static final List<String> bssidList = new ArrayList<>();
	private static final Map<String, String> bssid2SSID = new HashMap<String, String>();
 	
	public GaussCalc(String sqliteDBPath){
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + sqliteDBPath);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getData(){
		String sql = "select distinct(position) from record";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				positionList.add(rs.getString("position"));
			}
			
			sql = "select distinct(bssid),ssid from record";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String ssid = rs.getString("ssid");
				String bssid = rs.getString("bssid");
				
				bssidList.add(bssid);
				bssid2SSID.put(bssid, ssid);
			}
			rs.close();
			Collections.sort(positionList, new Comparator<String>() {
				public int compare(String o1, String o2) {
					if(o1.length() < o2.length()){
						return -1;
					}else if(o1.length() > o2.length()){
						return 1;
					}else{
						return o1.compareTo(o2);
					}
				};
			});
			System.out.println(positionList);
			//System.out.println(bssidList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void calc(){
		String createTB = "create table if not exists avg(_id integer primary key autoincrement not null, "
				+ "position text, bssid text, ssid text, u double, delta double, min double, max double, avg double)";
		try {
			stmt.executeUpdate(createTB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int count = 0;
		int sqlCount = 0;
		for(String p : positionList){
			for(String b : bssidList){
				count++;
				String sql = "select * from record where position='" + p + "' and bssid='" + b + "'";
				try {
					ResultSet rs = stmt.executeQuery(sql);
					List<Integer> levels = new ArrayList<>();
					while(rs.next()){
						levels.add(rs.getInt("level"));
					}
					rs.close();
					if(levels.size() > 0){
						double u = getU(levels);
						double delta = Math.sqrt(getDeltaSqr(levels, u));
						/*double max = 1 / (u * delta * Math.sqrt(2 * Math.PI));
						double min = 0.6 * max;*/
						double min = -1;
						double max = -1;
						List<Integer> filterData = new ArrayList<>();
						double t = 0;
						System.out.println("COUNT: " + count + "\tSQL: " + sql + "\t\tCOUNT");
						System.out.println("\t\tu: " + u +" delta: " + delta +" max: " + max +" min: " + min + " t: " + t);
						for(Integer i : levels){
							//double q = Math.pow(Math.E, u + (Math.pow(delta, 2) / 2));
							//System.out.println("\ti: " + i + "\tE(i): " + q + "\t i - E(i): " + (i + q));
							
							t = Math.pow(Math.E, Math.pow(Math.abs(i) - u, 2) / (2 * Math.pow(delta, 2))) / (delta * Math.sqrt(2 * Math.PI));
							System.out.println("\t\t^^^^^t " + t + "\ti: " + i + "\t\t^^^^^");
							if(t < 1){
								filterData.add(i);
							}
							
							//System.out.println("\t\t " + Math.pow(Math.E, Math.pow(Math.abs(i) - u, 2) / (2 * Math.pow(delta, 2))));
							//System.out.println("\t\t" + (delta * Math.sqrt(2 * Math.PI)));
							
							/*t = (double)1 / (Math.abs(i) * delta * Math.sqrt(2 * Math.PI) * Math.pow(Math.E, Math.pow(Math.log(Math.abs(i)) - u, 2) / (2 * Math.pow(delta, 2))));
							
							System.out.println("\tPart 1: " +  (Math.abs(i) * delta * Math.sqrt(2 * Math.PI)) + 
									"\n\tPart 2: " + Math.pow(Math.E, (Math.pow(Math.log(Math.abs(i)) - u, 2) / (2 * Math.pow(delta, 2)))));
							System.out.printf("\tt: %.6f \t min: %.3f \tmax: %.3f\ti: %d\n", t, min, max, i);
							if(t > min && t < max){
								filterData.add(i);
							}*/
						}
						//System.out.println("\tget [" + filterData.size() + "] records");
						if(filterData.size() > 0){
							/*double uFilter = getU(filterData);
							double avg = -Math.pow(Math.E, uFilter);
							//create table if not exists pbtb(_id, position, bssid, u, delta, min, max, avg);
							String sqlIst = "insert into pbtb values(null, '" + p + "','" + b + "'," + u + "," + delta + "," + min + "," + max + "," + avg + ")";
							System.out.println("u: " + u +" delta: " + delta +" max: " + max +" min: " + min + " t: " + t + " uFilter: " + uFilter +" avg: " + avg);
							System.out.println("sqlIst: " + sqlIst);
							stmt.executeUpdate(sqlIst);*/
							
							double avg = getAvg(filterData);
							String sqlIst = "insert into avg values(null, '" + p + "','" + b + "','" + bssid2SSID.get(b) + "'," + f(u) + "," + f(delta) + "," + min + "," + max + "," + f(avg) + ")";
							System.out.println("\t\tI1: " + sqlIst);
							stmt.executeUpdate(sqlIst);
							sqlCount++;
							
							
						}else{
							//System.out.println(count + "\tERROR: filterData length: " + filterData.size());
							String sqlIst = "insert into avg values(null, '" + p + "','" + b + "','" + bssid2SSID.get(b)  + "'," + f(u) + "," + f(delta) + "," + min + "," + max + "," + f(-u) + ")";
							System.out.println("\t\tI2: " + sqlIst);
							stmt.executeUpdate(sqlIst);
							sqlCount++;
						}
					}else{
						System.out.println("\t&&&" + sql + "\t空");
						//System.out.println("ERROR: " + levels.size());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("添加" + sqlCount + "条数据");
	}
	
	@SuppressWarnings("unused")
	private double getULog(List<Integer> list){
		double sum = 0;
		for(Integer i : list){
			sum += Math.log(Math.abs(i));
		}		
		return sum / list.size();
	}
	
	@SuppressWarnings("unused")
	private double getDeltaSqrLog(List<Integer> list, double u){
		double sum = 0;
		for(Integer i : list){
			sum += Math.pow(Math.log(Math.abs(i)) - u, 2);
		}
		return sum / list.size();
	}
	
	private double getU(List<Integer> list){
		double sum = 0;
		for(Integer i : list){
			sum += Math.abs(i);
		}		
		return sum / list.size();
	}
	
	private double getAvg(List<Integer> list){
		double sum = 0;
		for(Integer i : list){
			sum += i;
		}
		return sum / list.size();
	}
	
	private double getDeltaSqr(List<Integer> list, double u){
		double sum = 0;
		for(Integer i : list){
			sum += Math.pow(Math.abs(i) - u, 2);
		}
		return sum / list.size();
	}
	
	private void closeDB(){
		try {
			if(stmt != null){
				stmt.close();
			}
			
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static final String f(double d){
		return String.format("%.3f", d);
	}
	
	public static void main(String[] args) {
		final String dbPath = "D:/scan_0617.db";
		GaussCalc gc = new GaussCalc(dbPath);
		gc.getData();
		gc.calc();
		gc.closeDB();
		
	}
}
