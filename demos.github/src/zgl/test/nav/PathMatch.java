package zgl.test.nav;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zgl.util.Utils;

public class PathMatch {
	public static final float FACTOR = 0.2f;
	//相关系数	0.93	0.8
	public static final float COEFFICIENT = 0.8f;
	public static final float COINCIDE_FACTOR = 0.6f;

	public static void main(String[] args) {
		/*PathMatch dm = new PathMatch();
		double[] input_202 = dm.getInputArray("./wifi/b1-202");
		double[] input_207 = dm.getInputArray("./wifi/b1-207");
		double[] input_208 = dm.getInputArray("./wifi/b1-208");
		
		double [] data1 = dm.getInputArray("./wifi/data1.txt");
		//List<Integer> pairs1 = dm.match(input_202, data1, 5);
		//System.out.println("****************************************************");
		double [] data2 = dm.getInputArray("./wifi/data2.txt");
		//List<Integer> pairs2 = dm.match(input_207, data2, 5);
		//System.out.println("****************************************************");
		double [] data3 = dm.getInputArray("./wifi/data3.txt");
		//List<Integer> pairs3 = dm.match(input_208, data3, 5);
		//System.out.println("****************************************************");
		
		int[][] arr1 = dm.listToArray(pairs1);
		int[][] arr2 = dm.listToArray(pairs2);
		int[][] arr3 = dm.listToArray(pairs3);
		
		//int[] result = dm.intersection(COINCIDE_FACTOR,arr1, arr2, arr3);
		//System.out.println("result: [" + result[0] + ", "+ result[1] + "]");
		List<Double[]> baseList = new ArrayList<>();
		List<Double[]> dataList = new ArrayList<>();
		baseList.add(dm.toDouble(input_202));
		baseList.add(dm.toDouble(input_207));
		baseList.add(dm.toDouble(input_208));
		
		dataList.add(dm.toDouble(data1));
		dataList.add(dm.toDouble(data2));
		dataList.add(dm.toDouble(data3));
		
		int[] result = dm.matchResult(baseList, dataList);
		System.out.println("result: [" + result[0] + ", "+ result[1] + "]");
		
		
		double[] d_202_5_10 = { -81.48926, -80.41409, -83.28775, -78.83826, -79.63105, -75.79207 };
		double[] d_202_12_17 = { -76.91135, -73.44124, -73.01768, -72.87473, -69.1511, -65.4831 };
		double[] d_202_17_24 = {-65.4831,-61.17071,-58.21389,-56.54264,-50.80845,-51.39465,-57.35342,-60.38938};
		//dm.match(input_202, d_202_17_24, 5);
		double[] o_202_12_17 = {-72.39186,-70.04067,-69.14217,-69.30319,-65.66306,-62.90903};
		System.out.println(dm.correlationCoefficient(o_202_12_17, d_202_12_17));
		double[] o_202_5_10 = {-78.96016,-78.80214,-75.50448,-75.27746,-72.5426,-75.40928};
		System.out.println("od202_5-10: " + dm.correlationCoefficient(o_202_5_10, d_202_5_10));
		double[] o_202_5_17 = {-78.96016,-78.80214,-75.50448,-75.27746,-72.5426,-75.40928,-74.10011,-72.39186,-70.04067,-69.14217,
				-69.30319,-65.66306,-62.90903};
		double[] d_202_5_17 = {-81.48926,-80.41409,-83.28775,-78.83826,-79.63105,-75.79207,-74.22064,-76.91135,-73.44124,-73.01768,
				-72.87473,-69.1511,-65.4831};
		System.out.println("od202_5-17: " + dm.correlationCoefficient(o_202_5_17, d_202_5_17));
		
		double[] d_207_5_10 = {-80.808,-80.051,-79.128,-75.56,-73.373,-71.18};
		double[] o_207_5_10 = {-75.16837,-72.6365,-71.28358,-70.31447,-66.87311,-64.29726};
		double[] d_207_12_17 = {-66.793,-70.486,-68.242,-64.848,-63.927,-64.185};
		double[] d_207_17_24 = {-64.185,-70.132,-72.727,-74.875,-78.351,-78.843,-77.523,-79.043};
		
		double[] d_208_5_10 = {-86.74,-82.807,-86.044,-80.688,-79.872,-79.75};
		double[] o_208_5_10 = {-80.37259,-81.42623,-80.08422,-79.05002,-77.00251,-73.73888};
		double[] d_208_12_17 = {-78.642,-76.643,-74.644,-70.754,-66.185,-65.292};
		double[] d_208_17_24 = {-65.292,-64.26,-61.893,-60.681,-58.475,-61.575,-65.366,-72.671};
		
		
		//相关系数测试
		double[] d1 = {-72.39186,-70.04067,-69.14217,-69.30319,-65.66306,-62.90903,-62.41402,-57.32496,-51.10373,-49.13105};
		double[] d2 = {-76.91135,-73.44124,-73.01768,-72.87473,-69.1511,-65.4831,-61.17071,-58.21389,-56.54264,-50.80845};
		System.out.println(dm.correlationCoefficient(d1, d2));
		
		System.out.println("od208_5-10: " + dm.correlationCoefficient(o_208_5_10, d_208_5_10));
		System.out.println("od207_5-10: " + dm.correlationCoefficient(o_207_5_10, d_207_5_10));
		
		double[] data11 = {3,3,3,3};
		double[] data22 = {4,4,4,4};
		System.out.println("相关系数: " + dm.correlationCoefficient(data11, data22));*/
		
		//testOnlineData();
		testOnOfflineData();
		PathMatch dm = new PathMatch();
		//相关系数测试
				double[] d1 = {-72.39186,-70.04067,-69.14217,-69.30319,-65.66306,-62.90903,-62.41402,-57.32496,-51.10373,-49.13105};
				double[] d2 = {-76.91135,-73.44124,-73.01768,-72.87473,-69.1511,-65.4831,-61.17071,-58.21389,-56.54264,-50.80845};
				double[] d3 = new double[d2.length];
				for(int i = 0; i < d2.length; i++){
					d3[d2.length - i - 1] = d2[i];
				}
				System.out.println(dm.correlationCoefficient(d1, d3));
				
				System.out.println(dm.correlationCoefficient(d1, d2));
	}

