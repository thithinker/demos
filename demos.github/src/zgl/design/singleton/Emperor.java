package zgl.design.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * 
 * @author yize
 * 2013��12��6��
 */
public class Emperor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Emperor emperor = new Emperor();
	
	private Emperor(){
		
	}
	
	public static Emperor getSingleton(){
		return emperor;
	}
	
	private void say(){
		System.out.println("I'm an emperor.");
	}
	
	public static void main(String[] args){
		ArrayList<Emperor> es = new ArrayList<Emperor>();
		es.add(Emperor.getSingleton());
		es.add(Emperor.getSingleton());
		
		System.out.println(es.get(0) == es.get(1));
		
		//ͨ�������ȡ���󣬴�ʱEmperor��ʵ��������Ψһ
		try {
			Class<?> clazz = Class.forName("zgl.design.singleton.Emperor");
			Emperor emperor1 = (Emperor)clazz.newInstance();
			System.out.println(emperor1 + "\n\t" + es.get(0) + "\n\t" + es.get(1));
			emperor1.say();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
		//ͨ�������л���ȡ���󣬴�ʱEmperor��ʵ������Ҳ����Ψһ
		ObjectOutputStream oos;
		ObjectInputStream ois;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("./object.txt")));
			oos.writeObject(es.get(0));
			oos.close();
			
			ois = new ObjectInputStream(new FileInputStream(new File("./object.txt")));
			Emperor e = (Emperor)ois.readObject();
			System.out.println(e == es.get(0));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
}
