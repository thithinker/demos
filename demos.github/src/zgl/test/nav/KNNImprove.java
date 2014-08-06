package zgl.test.nav;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KNNImprove {
	private enum calcType{MAX, MIN, AVG};
	/**
	 * 结合历史数据改进的knn算法
	 * @param offlineList 离线数据列表
	 * @param onlineList 在线数据列表
	 * @return 定位位置
	 */
	public int knn(List<Double[]> offlineList, List<Double[]> onlineList){
		int length = onlineList.get(0).length;
		int offlineArrLength = offlineList.get(0).length;
		//List<Double> distances = new ArrayList<>();
		Map<Integer, Double> distanceMap = new HashMap<>();
		/*int[] rs = new int[length - 7];
		for(int i = 7; i < length; i++){
			rs[i - 7] = knn(offlineList, getDoubleArr(onlineList, i));
		}*/
		int[] rs = new int[length];
		for(int i = 0; i < length; i++){
			rs[i] = knn(offlineList, getDoubleArr(onlineList, i));
		}
		
		int begin = rs[0];
		for(int m = begin ; m < offlineArrLength; m++){
			distanceMap.put(m, getDistance(begin, m, offlineList, onlineList));
		}
		
		for(int m = begin ; m >= 0; m--){
			distanceMap.put(m, getDistance(begin, m, offlineList, onlineList));
		}
		
		List<Map.Entry<Integer, Double>> list = new ArrayList<>(distanceMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		int result = 0;
		int k = 3;
		for(int i = 0; i < k; i++){
			result += list.get(i).getKey();
		}
		return result / k;
	}

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
		int len = Math.abs(currCalc - begin) + 1;
		for(int m = 0; m < offlineList.size(); m++){
			Double[] d = new Double[len];
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
	
}
