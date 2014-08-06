package zgl.test.nav;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathMatch_2 {
	
	public static void main(String[] args) {
		//testRegex();
		long t1 = System.currentTimeMillis();
		test2();
		System.out.println(System.currentTimeMillis() - t1 + "ms");
	}
	
	private static void testRegex(){
		String str = "offline:	00:0b:85:92:a5:4c	[0.0, -82.5, -80.8, -71.9, -66.4, -68.7, -61.3, -60.0, -61.9, -73.18181818181819, -67.11111111111111, -81.6, -80.1, -79.6, -79.8, -80.1, -86.6, -90.1, -90.7, -89.3, -90.25, -85.6, -88.6, -94.0, -94.0, -90.8, -97.8, -91.6, -89.8, -95.6, -93.4, -95.2, 0.0]";
		Pattern pattern = Pattern.compile("\\[(.*)\\]");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			//System.out.println(match);
			System.out.println(matcher.group(1));
		}
	}
	
	/**
	 * 直接对元数据进行匹配
	 */
	private static void test(){
		Pattern pattern = Pattern.compile("\\[(.*)\\]");
		Matcher matcher = null;
		
		List<Double[]> onlineList = new ArrayList<>();
		List<Double[]> offlineList = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("./wifi/pathmatch.data"));
			String line = null;
			double currData;
			while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				matcher.find();
				String[] strs = matcher.group(1).split(",");
				Double[] d = new Double[strs.length];
				for(int i = 0; i < strs.length; i++){
					d[i] = Double.parseDouble(strs[i]);
				}
				if(line.charAt(1) == 'n'){
					onlineList.add(d);
				}else{
					offlineList.add(d);
				}
			}
			System.out.println(onlineList.size() + " " + offlineList.size());
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		PathMatch pm = new PathMatch();
		int[] arr = pm.matchResult(offlineList, onlineList);
		System.out.println("result: [" + arr[0] + ", " + arr[1] + "]");
	}
	
	/**
	 * 将等于信号等于0及小于某一数值的值进行处理
	 */
	private static void test2(){
		Pattern pattern = Pattern.compile("\\[(.*)\\]");
		Matcher matcher = null;
		
		List<Double[]> onlineList = new ArrayList<>();
		List<Double[]> offlineList = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("./wifi/pathmatch.data"));
			String line = null;
			double currData;
			while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				matcher.find();
				String[] strs = matcher.group(1).split(",");
				Double[] d = new Double[strs.length];
				for(int i = 0; i < strs.length; i++){
					double t = Double.parseDouble(strs[i]);
					d[i] = parseDouble(t);
				}
				if(line.charAt(1) == 'n'){
					//d = PathMatch.reverseDoubleArr(d);
					onlineList.add(d);
				}else{
					offlineList.add(d);
				}
			}
			
			System.out.println("Online Size: " + onlineList.size() + " Offline Size: " + offlineList.size());
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		PathMatch pm = new PathMatch();
		int[] arr = pm.matchResult(offlineList, onlineList);
		System.out.println("result: [" + arr[0] + ", " + arr[1] + "]");
	}
	
	private static double parseDouble(double d){
		//return (d == 0) ? -100 : (d < -80) ? ((int)d / 10 * 10 + (-5)) : d;
		return d;
	}

}
