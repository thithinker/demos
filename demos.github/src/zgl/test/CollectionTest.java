package zgl.test;

import junit.framework.TestCase;

import org.junit.Test;

public class CollectionTest extends TestCase{
	@Test
	public void testInsert(){
		Collection c = new Collection();
		c.insert("asf");
		c.insert("sdf");
		assertEquals(2, c.size());
	}
	@Test
	public void testRemove(){
		Collection c = new Collection();
		c.insert("this");
		c.insert("that");
		c.insert("cat");
		assertEquals(3, c.size());
		//System.out.println(c);
		c.remove("that");
		assertEquals(true, c.isPresent("this"));
		assertEquals(false, c.isPresent("thaat"));
		//System.out.println(c);
		assertEquals(2, c.size());
		assertEquals(false, c.isEmpty());
		c.makeEmpty();
		assertEquals(0, c.size());
	}
}
