package zgl.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import zgl.util.Utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Lists;

/**
 * @author yize
 * 2014年7月31日
 */
public class GuavaTest {
	public static void main(String[] args) {
		new GuavaTest().list();
	}
	
	public void list(){
		List<String> list1= Lists.newArrayList("sd", "sdf");
		list1.add("sdjfl");
		for(String s : list1){
			System.out.print(s + " ");
		}
		System.out.println();
		ImmutableList<String> list2 = ImmutableList.of("day1", "day2", "day3", "day4", "day5", "day6");
		Utils.printList("BeforeReverse", list2);
		ImmutableList<String> list3 = list2.reverse();
		Utils.printList("AfterReverse", list3);
		
		ImmutableList<Object> list4 = ImmutableList.builder().add("this").add("is").add("new").add("world").build();
		Utils.printList(list4);
		
		ImmutableList<String> list5 = ImmutableList.of();
	
	}
	public void map(){
		
	}
	
	public void set(){
		
	}
}
