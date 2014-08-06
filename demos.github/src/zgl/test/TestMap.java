package zgl.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import zgl.util.Utils;

public class TestMap {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> treeMap = new TreeMap<>();
		Map<Integer, Integer> hashMap	= new HashMap<Integer, Integer>();
		Map<Integer, Integer> linkHashMap = new LinkedHashMap<>();
		for(int i = 10; i > 0; i--){
			list.add(i);
			treeMap.put(i, i * i);
			hashMap.put(i, i * i);	
			linkHashMap.put(i, i * i);
		}
		for(int i = 11; i < 15; i++){
			list.add(i);
			treeMap.put(i, i * i);
			hashMap.put(i, i * i);	
			linkHashMap.put(i, i * i);
		}
		Utils.printList(list);
		Set<Integer> setTree = treeMap.keySet();
		for(Integer s : setTree){
			System.out.print(treeMap.get(s) + " ");
		}
		System.out.println();
		Set<Integer> setHash = hashMap.keySet();
		for(Integer s : setHash){
			System.out.print(hashMap.get(s) + " ");
		}
		System.out.println();
		Set<Integer> linkMap = linkHashMap.keySet();
		for(Integer s : linkMap){
			System.out.print(linkHashMap.get(s) + " ");
		}
		
	}
}
