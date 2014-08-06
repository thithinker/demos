package demos.algorithm.princeton;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
	private Item[] q;
	private int N = 0;
	private int first = 0;
	private int last = 0;

	@SuppressWarnings("unchecked")
	public ResizingArrayQueue() {
		q = (Item[]) new Object[2];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private void resize(int max) {
		assert max >= N;
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = q[(first + i) % q.length];
		}
		q = temp;
		first = 0;
		last = N;
	}

	public void enqueue(Item item) {
		if (N == q.length)
			resize(2 * q.length);
		q[last++] = item;
		if (last == q.length)
			last = 0;
		N++;
	}

	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = q[first];
		q[first] = null;
		N--;
		first++;
		if (first == q.length)
			first = 0;
		if (N > 0 && N == q.length / 4)
			resize(q.length / 2);
		return item;
	}

	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		return q[first];
	}

	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<Item> {
		private int i = 0;

		@Override
		public boolean hasNext() {
			return i < N;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = q[(i + first) % q.length];
			i++;
			return item;
		}
	}

	public static void main(String[] args) {
		ResizingArrayQueue<String> q = new ResizingArrayQueue<>();
		for(int i = 0; i < 5; i++){
			String s = RandomStringUtils.random(2, true, true);
			q.enqueue(s);
			System.out.print(s + " ");
		}
		System.out.println();
		for(String s : q){
			System.out.print(s + " ");
		}
	}

}
