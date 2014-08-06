package zgl.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import zgl.util.Utils;

public class TestMap_140724 {
	public static void main(String[] args) {
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new LinkedHashMap<>();
		Random r = new Random();
		for(int i = 0; i < 20; i = i + 2){
			map1.put(i + " ", i + " " + r.nextInt());
			map2.put(i + " ", i + " " + r.nextInt());
		}
		for(int i = 1; i < 20; i = i + 2){
			map1.put(i + " ", i + " " + r.nextInt());
			map2.put(i + " ", i + " " + r.nextInt());
		}
		
		List<String> list1 = new ArrayList<>(map1.keySet());
		List<String> list2 = new ArrayList<>(map2.keySet());
		Utils.printList(list1);
		Utils.printList(list2);	
  	}
}
