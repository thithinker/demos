package demos.algorithm;

public class Node<T> {
	public Node(T v){
		this.value = v;
	}
	public T value;
	Node<T> next;
}
