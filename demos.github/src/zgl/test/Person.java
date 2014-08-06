package zgl.test;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

public class Person implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3418625140197485928L;
	private String name;
	private Person father;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) {
		this.father = father;
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	public Person(String name, Person father) {
		super();
		this.name = name;
		this.father = father;
	}
	
	@Override
	protected Person clone() {
		Person p = null;
		try {
			p = (Person)super.clone();
			p.setFather(new Person(this.getFather().getName()));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	
	public static void main(String[] args) {
		Person f = new Person("father");
		Person s1 = new Person("Son1", f);
		Person s2 = s1;
		s2 = (Person) s1.clone();
		s2.setName("Son2");
		s1.getFather().setName("s1's father");
		System.out.println(s1.getName() + " " + s1.getFather().getName());
		System.out.println(s2.getName() + " " + s2.getFather().getName());
		
		Person s3 = SerializationUtils.clone(s1);
		s3.setName("son3");
		System.out.println(s3.getName() + " " + s1.getFather().getName());
		s3.getFather().setName("s3's father");
		System.out.println(s1.getName() + " " + s1.getFather().getName());
		System.out.println(s3.getName() + " " + s3.getFather().getName());
	}
}
