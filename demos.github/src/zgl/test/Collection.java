package zgl.test;

import java.util.Arrays;

/**
 * 《数据结构与算法》 P20
 * @author yize
 * 2014年7月16日
 */
public class Collection {
	private Object[] items;
	private int len;
	private final static int DEFAULT_LEN = 10;
	public Collection(){
		this(DEFAULT_LEN);
	}
	public Collection(int len){
		this.len = 0;
		items = new Object[len];
	}
	
	public boolean isEmpty(){
		return len == 0;
	}
	
	public void makeEmpty(){
		items = new Object[DEFAULT_LEN];
		len = 0;
	}
	
	public void insert(Object obj){
		if(len > items.length){
			items = Arrays.copyOf(items, (int)(items.length * 1.5));
		}
		items[len++] = obj;
	}
	
	public void remove(Object obj){
		for(int i = 0; i < len; i++){
			if(items[i].equals(obj)){
				moveItems(i);
				len--;
			}
		}
		
	}
	
	public boolean isPresent(Object obj) {
		for(int i = 0; i < len; i++){
			if(items[i].equals(obj))
				return true;
		}
		return false;
	}
	
	private void moveItems(int start){
		for(int i = start; i < len - 1; i++){
			items[i] = items[i + 1];
		}
		items[len - 1] = null;
	}
	
	public int size(){
		return len;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < len; i++){
			sb.append(items[i] + " , ");
		}
		sb.append("]");
		return sb.toString();
	}
}
