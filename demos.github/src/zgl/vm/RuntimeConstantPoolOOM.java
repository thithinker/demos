package zgl.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yize
 * @date May 4, 2014
 * 运行时常量池内存溢出
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 运行不理想
 * 虚拟机太好了？
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
