package demos.algorithm;

/*class Node<T>{
	public Node(T v){
		this.value = v;
	}
	
	T value;
	Node<T> next;
}*/

public class FirstCommonNode {
	public static void main(String[] args) {
		Node<String> a = new Node<String>("a");
		Node<String> b = new Node<String>("b");
		Node<String> c = new Node<String>("c");
		Node<String> d = new Node<String>("d");
		Node<String> e = new Node<String>("e");
		Node<String> f = new Node<String>("f");
		Node<String> g = new Node<String>("g");
		Node<String> h = new Node<String>("h");
		Node<String> i = new Node<String>("i");
		Node<String> j = new Node<String>("j");
		Node<String> k = new Node<String>("k");
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		//f.next = null;
		
		k.next = j;
		j.next = i;
		i.next = h;
		h.next = g;
		g.next = d;
		
		//System.out.println(new FirstCommonNode().len(a));
		//System.out.println(new FirstCommonNode().len(k));
		
		@SuppressWarnings("unchecked")
		Node<String> t = (Node<String>) new FirstCommonNode().calc(k, a);
		System.out.println(t.value);
	}
	
	
	
	public Node<?> calc(Node<?> head1, Node<?> head2){
		int len1 = len(head1);
		int len2 = len(head2);
		Node<?> p1 = head1;
		Node<?> p2 = head2;
		
		
		
		if(len1 > len2){
			int tmp = len1 - len2;
			while(tmp-- > 0){
				p1 = p1.next;
			}
			while(p1 != null && p2 != null && !p1.equals(p2)){
				p1 = p1.next;
				p2 = p2.next;
			}
			return p1;
		}else{
			int tmp = len2 - len1;
			System.out.println(tmp);
			while(tmp-- > 0){
				p2 = p2.next;
			}
			while(p1 != null && p2 != null && !p1.equals(p2)){
				p1 = p1.next;
				p2 = p2.next;
			}
			return p1;
		}
	}
	
	private int len(Node<?> head){
		int len = 0;
		if(head == null){
			return -1;
		}
		Node<?> p = head;
		len++;
		while(p.next != null){
			p = p.next;
			len++;
		}
		
		return len;
	}
}
