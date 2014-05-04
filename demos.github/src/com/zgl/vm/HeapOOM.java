package com.zgl.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yize
 * @date May 4, 2014
 * 产生堆溢出
 * 堆内存大小固定为20M
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
	static class OOMObject{}
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while(true){
			list.add(new OOMObject());
		}
	}
}
