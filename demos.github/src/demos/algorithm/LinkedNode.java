package demos.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 链表逆置操作
 * @author yize
 *
 */
public class LinkedNode {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		try {
			 line = br.readLine();
			 br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] strs = line.split(" ");
		
		Node<String> currNode = null;
		Node<String> head = null;
		
		/**
		 * 构造链表
		 */
		for(int i = 0; i < strs.length; i++){
			if(currNode == null){
				currNode = new Node<String>(strs[i]);
				head = currNode;
			}else{
				currNode.next = new Node<String>(strs[i]);
				currNode = currNode.next;
			}
		}
		//原链表
		print(head);
		//辅助空间逆置
		//Node<String> p = new LinkedNode().reverse(head);
		
		//就地逆置
		Node<String> p = new LinkedNode().reverse2(head);
		print(p);
	}
	
	/**
	 * 链表逆置 时间复杂度O(N), 空间复杂度O(N)
	 * 2014年8月8日
	 * @param head 原链表头节点
	 * @return 逆置后的链表头节点
	 */
	public <T> Node<T> reverse(Node<T> head){
		Node<T> p = null;
		Node<T> old = head;
		while(old != null){
			Node<T> tNode = new Node<T>(old.value);
			tNode.next = p;
			p = tNode;
			old = old.next;
		}
		return p;
	}
	
	/**
	 * 单链表就地逆置 时间复杂度O(N) 空间复杂度O(1)
	 * 2014年8月8日
	 * @param head
	 * @return
	 */
	public <T> Node<T> reverse2(Node<T> head){
		Node<T> p = head;
		Node<T> q = p.next;
		head.next = null;
		while(q.next != null){
			p = q;
			q = q.next;
			p.next = head;
			head = p;
		}
		q.next = head;
		head = q;
		return head;
	}
	
	private static <T> void print(Node<T> head){
		while(head != null){
			System.out.print(head.value + ", ");
			head = head.next;
		}
		System.out.println();
	}
}
