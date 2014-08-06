package zgl.test.nav;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zgl.util.Utils;

public class PathMatch_3 {
	public static final float FACTOR = 0.2f;
	//相关系数	0.93	0.8
	public static final float COEFFICIENT = 0.8f;
	public static final float COINCIDE_FACTOR = 0.6f;
	public static final int MIN_MATCH_LENGTH = 7;

	public static void main(String[] args) {
		PathMatch_3 pathMatch_3 = new PathMatch_3();
		pathMatch_3.test3();
	}

	private void test2(){
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
					d[i] = t;
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
		//特征值法
		int v1 = calc(offlineList, onlineList);
		System.out.println("特征值法: " + v1);
		//knn法
		int v2 = knn(offlineList, onlineList);
		System.out.println("KNN法:   " + v2);
	}
	
	private void test3(){
		Pattern pattern = Pattern.compile("\\[(.*)\\]");
		Matcher matcher = null;
		
		List<Double[]> onlineList = new ArrayList<>();
		List<Double[]> offlineList = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("./wifi/pathmatch.data"));
			String line = null;
			double currData;
			boolean flag = false;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				if(line.length() !=  0 && (line.charAt(1) == 'n' || line.charAt(1) == 'f')){
					matcher = pattern.matcher(line);
					matcher.find();
					String[] strs = matcher.group(1).split(",");
					Double[] d = new Double[strs.length];
					for(int i = 0; i < strs.length; i++){
						double t = Double.parseDouble(strs[i]);
						d[i] = t;
					}
					if(line.charAt(1) == 'n'){
						//d = PathMatch.reverseDoubleArr(d);
						onlineList.add(d);
						flag = false;
					}else{
						offlineList.add(d);
						flag = true;
					}
					//System.out.println(line.length() + " " + flag);
				}else if(line.length() != 0 && line.charAt(0) == '-' && flag){
					//System.out.println("计算");
					//特征值法
					int v1 = calc(offlineList, onlineList);
					System.out.print("特征值: " + v1);
					//knn法
					int v2 = knn(offlineList, onlineList);
					System.out.print("\tKNN: " + v2);
					//knn2法
					int v3 = knn2(offlineList, onlineList);
					System.out.print("\t    KNN2: " + v3);
					//System.out.println();
					
					int v4 = knn3(offlineList, onlineList);
					System.out.print("\t  KNN3: " + v4);
					
					int v5 = new KNNImprove().knn(offlineList, onlineList);
					System.out.println("\t  KNN3: " + v5);
					
					onlineList.clear();
					offlineList.clear();
				}
				
				
			}
			
			//System.out.println("Online Size: " + onlineList.size() + " Offline Size: " + offlineList.size());
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		//knn2(offlineList, onlineList)
	}
	
	
	/**
	 * 结合历史数据改进的knn算法
	 * @param offlineList 离线数据
	 * @param onlineList 在线数据
	 * @return 定位位置
	 */
	private int knn3(List<Double[]> offlineList, List<Double[]> onlineList){
		int length = onlineList.get(0).length;
		int offlineArrLength = offlineList.get(0).length;
		List<Double> distances = new ArrayList<>();
		Map<Integer, Double> distanceMap = new HashMap<>();
		int[] rs = new int[length - 7];
		for(int i = 7; i < length; i++){
			rs[i - 7] = knn(offlineList, getDoubleArr(onlineList, i));
		}
		int begin = rs[0];
		for(int m = begin ; m < offlineArrLength; m++){
			//distances.add(getDistance(begin, m, offlineList, onlineList));
			distanceMap.put(m, getDistance(begin, m, offlineList, onlineList));
		}
		
		for(int m = begin ; m >= 0; m--){
			//distances.add(getDistance(begin, m, offlineList, onlineList));
			distanceMap.put(m, getDistance(begin, m, offlineList, onlineList));
		}
		
		List<Map.Entry<Integer, Double>> list = new ArrayList<>(distanceMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		//System.out.println("*********************************************");
		int result = 0;
		int k = 3;
		for(int i = 0; i < k; i++){
			//System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
			result += list.get(i).getKey();
		}
		//System.out.println("*********************************************");
		
		//System.out.println("length: " + rs.length);
		//Utils.printArr(rs);
		
		
		return result / k;
	}
	
	private enum calcType{MAX, MIN, AVG};
	/**
	 * 获取offlineList中数组从begin开始到currCalc这一段数据的距离
	 * @param begin offlineList中数组的起始点
	 * @param currCalc offlineLIst中数组的终止点
	 * @param offlineList 离线数据
	 * @param onlineList 在线数据
	 * @return 距离
	 */
	private Double getDistance(int begin, int currCalc, List<Double[]> offlineList, List<Double[]> onlineList) {
		double[] currOfflineVector = getDoubleArr(offlineList, currCalc);
		double[] currOnlineVerctor = getDoubleArr(onlineList, onlineList.get(0).length - 1);
		double d1 = vectorDistance(currOnlineVerctor, currOfflineVector);
		
		List<Double[]> usedOfflineList = new ArrayList<>();
		//int len = currCalc - begin + 1;
		int len = Math.abs(currCalc - begin) + 1;
		for(int m = 0; m < offlineList.size(); m++){
			Double[] d = new Double[len];
			//System.arraycopy(offlineList.get(m), begin, d, 0, len);
			if(currCalc < begin){
				System.arraycopy(offlineList.get(m), currCalc, d, 0, len);
			}else{
				System.arraycopy(offlineList.get(m), begin, d, 0, len);
			}
			usedOfflineList.add(d);
		}
		double dMax = getTypeDistance(usedOfflineList, onlineList, calcType.MAX);
		double dMin = getTypeDistance(usedOfflineList, onlineList, calcType.MIN);
		double dAvg = getTypeDistance(usedOfflineList, onlineList, calcType.AVG);
		
		//System.out.println("Distance(curr, max, min, avg): [" + d1 + ", " + dMax + ", " + dMin + ", " + dAvg + "]");
		
		
		return d1 + (dMax + dMin + dAvg) * 0.4;
	}

	/**
	 * 获取某一类型的向量距离，如每一AP信号值的最大值组成的向量，最小值组成的向量等。
	 * @param usedOfflineList 离线数据
	 * @param onlineList 在线数据
	 * @param type 数据类型
	 * @return 向量距离
	 */
	private double getTypeDistance(List<Double[]> usedOfflineList, List<Double[]> onlineList, calcType type) {
		double distance = 0;
		switch (type) {
		case MAX:
			double[] offlineMax = getMaxArr(usedOfflineList);
			double[] onlineMax = getMaxArr(onlineList);
			distance = vectorDistance(onlineMax, offlineMax);
			break;
		case MIN:
			double[] offlineMin = getMinArr(usedOfflineList);
			double[] onlineMin = getMinArr(onlineList);
			distance = vectorDistance(onlineMin, offlineMin);
			break;
		case AVG:
			double[] offlineAvg = getAvgArr(usedOfflineList);
			double[] onlineAvg = getAvgArr(onlineList);
			distance = vectorDistance(onlineAvg, offlineAvg);
			break;

		default:
			break;
		}
		return distance;
	}

	/**
	 * 获取列表中每一个数组的最大值数组
	 * @param list 输入列表
	 * @return 列表中每一个数组中最大值组成的数组
	 */
	private double[] getMaxArr(List<Double[]> list) {
		int len = list.get(0).length;
		double[] rs = new double[list.size()];
		for(int m = 0; m < list.size(); m++){
			Double[] d = list.get(m);
			double t = d[0];
			for(int n = 0; n < len; n++){
				if(t < d[n]){
					t = d[n];
				}
			}
			rs[m] = t;
 		}
		return rs;
	}
	
	/**
	 * 获取列表中每一个数组的最小值数组
	 * @param list 输入列表
	 * @return 列表中每一个数组中最小值组成的数组
	 */
	private double[] getMinArr(List<Double[]> list) {
		int len = list.get(0).length;
		double[] rs = new double[list.size()];
		for(int m = 0; m < list.size(); m++){
			Double[] d = list.get(m);
			double t = d[0];
			for(int n = 0; n < len; n++){
				if(t > d[n]){
					t = d[n];
				}
			}
			rs[m] = t;
 		}
		return rs;
	}

	/**
	 * 获取列表中每一个数组平均值组成的数组
	 * @param list 输入列表
	 * @return 列表中每一个数组的平均值组成的数组
	 */
	private double[] getAvgArr(List<Double[]> list) {
		int len = list.get(0).length;
		double[] rs = new double[list.size()];
		for(int m = 0; m < list.size(); m++){
			Double[] d = list.get(m);
			double t = 0;
			for(int n = 0; n < len; n++){
				t += d[n];
			}
			rs[m] = t / len;
 		}
		return rs;
	}
	/**
	 * 获取在线数据列表中每个数组的第k个数
	 * @param list 输入列表
	 * @param k 序号(0~arr.length-1)
	 * @return 列表中每个数组的第k个数成的数组
	 */
	private double[] getDoubleArr(List<Double[]> list, int k){
		if(k > list.get(0).length){
			return null;
		}
		double[] result = new double[list.size()];
		for(int j = 0; j < list.size(); j++){
			result[j] = list.get(j)[k];
		}
		return result;
	}
	
	/**
	 * 辅助knn3进行计算
	 * @param offlineList 输入离线数据列表
	 * @param online 输入在线当前检测到的值
	 * @return 代表点位置的数
	 */
	private int knn(List<Double[]> offlineList, double[] online) {
		Map<String, Double> positionToLength = new HashMap<>();
		int offlineDataLength = offlineList.get(0).length;
		double[] offline;
		for(int k = 0; k < offlineDataLength; k++){
			offline = new double[online.length];
			for(int m = 0; m < offline.length; m++){
				offline[m] = offlineList.get(m)[k];
			}
			positionToLength.put(k + "", vectorDistance(online, offline));
		}
		
		// 根据距离大小排序，小的在前
		List<Map.Entry<String, Double>> distanceList = new ArrayList<Map.Entry<String, Double>>(positionToLength.entrySet());
		Collections.sort(distanceList,
				new Comparator<Map.Entry<String, Double>>() {
					public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
						return o1.getValue() - o2.getValue() > 0 ? 1 : -1;
					}
				});
		int position = 0;
		int num = 3;
		for(int i = 0; i < num; i++){
			position += Integer.valueOf(distanceList.get(i).getKey());
		}
		position = position / num;
		return position;
	}
	
	
	private int knn(List<Double[]> offlineList, List<Double[]> onlineList) {
		Map<String, Double> positionToLength = new HashMap<>();
		//double[] online = new double[onlineList.size()];
		int onlineDataLength = onlineList.get(0).length;
		int offlineDataLength = offlineList.get(0).length;
		/*for(int k = 0; k < online.length; k++){
			online[k] = onlineList.get(k)[onlineDataLength - 1];
		}*/
		
		double[] online = getDoubleArr(onlineList, onlineDataLength - 1);
		
		double[] offline;
		for(int k = 0; k < offlineDataLength; k++){
			offline = new double[online.length];
			for(int m = 0; m < offline.length; m++){
				offline[m] = offlineList.get(m)[k];
			}
			positionToLength.put(k + "", vectorDistance(online, offline));
		}
		
		// 根据距离大小排序，小的在前
		List<Map.Entry<String, Double>> distanceList = new ArrayList<Map.Entry<String, Double>>(positionToLength.entrySet());
		Collections.sort(distanceList,
				new Comparator<Map.Entry<String, Double>>() {
					public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
						return o1.getValue() - o2.getValue() > 0 ? 1 : -1;
					}
				});
		int position = 0;
		int num = 3;
		for(int i = 0; i < num; i++){
			position += Integer.valueOf(distanceList.get(i).getKey());
			//System.out.println(distanceList.get(i).getKey() + "\t" + distanceList.get(i).getValue());
		}
		position = position / num;
		//System.out.println(position);
		return position;
	}
	
	private int knn2(List<Double[]> offlineList, List<Double[]> onlineList) {
		Map<String, Double> positionToLength = new HashMap<>();
		double[] online = new double[onlineList.size()];
		int onlineDataLength = onlineList.get(0).length;
		int offlineDataLength = offlineList.get(0).length;
		for(int k = 0; k < online.length; k++){
			online[k] = onlineList.get(k)[onlineDataLength - 1];
		}
		
		double[] offline;
		for(int k = 0; k < offlineDataLength; k++){
			offline = new double[online.length];
			for(int m = 0; m < offline.length; m++){
				offline[m] = offlineList.get(m)[k];
			}
			positionToLength.put(k + "", vectorDistance(online, offline));
		}
		
		// 根据距离大小排序，小的在前
		List<Map.Entry<String, Double>> distanceList = new ArrayList<Map.Entry<String, Double>>(positionToLength.entrySet());
		Collections.sort(distanceList,
				new Comparator<Map.Entry<String, Double>>() {
					public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
						int v1 = Integer.valueOf(o1.getKey());
						int v2 = Integer.valueOf(o2.getKey());
						if(v1 > v2){
							return 1;
						}else if(v1 < v2){
							return -1;
						}
						return 0;
					}
				});
		double value = 0;
		int num = 3;
		double min = Double.MAX_VALUE;
		int position = -1;
		for(int k = 0; k < distanceList.size() - num; k++){
			value = distanceList.get(k).getValue() + distanceList.get(k + 1).getValue() + distanceList.get(k + 2).getValue();
			if(value < min){
				min = value;
				position = k;
			}
		}
		return position;
	}
	

	/**
	 * 计算向量距离∑(xi - yi)^2
	 * @param online 输入向量
	 * @param offline 输入向量
	 * @return 输入向量的向量距离
	 */
	private double vectorDistance(double[] online, double[] offline) {
		double value = 0;
		for (int k = 0; k < online.length; k++) {
			value += Math.pow(online[k] - offline[k], 2);
		}
		return value;
	}

	private int calc(List<Double[]> offLineList, List<Double[]> onlineList){
		List<Integer> numPair = new ArrayList<>();
		List<Integer> secondMatchNum = new ArrayList<>();
		if(offLineList.size() != onlineList.size()){
			return -1;
		}
		final int MIN_OFFLINE_LEN = 5;
		int listSize = offLineList.size();
		for(int i = 0; i < listSize; i++){
			double[] onlineData = fromDouble(onlineList.get(i));
			Map<String, Double> onlineDataInfo = arrInfo(onlineData);
			double[] offlineData = fromDouble(offLineList.get(i));
			//System.out.println("onlineList_" + i + ": " + Arrays.deepToString(onlineList.get(i)));
			//System.out.println("offlineList_" + i + ": " + Arrays.deepToString(offLineList.get(i)));
			for(int j = 0; j < offlineData.length - MIN_OFFLINE_LEN; j++){
				for(int k = j + MIN_OFFLINE_LEN; k < offlineData.length; k++){
					int t = k - j + 1;
					double[] offlinePartData = new double[t];
					System.arraycopy(offlineData, j, offlinePartData, 0, t);
					Map<String, Double> offlinePartDataInfo = arrInfo(offlinePartData);
					
					if(isMatch(onlineDataInfo, offlinePartDataInfo)){
						//System.out.println("offline: [" + j + ", " + k + "]");

//						System.out.println("\tonlinedata_" + i + ": " + Arrays.deepToString(toDouble(onlineData)));
//						System.out.println("\tofflinedata:" + Arrays.deepToString(toDouble(offlineData)));
						if(furtherMatch(onlineData, offlinePartData)){
							//System.out.println("\t\toffline index: [" + j + ", " + k + "]");
							numPair.add(j);
							numPair.add(k);
							secondMatchNum.add(k);
						}
					}
				}
			}
		}
		
		//Integer[][] offlineIndexes = listToArray(numPair);
		//Utils.printArr(offlineIndexes);
		//Utils.printList(secondMatchNum);
		int matchResult = dealSecondMatchNum(secondMatchNum);
		return matchResult;
		//System.out.println(matchResult);
	}
	
	
	private int dealSecondMatchNum(List<Integer> secondMatchNum) {
		int avg = (int)getAvg(secondMatchNum);
		//System.out.println("avg: " + avg);
		List<Integer> partIntegers = removeOutlier(avg, secondMatchNum);
		//Utils.printList(partIntegers);
		return (int)getAvg(partIntegers);
	}

	/**
	 * 去除离群点，即离平均值比较远的值(在平均值的20%以内)
	 * @param avg 平均值
	 * @param list 待判断的点
	 * @return 去除离群点的后的列表
	 */
	private List<Integer> removeOutlier(int avg, List<Integer> list) {
		return removeOutlier(avg, list, 0.2f);
	}

	/**
	 * 去除离群点，即离平均值比较远的值
	 * @param avg 平均值
	 * @param list 待判断的点
	 * @param f 离平均值的范围
	 * @return 去除离群点的后的列表
	 */
	private List<Integer> removeOutlier(int avg, List<Integer> list, float f) {
		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < list.size(); i++){
			if(isInclude(avg, list.get(i), f))
				result.add(list.get(i));
		}
		return result;
	}

	/**
	 * 获取平均值
	 * @param list 输入列表
	 * @return 平均值
	 */
	private double getAvg(List<Integer> list){
		double avg = 0;
		for(int i = 0; i < list.size(); i++){
			avg += list.get(i);
		}
		return avg / list.size();
	}
	
	private boolean furtherMatch(double[] online, double[] offline){
		int onLen = online.length;
		int offLen = offline.length;
		int minLen = onLen > offLen ? offLen : onLen;
		
		
		if(minLen >= 12){
			double[] onlinePart1 = new double[onLen / 2];
			double[] onlinePart2 = new double[onLen - onLen / 2];
			double[] offlinePart1 = new double[offLen / 2];
			double[] offlinePart2 = new double[offLen - offLen / 2];
			
			System.arraycopy(online, 0, onlinePart1, 0, onLen / 2);
			System.arraycopy(online, onLen / 2, onlinePart2, 0, onlinePart2.length);
			System.arraycopy(offline, 0, offlinePart1, 0, offLen / 2);
			System.arraycopy(offline, offLen / 2, offlinePart2, 0, offlinePart2.length);
			if(isMatch(arrInfo(onlinePart2), arrInfo(offlinePart1), 0.15f) && isMatch(arrInfo(onlinePart2), arrInfo(offlinePart2), 0.15f)){
				return true;
			}
		}else{
			if(isMatch(arrInfo(online), arrInfo(offline), 0.1f)){
				return true;
			}
		}
		return false;
	}
	
	private boolean furtherMatch(double[] online, double[] offline, int k){
		int min = online.length > offline.length ? offline.length : online.length;
		int len1 = online.length / MIN_MATCH_LENGTH;
		int len2 = offline.length / MIN_MATCH_LENGTH;
	
		
		return false;
	}
	
	
	/**
	 * @date 2014.7.11
	 * 使用在线获取的数据进行测试
	 */
	private static void testOnlineData(){
		PathMatch_3 dm = new PathMatch_3();
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
		PathMatch_3 pm = new PathMatch_3();
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
		PathMatch_3 pm = new PathMatch_3();
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
	
	private boolean isMatch(Map<String, Double> tInfo, Map<String, Double> dInfo, float factor) {
		if (isInclude(tInfo.get("MAX"), dInfo.get("MAX"), factor) && isInclude(tInfo.get("MIN"), dInfo.get("MIN"), factor)
				&& isInclude(tInfo.get("AVG"), dInfo.get("AVG"), factor) && isInclude(tInfo.get("START"), dInfo.get("START"), factor)
				&& isInclude(tInfo.get("END"), dInfo.get("END"), factor)) {
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