	/**
	 * @date 2014.7.11
	 * 使用在线获取的数据进行测试
	 */
	private static void testOnlineData(){
		PathMatch dm = new PathMatch();
		double[] input_202 = dm.getInputArray("./wifi/b1-202");
		
		double [] data1 = dm.getInputArray("./wifi/data_0711.txt");

		List<Double[]> baseList = new ArrayList<>();
		List<Double[]> dataList = new ArrayList<>();
		baseList.add(dm.toDouble(input_202));
		
		dataList.add(dm.toDouble(data1));

		int[] result = dm.matchResult(baseList, dataList);
		System.out.println("result: [" + result[0] + ", "+ result[1] + "]");
	}
	
	private static void testOnOfflineData(){
		Double[] off = {-85.0, -85.0, -79.958, -85.0, -77.591, -78.609, -71.474, -75.0, -69.2, -78.913, -72.5, -70.304, -67.167, -68.044, -69.5, -61.214, -59.543, -61.809, -51.105, -43.5, -46.72, -49.958, -63.44, -56.136, -70.146, -70.64, -68.723, -75.04, -77.261, -85.0, -85.0};
		Double[] on = {-59.0, -59.0, -55.0, -55.0, -51.0, -51.0, -48.0, -48.0};
		//on = reverseDoubleArr(on);
		
		List<Double[]> offlineList = new ArrayList<>();
		List<Double[]> onlineList = new ArrayList<>();
		
		offlineList.add(off);
		onlineList.add(on);
		PathMatch pm = new PathMatch();
		int[] result = pm.matchResult(offlineList, onlineList);
		System.out.println("result: [" + result[0] + ", "+ result[1] + "]");
	}
	
	/**
	 * 数组顺序影响相关系数及结果
	 */
	private static void tmpOnOfflineData(){
		Double[] off = {-85.0, -85.0, -79.958, -85.0, -77.591, -78.609, -71.474, -75.0, -69.2, -78.913, -72.5, -70.304, -67.167, -68.044, -69.5, -61.214, -59.543, -61.809, -51.105, -43.5, -46.72, -49.958, -63.44, -56.136, -70.146, -70.64, -68.723, -75.04, -77.261, -85.0, -85.0};
		Double[] on = {-59.0, -59.0, -55.0, -55.0, -51.0, -51.0, -48.0, -48.0};
		//on = reverseDoubleArr(on);
		
		List<Double[]> offlineList = new ArrayList<>();
		List<Double[]> onlineList = new ArrayList<>();
		
		offlineList.add(off);
		onlineList.add(on);
		PathMatch pm = new PathMatch();
		int[] result = pm.matchResult(offlineList, onlineList);
		System.out.println("result: [" + result[0] + ", "+ result[1] + "]");
	}
	
