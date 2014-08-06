package demos.algorithm.princeton;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yize
 * 2014年6月7日
 */
public class Bag<Item> implements Iterable<Item> {
	private int N;
	private Node<Item> first;
	
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	public Bag(){
		first = null;
		N = 0;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return N;
	}
	
	public void add(Item item){
		Node<Item> oldItem = first;
		first = new Node<>();
		first.item = item;
		first.next = oldItem;
		N++;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	@SuppressWarnings("hiding")
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		public ListIterator(Node<Item> first){
			current = first;
		}
		
		public boolean hasNext(){
			return current != null;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public Item next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
	public static void main(String[] args) {
		Bag<String> bag = new Bag<>();
		for(int i = 0; i < 5; i++){
			String item = RandomStringUtils.random(6, true, false);
			System.out.print(item + " ");
			bag.add(item);
		}
		System.out.println();
		for(String s : bag){
			System.out.print(s + " ");
		}
		
		int i = StringUtils.getLevenshteinDistance("RandomStringUtils", "LevenshteinDistance");
		System.out.println("\n" + i);
	}
}
