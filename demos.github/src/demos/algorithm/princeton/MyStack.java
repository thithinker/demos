package demos.algorithm.princeton;

import java.util.Iterator;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author yize
 * 2014年6月7日
 */
public class MyStack<Item> implements Iterable<Item>{
	private Node<Item> top;
	private int length;
	
	public MyStack(){
		top = null;
		length = 0;
	}	
	
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	public void push(Item item){
		Node<Item> old = top;
		top = new Node<>();
		top.item = item;
		top.next = old;
		length++;
	}
	
	public Item pop(){
		Item item = top.item;
		top = top.next;
		return item;
	}
	
	public boolean isEmpty(){
		return top == null;
	}
	
	public int size(){
		return length;
	}
	
	
	@Override
	public Iterator<Item> iterator() {
		return new MyIterator(top);
	}
	
	private class MyIterator implements Iterator<Item> {
		Node<Item> top;
		public MyIterator(Node<Item> top){
			this.top = top;
		}
		
		@Override
		public boolean hasNext() {
			return top != null;
		}

		@Override
		public Item next() {
			Item item = top.item;
			top = top.next;
			return item;
		}

		@Override
		public void remove() {
			throw new RuntimeException("Not Supported!");
		}
	}
	
	public static void main(String[] args) {
		MyStack<String> stack = new MyStack<>();
		System.out.println("Add order: ");
		for(int i = 0; i < 5; i++){
			String s = RandomStringUtils.random(5, new char[]{'z', 'w', 'g', 'e', 'a', 't'});
			stack.push(s);
			System.out.print(s + " ");
		}
		System.out.println("\nIterator order: ");
		for(String s : stack){
			System.out.print(s + " ");
		}
		System.out.println();
		System.out.println(stack.pop());
		System.out.println("\nIterator order again: ");
		for(String s : stack){
			System.out.print(s + " ");
		}
		
		
	}

}
