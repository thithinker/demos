package zgl.test.nav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DealSqliteData {
	public enum SSID {
		B1_202east, B1_203Left, B1_207, B1_207_1, B1_207_2, B1_207_3, B1_207_4, B1_208, BSP, NSTL_ROUTER
	};
	public static final int MATCH = 5;
	
	public static void main(String[] args) {
		//dealData();
		locate();
	}
	
	public static void dealData() {
		Connection conn = null;
		Statement stmt = null;
		int update = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:D:/scan_0524.db");
			stmt = conn.createStatement();
			
			String sqlCreateTB = "create table if not exists avg(_id integer primary key autoincrement not null,"
					+ "position text, bssid text, ssid text, avg double)";
			stmt.executeUpdate(sqlCreateTB);
			
			String sqlPosition = "select distinct(position) from record";
			ResultSet rs = stmt.executeQuery(sqlPosition);

			List<String> positions = new ArrayList<String>();
			while (rs.next()) {
				positions.add(rs.getString("position"));
			}

			String sqlBSSID = "select distinct(bssid), ssid from record";
			rs = stmt.executeQuery(sqlBSSID);
			//List<String> bssids = new ArrayList<String>();
			Map<String, String> bssidMap = new LinkedHashMap<String, String>();
			while (rs.next()) {
				bssidMap.put(rs.getString("bssid"), rs.getString("ssid"));
				//bssids.add(rs.getString("bssid"));
			}

			String sqlQuery;
			String sqlInsert;
			int n = 0;
			double sum = 0;
			double avg = 0;
			for (String p : positions) {
				for (String s : bssidMap.keySet()) {
					sqlQuery = "select level from record where position='" + p + "' and bssid='" + s + "'";
					rs = stmt.executeQuery(sqlQuery);
					n = 0;
					sum = 0;
					while (rs.next()) {
						n++;
						sum += rs.getDouble("level");
								//Integer.parseInt(rs.getString("level"));
					}
					if (n != 0) {
						avg = sum / n;
						//sqlUpdate = "update wifi set avg=" + avg + " where position='" + p + "' and ssid='" + s + "'";
						sqlInsert = "insert into avg values(null, '" + p + "', '" + s + "', '" + bssidMap.get(s) + "', " + avg + ")";
						System.out.println(sqlInsert);
						int ss = stmt.executeUpdate(sqlInsert);
						update++;
						if (ss == 0) {
							System.out.println("UPDATE ERROR");
						}
					}

				}
			}
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("UPDATE" + update);
			e.printStackTrace();
		}
	}
	
	public static void locate(){
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:D:/wifi_data.db");
			stmt = con.createStatement();

			String sqlTotalPosition = "select * from position";
			Map<String, PPoint> positions = new HashMap<String, DealSqliteData.PPoint>();
			ResultSet rs = stmt.executeQuery(sqlTotalPosition);
			while (rs.next()) {
				positions.put(rs.getString("position"), new PPoint(rs.getDouble("point_x"), rs.getDouble("point_y")));
			}
			List<String> pList = new ArrayList<String>(positions.keySet());
			String sqlAvg = "select * from avg";
			List<SsidAvg> avgInfos = new ArrayList<DealSqliteData.SsidAvg>();
			rs = stmt.executeQuery(sqlAvg);
			while (rs.next()) {
				SsidAvg ssidAvg = new SsidAvg(rs.getString("position"), rs.getString("ssid"), rs.getDouble("avg"));
				avgInfos.add(ssidAvg);
			}

			double[][] totalAvgs = new double[pList.size()][SSID.values().length];
			for (SsidAvg ssidAvg : avgInfos) {
				totalAvgs[pList.indexOf(ssidAvg.getPosition())][SSID.valueOf(ssidAvg.getSsid().replace('-', '_')).ordinal()] = ssidAvg.getAvg();
			}
			
			//模拟测试数据
			Map<String, Double> curSsidLevel = new HashMap<String, Double>();
			curSsidLevel.put(SSID.B1_202east.toString(), -53.0);
			curSsidLevel.put(SSID.B1_203Left.toString(), -85.0);
			curSsidLevel.put(SSID.B1_207.toString(), -91.0);
			curSsidLevel.put(SSID.BSP.toString(), -54.0);
			curSsidLevel.put(SSID.B1_208.toString(), -80.0);
			
			Map<String, Double> distance = new HashMap<String, Double>();
			Set<String> curSSIDs = curSsidLevel.keySet();
			for(int i = 0; i < pList.size(); i++){
				double value = 0;
				for(String s : curSSIDs){		
					value += Math.pow(curSsidLevel.get(s) - totalAvgs[i][SSID.valueOf(s).ordinal()], 2);
				}
				distance.put(pList.get(i), Math.sqrt(value));
			}
			List<Map.Entry<String, Double>> distanceList = new ArrayList<Map.Entry<String,Double>>(distance.entrySet());
			Collections.sort(distanceList, new Comparator<Map.Entry<String, Double>>() {
				@Override
				public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
					//return o1.getValue() - o2.getValue() > 0 ? 1 : -1;
					if(o1.getKey().length() > o2.getKey().length())
						return 1;
					else if(o1.getKey().length() == o2.getKey().length()){
						return o1.getKey().compareTo(o2.getKey());
					}else if(o1.getKey().length() < o2.getKey().length())
						return -1;
					return 0;
				}
			});
			
			int matchChainLength = 5;
			double MIN = Double.MAX_VALUE;
			double cur = 0;
			List<Entry<String, Double>> list;
			List<Entry<String, Double>> minList = new ArrayList<Map.Entry<String,Double>>();
			for(int i = 0; i < distanceList.size() - matchChainLength; i++){
				list = new ArrayList<Map.Entry<String,Double>>();
				for(int j = 0; j < matchChainLength; j++){
					Entry<String, Double> entry = distanceList.get(i + j);
					cur += entry.getValue();
					list.add(entry);
				}
				if(cur < MIN){
					MIN = cur;
					minList = list;
				}
				cur = 0;
			}
			
			for(Entry<String, Double> en : minList){
				System.out.println(en.getKey() + " " + en.getValue());
			}
			
			PPoint toLocate = new PPoint();
			PPoint tmpPoint;
			for(int i = 0; i < MATCH; i++){
				tmpPoint = positions.get(distanceList.get(i).getKey());
				toLocate.setX(toLocate.getX() + tmpPoint.getX());
				toLocate.setY(toLocate.getY() + tmpPoint.getY());
			}
			toLocate.setX(toLocate.getX() / MATCH);
			toLocate.setY(toLocate.getY() / MATCH);
			System.out.println(toLocate);
			
			rs.close();
			stmt.close();
			con.close();

			/*// 显示以验证
			System.out.print("\t");
			for (int i = 0; i < SSID.values().length; i++) {
				System.out.print(SSID.values()[i] + "\t");
			}
			System.out.println();
			for (int i = 0; i < totalAvgs.length; i++) {
				System.out.print(pList.get(i) + "\t");
				for (int j = 0; j < totalAvgs[i].length; j++) {
					System.out.print(totalAvgs[i][j] + "\t");
				}
				System.out.println();
			}*/
			
			//验证矢量距离
			for(Entry<String, Double> e : distanceList){
				System.out.println(e.getKey() + "[" + String.format("%4.2f", e.getValue()) + "]");
			}
			
			/*for(String s : keys){
				System.out.println(s + "[" + distance.get(s) + "]");
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class SsidAvg {
		private String position;
		private String ssid;
		private double avg;

		public SsidAvg(String position, String ssid, double avg) {
			this.ssid = ssid;
			this.avg = avg;
			this.position = position;
		}

		public String getSsid() {
			return ssid;
		}

		public double getAvg() {
			return avg;
		}

		public String getPosition() {
			return position;
		}

		@Override
		public String toString() {
			return "Position: " + this.position + ", SSID: " + this.ssid + ", Avg: " + this.avg;
		}
	}

	public static class PPoint {
		private double x, y;
		
		public PPoint(){
			this.x = 0;
			this.y = 0;
		}

		public PPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public void setX(double x) {
			this.x = x;
		}

		public void setY(double y) {
			this.y = y;
		}
		@Override
		public String toString() {
			return "[" + this.x + ", " + this.y + "]";
		}
	}

	private static void avgToTable() {
		Connection conn = null;
		Statement stmt = null;
		int update = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:D:/wifi_data.db");
			System.out.println("Find database");
			stmt = conn.createStatement();
			String sqlPosition = "select distinct(position) from wifi";
			ResultSet rs = stmt.executeQuery(sqlPosition);

			List<String> positions = new ArrayList<String>();
			while (rs.next()) {
				positions.add(rs.getString("position"));
			}

			String sqlSSID = "select distinct(ssid) from wifi";
			rs = stmt.executeQuery(sqlSSID);
			List<String> ssids = new ArrayList<String>();
			while (rs.next()) {
				ssids.add(rs.getString("ssid"));
			}

			String sqlQuery;
			String sqlInsert;
			int n = 0;
			int sum = 0;
			double avg = 0;
			for (String p : positions) {
				for (String s : ssids) {
					sqlQuery = "select position,ssid,avg from wifi where position='" + p + "' and ssid='" + s + "'";
					rs = stmt.executeQuery(sqlQuery);
					if (rs.next()) {
						sqlInsert = "insert into avg values('" + rs.getString("position") + "', '" + rs.getString("ssid") + "', "
								+ rs.getDouble("avg") + ")";
						stmt.executeUpdate(sqlInsert);
					}

				}
			}
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("UPDATE" + update);
			e.printStackTrace();
		} finally {
		}
	}

	private void avg() {
		Connection conn = null;
		Statement stmt = null;
		int update = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:D:/wifi_data.db");
			System.out.println("Find database");
			stmt = conn.createStatement();
			String sqlPosition = "select distinct(position) from wifi";
			ResultSet rs = stmt.executeQuery(sqlPosition);

			List<String> positions = new ArrayList<String>();
			while (rs.next()) {
				positions.add(rs.getString("position"));
			}

			String sqlSSID = "select distinct(ssid) from wifi";
			rs = stmt.executeQuery(sqlSSID);
			List<String> ssids = new ArrayList<String>();
			while (rs.next()) {
				ssids.add(rs.getString("ssid"));
			}

			String sqlQuery;
			String sqlUpdate;
			int n = 0;
			int sum = 0;
			double avg = 0;
			for (String p : positions) {
				for (String s : ssids) {
					sqlQuery = "select level from wifi where position='" + p + "' and ssid='" + s + "'";
					rs = stmt.executeQuery(sqlQuery);
					n = 0;
					sum = 0;
					while (rs.next()) {
						n++;
						sum += Integer.parseInt(rs.getString("level"));
					}
					if (n != 0) {
						avg = ((double) sum) / n;
						sqlUpdate = "update wifi set avg=" + avg + " where position='" + p + "' and ssid='" + s + "'";
						int ss = stmt.executeUpdate(sqlUpdate);
						update++;
						if (ss == 0) {
							System.out.println("UPDATE ERROR");
						}
					}

				}
			}
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("UPDATE" + update);
			e.printStackTrace();
		}
	}
}