	public static Double[] reverseDoubleArr(Double[] arr){
		int len = arr.length;
		for(int i = 0; i < len / 2; i++){
			double d = arr[i];
			arr[i] = arr[len - i - 1];
			arr[len - i - 1] = d;
		}
		return arr;
	}
	
	/**
	 * 读入文件中的数据(每行一个数)，转换成一维数组
	 * @param path 文件路径
	 * @return 包含文件中数据的一维数组
	 */
	private double[] getInputArray(String path) {
		List<Double> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			double currData;
			while ((line = br.readLine()) != null) {
				currData = Double.parseDouble(line);
				list.add(currData);
			}
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		double[] input = new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			input[i] = list.get(i);
		}
		return input;
	}

	/*public int[] matchResult(double[] base, double[] data){
		double[] input_202 = getInputArray("./wifi/b1-202");
		
		double [] data1 = getInputArray("./wifi/data1.txt");
		List<Integer> pairs1 = match(input_202, data1, 5);
		System.out.println("****************************************************");	
		int[][] arr1 = listToArray(pairs1);
		
		//int[][] result = intersection(COINCIDE_FACTOR,arr1, arr2, arr3);
		///System.out.println("result: [" + result[0][0] + ", "+ result[0][1] + "]");
		
		int[] result = intersection(COINCIDE_FACTOR, listToArray(match(base, data, 5)));
		return result;
	}*/
	
	/**
	 * 获取匹配结果
	 * @param baseList 离线数据列表
	 * @param dataList 在线数据列表
	 * @return 匹配到离线数据的索引
	 */
	public int[] matchResult(List<Double[]> baseList, List<Double[]> dataList){
		List<Integer[][]> list = new ArrayList<>();
		if(baseList.size() != dataList.size()){
			return null;
		}
		for(int i = 0; i < baseList.size(); i++){
			System.out.println("****************************************************");
			System.out.println("\tOffline" + Arrays.deepToString(baseList.get(i)) + "\n\tOnline: " + Arrays.deepToString(dataList.get(i)));
			Integer[][] arr = listToArray(match(fromDouble(baseList.get(i)), fromDouble(dataList.get(i)), 5));
			if(arr.length > 0){
				list.add(arr);
			}
		}
		if(list.size() == 0){
			return new int[]{-1, -1};
		}
		return intersection(COINCIDE_FACTOR, list);
		
	}
	private double[] fromDouble(Double[] data){
		double[] d = new double[data.length];
		for(int i = 0; i < data.length; i++){
			d[i] = data[i];
		}
		return d;
	}
	
	private Double[] toDouble(double[] data){
		Double[] d = new Double[data.length];
		for(int i = 0; i < data.length; i++){
			d[i] = data[i];
		}
		return d;
	}
	/**
	 * 找出数组origin中与新数组d匹配程度较高的部分
	 * @param origin	原数组
	 * @param d 	新数组
	 * @param LEAST 	原数组中最小匹配的元素数
	 * @return		包含origin中与d匹配程度较高的部分的起点和终点的位置的列表
	 */
	public List<Integer> match(final double[] origin, final double[] d, final int LEAST) {
		List<Integer> numPair = new ArrayList<>();
		Map<String, Double> dInfo = arrInfo(d);
		/*System.out.println("新数据：\t[" + dInfo.get("MAX") + "," + dInfo.get("MIN") + "," + dInfo.get("AVG") + "," + dInfo.get("START") + ","
				+ dInfo.get("END") + "]");*/
		for (int i = 0; i < origin.length - LEAST; i++) {
			for (int j = i + LEAST; j < origin.length; j++) {
				/*if(j - i + 1 > d.length){			//基值是根据距离采集的值，间隔时间比新采集数据的间隔时间长，故匹配结果基值数据个数不会比新数据个个数多
					continue;
				}*/
				Map<String, Double> tInfo = arrInfo(origin, i, j + 1);
				
				if (isMatch(tInfo, dInfo)) {
					/*double[] arr = new double[d.length];
					System.arraycopy(origin, i, arr, 0, d.length);
					double coco = correlationCoefficient(arr, d);
					
					list.add(tInfo);
					System.out.println("相关系数：[" + coco + "] origin[pos:" + i + ", length:" + d.length);
					System.out.println("\t[" + tInfo.get("MAX") + "," + tInfo.get("MIN") + "," + tInfo.get("AVG") + "," + tInfo.get("START") + ","
							+ tInfo.get("END") + "]");*/
					
					
					/*list.add(tInfo);
					System.out.println("origin[" + i + ", " + (j + 1) + ")  长度：" + (j - i + 1));
					System.out.println("\t[" + tInfo.get("MAX") + "," + tInfo.get("MIN") + "," + tInfo.get("AVG") + "," + tInfo.get("START") + ","
							+ tInfo.get("END") + "]");*/
					
					/*System.out.println("\t 基值[" + tInfo.get("MAX") + "," + tInfo.get("MIN") + "," + tInfo.get("AVG") + "," + tInfo.get("START")
					 		* + "," + tInfo.get("END") + "]");*/
					
					int len = j - i + 1;
					double[] tmp = new double[len];
					System.arraycopy(origin, i, tmp, 0, len);
					//Utils.printArr(tmp);
					double coefficient;
					Map<String, Double> ddInfo = null;
					if(len != d.length){
						double[] dd = new double[len];
						for(int k = 0 ; k < len; k++){
							dd[k] = d[k * d.length / len];
						}
						coefficient = correlationCoefficient(tmp, dd);
						ddInfo = arrInfo(dd);
					}else{
						coefficient = correlationCoefficient(tmp, d);
					}
					
					if(coefficient > COEFFICIENT){
						Map<String, Double> ttInfo = arrInfo(tmp);
						/*System.out.printf("\t 基值[%.3f, %.3f, %.3f, %.3f, %.3f]\n", ttInfo.get("MAX"),ttInfo.get("MIN"),ttInfo.get("AVG"),
								ttInfo.get("START"),ttInfo.get("END"));
						System.out.printf("\t 新值[%.3f, %.3f, %.3f, %.3f, %.3f]\n", ddInfo.get("MAX"),ddInfo.get("MIN"),ddInfo.get("AVG"),
								ddInfo.get("START"),ddInfo.get("END"));*/
						
						/*System.out.println("\t 基值[" + ttInfo.get("MAX") + "," + ttInfo.get("MIN") + "," + ttInfo.get("AVG") + "," + 
								ttInfo.get("START") + "," + ttInfo.get("END") + "]");
						System.out.println("\t 新值[" + ddInfo.get("MAX") + "," + ddInfo.get("MIN") + "," + ddInfo.get("AVG") + "," + 
								ddInfo.get("START") + "," + ddInfo.get("END") + "]");*/
						System.out.println("\t相关系数：" + coefficient + " \torigin[" + i + ", " + (j + 1) + ") \t长度：" + len);
						
						
						
						//将匹配到的区间（闭区间）返回
						if(len < 10 && coefficient > 0.95){		//当数组长度很小时，提高对其相关系数的限制
							numPair.add(i);
							numPair.add(j);
						}else if(len > 10){
							numPair.add(i);
							numPair.add(j);
						}
						
						
					}
					
					
				}
			}
		}
		return numPair;
	}

	/**
	 * 判断包含两个数组特征信息的Map是否匹配
	 * @param tInfo 包含数组特征信息的Map
	 * @param dInfo 包含数组特征信息的Map
	 * @return	true or false
	 */
	private boolean isMatch(Map<String, Double> tInfo, Map<String, Double> dInfo) {
		if (isInclude(tInfo.get("MAX"), dInfo.get("MAX")) && isInclude(tInfo.get("MIN"), dInfo.get("MIN"))
				&& isInclude(tInfo.get("AVG"), dInfo.get("AVG")) && isInclude(tInfo.get("START"), dInfo.get("START"))
				&& isInclude(tInfo.get("END"), dInfo.get("END"))) {
			/*System.out.println("\t" + tInfo.get("MAX") / dInfo.get("MAX") + " " + tInfo.get("MIN") / dInfo.get("MIN") + " " + tInfo.get("AVG") / dInfo.get("AVG")
					+ " " + tInfo.get("START") / dInfo.get("START") + " " + tInfo.get("END") / dInfo.get("END"));*/
			return true;
		}
		return false;
	}

	/**
	 * 判断两个数是否在一定误差范围内
	 * @param base 基值
	 * @param d 新值
	 * @return true or false
	 */
	private boolean isInclude(double base, double d) {
		return isInclude(base, d, FACTOR);
	}

	/**
	 * 判断两个数是否在一定误差范围内
	 * @param base 基值
	 * @param d 新值
	 * @param factor 误差系数
	 * @return 如果(1-factor) * |base| < |d| < (1 + factor) * |base|返回true，否则返回false
	 */
	private boolean isInclude(double base, double d, float factor) {
		double t = Math.abs(d) / Math.abs(base);
		return t < (1 + factor) && t > (1 - factor);

	}

	/**
	 * 返回该数组的特征信息
	 * @param data 输入数组
	 * @return 包含该数组特征信息的Map
	 */
	private Map<String, Double> arrInfo(final double[] data) {
		return arrInfo(data, 0, data.length - 1);
	}

	/**
	 * 返回该数组指定范围内数的特征信息
	 * @param data 输入数组
	 * @param i 起点
	 * @param j 终点
	 * @return 包含该数组特征信息的Map
	 */
	private Map<String, Double> arrInfo(double[] data, int i, int j) {
		int idx = i;
		Map<String, Double> infoMap = new HashMap<>();
		double max = data[i];
		double min = data[i];
		double sum = 0;
		double avg = 0;
		double start = data[i];
		double end = data[j - 1];
		while (i < j) {
			if (data[i] < min)
				min = data[i];
			if (data[i] > max)
				max = data[i];
			sum += data[i];
			i++;
		}
		avg = sum / (j - idx);
		infoMap.put("MAX", max);
		infoMap.put("MIN", min);
		infoMap.put("AVG", avg);
		infoMap.put("START", start);
		infoMap.put("END", end);

		return infoMap;
	}
	
	/**
	 * 计算两组数的相关系数
	 * @param origin 数组
	 * @param d 数组
	 * @return 两数组的相关系数， -1～1.
	 */
	public double correlationCoefficient(double[] origin, double[] d){
		//assert(origin.length == d.length);
		double oSum = 0;
		double oSqrSum = 0;
		double dSum = 0;
		double dSqrSum = 0;
		double odSum = 0;
		int length = origin.length;
		for(int i = 0; i <length; i++){
			oSum += origin[i];
			oSqrSum += origin[i] * origin[i];
			dSum += d[i];
			dSqrSum += d[i] * d[i];
			odSum += origin[i] * d[i];
		}
		if(Math.sqrt((length * oSqrSum - oSum * oSum) * (length * dSqrSum - dSum * dSum)) == 0)
			return 0;
		return (length * odSum - oSum * dSum) / (Math.sqrt((length * oSqrSum - oSum * oSum) * (length * dSqrSum - dSum * dSum)));
	}
	
	/**
	 * 将特定列表转换成数组
	 * @param list 该列表中的数两两成对，分别为数组中一段数据的起始和终止下标
	 * @return 二维数组，行数为list.size()/2，列数为2，每行对应数组中一段数据下标
	 */
	/*public int[][] listToArray(List<Integer> list){
		int[][] arr = new int[list.size() / 2][2];
		for(int i = 0; i < list.size() / 2; i++){
			arr[i][0] = list.get(2 * i);
			arr[i][1] = list.get(2 * i + 1);
		}
		return arr;
	}*/
	
	public Integer[][] listToArray(List<Integer> list){
		Integer[][] arr = new Integer[list.size() / 2][2];
		for(int i = 0; i < list.size() / 2; i++){
			arr[i][0] = list.get(2 * i);
			arr[i][1] = list.get(2 * i + 1);
		}
		return arr;
	}
	
	/*public int[][] intersection(int[][]... arrs){
		int min = arrs[0][0][0];
		int max = arrs[0][0][1];
		for(int[][] t : arrs){
			for(int i = 0; i < t.length; i++){
				if(t[i][0] > min)
					min = t[i][0];
				if(t[i][1] < max)
					max = t[i][1];
			}
		}
		
		//System.out.println("Left: " + min + ", right: " + max);
		return new int[][]{{min, max}};
	}*/
	
	/**
	 * 返回所有数组的交集 
	 * @param cover 覆盖率，当一个数的覆盖率大于该数时将该数添加到返回结果的范围中
	 * @param arrs 输入的二维数组列表
	 * @return 返回交集范围
	 */
	public int[] intersection(float cover, int[][]... arrs){	
		int left = arrs[0][0][0];
		int right = arrs[0][0][1];
		int leftMin = left;
		int rightMax = right;
		int record = 0;
		for(int[][] t : arrs){
			for(int i = 0; i < t.length; i++){
				if(t[i][0] > left)		//左侧最大值
					left = t[i][0];
				if(t[i][1] < right)		//右侧最小值
					right = t[i][1];
				
				if(t[i][0] < leftMin)		//左侧最小值
					leftMin = t[i][0];
				if(t[i][1] > rightMax)		//右侧最大值
					rightMax = t[i][1];
				
				record++;
			}
		}
		
		int[] leftTmp = new int[left - leftMin];
		int[] rightTmp = new int[rightMax - right];
		for(int[][] t : arrs){
			for(int i = 0; i < t.length; i++){
				if(t[i][0] < left)
					leftTmp[t[i][0] - leftMin]++;
				if(t[i][1] > right)
					rightTmp[rightMax - t[i][1]]++;
			}
		}
		
		for(int i = leftTmp.length - 1; i > 0; i--){
			leftTmp[i - 1] += leftTmp[i];
		}
		
		for(int i = rightTmp.length - 1; i > 0; i--){
			rightTmp[i - 1] += rightTmp[i];
		}
		
		//System.out.println("record: " + record );
		
		int m = 0; 
		for(int i = 0; i < leftTmp.length; i++){
			if(((float)leftTmp[i] / (float)record > cover) && leftTmp[i] > leftTmp[i + 1]){
				//System.out.println((float)leftTmp[i] / (float)record);
				m++;
			}else{
				break;
			}
		}
		left -= m;
		
		m = 0;
		for(int i = 0; i < rightTmp.length; i++){
			if((float)rightTmp[i] / record > cover && rightTmp[i] > rightTmp[i + 1]){
				m++;
			}else{
				break;
			}
		}
		right += m;
		
		//Utils.printArr(leftTmp);
		//Utils.printArr(rightTmp);
		return new int[]{left, right};
	}
	
	
	public int[] intersection(float cover, List<Integer[][]> list){	
		int left = list.get(0)[0][0];
		int right = list.get(0)[0][1];
		int leftMin = left;
		int rightMax = right;
		int record = 0;
		for(Integer[][] t : list){
			for(int i = 0; i < t.length; i++){
				if(t[i][0] > left)		//左侧最大值
					left = t[i][0];
				if(t[i][1] < right)		//右侧最小值
					right = t[i][1];
				
				if(t[i][0] < leftMin)		//左侧最小值
					leftMin = t[i][0];
				if(t[i][1] > rightMax)		//右侧最大值
					rightMax = t[i][1];
				
				record++;
			}
		}
		if(left > right){
			left = right - 1;
		}
		
		int[] leftTmp = new int[left - leftMin];
		int[] rightTmp = new int[rightMax - right];
		for(Integer[][] t : list){
			for(int i = 0; i < t.length; i++){
				if(t[i][0] < left)
					leftTmp[t[i][0] - leftMin]++;
				if(t[i][1] > right)
					rightTmp[rightMax - t[i][1]]++;
			}
		}
		
		for(int i = leftTmp.length - 1; i > 0; i--){
			leftTmp[i - 1] += leftTmp[i];
		}
		
		for(int i = rightTmp.length - 1; i > 0; i--){
			rightTmp[i - 1] += rightTmp[i];
		}
		
		//System.out.println("record: " + record );
		
		int m = 0; 
		for(int i = 0; i < leftTmp.length - 1; i++){
			if(((float)leftTmp[i] / (float)record > cover) && leftTmp[i] > leftTmp[i + 1]){
				//System.out.println((float)leftTmp[i] / (float)record);
				m++;
			}else{
				break;
			}
		}
		left -= m;
		
		m = 0;
		for(int i = 0; i < rightTmp.length - 1; i++){
			if((float)rightTmp[i] / record > cover && rightTmp[i] > rightTmp[i + 1]){
				m++;
			}else{
				break;
			}
		}
		right += m;
		
		//Utils.printArr(leftTmp);
		//Utils.printArr(rightTmp);
		return new int[]{left, right};
	}
}
