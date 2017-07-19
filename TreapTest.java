package assignment;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class TreapTest {
	
	@Test
	public void testInsertNull() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(null, "");
		assertEquals("", tm1.toString());
		tm1.insert(0, null);
		assertEquals("", tm1.toString());
	}
	
	@Test
	public void testInsertDifferent() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "");
	}
	
	@Test 
	public void testLookup() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "");
		assertEquals("", tm1.lookup(0));
		assertEquals(null, tm1.lookup(12));
	}
	
	@Test 
	public void testOverride() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		tm1.insert(0, "b");
		assertEquals("b", tm1.lookup(0));
	}
	
	@Test 
	public void testIterator() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		tm1.insert(1, "b");
		tm1.insert(2, "c");
		tm1.insert(3, "d");
		Iterator<Integer> it = tm1.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	@Test 
	public void testToString() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		tm1.insert(1, "b");
		tm1.insert(2, "c");
		tm1.insert(3, "d");
		System.out.println(tm1.toString());
	}
	
	@Test 
	public void testLookupEmpty() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.lookup(0);
	}
	
	@Test
	public void testRemoveNull() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		String val = tm1.remove(null);
		assertEquals(null, val);
	}
	
	@Test
	public void testRemoveOne() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "");
		String val = tm1.remove(0);
		assertEquals("", val);
	}
	
	@Test
	public void testSplitEmpty() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.split(0);
	}
	
	@Test
	public void testSplitNull() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		tm1.insert(2, "b");
		tm1.insert(3, "d");
		tm1.split(null);
	}
	
	@Test
	public void testSplit() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		tm1.insert(2, "b");
		tm1.insert(3, "d");
		TreapMap<Integer, String>[] tms = (TreapMap<Integer, String>[]) tm1.split(1);
		TreapMap<Integer, String> tm2 = (TreapMap<Integer, String>) tms[0]; 
		TreapMap<Integer, String> tm3 = (TreapMap<Integer, String>) tms[1];
		TreapMap<Integer, String> tm4 = new TreapMap<Integer, String>();
		tm4.insert(0, "a");
		TreapMap<Integer, String> tm5 = new TreapMap<Integer, String>();
		tm5.insert(2, "b");
		tm5.insert(3, "d");
		Iterator<Integer> it2 = tm2.iterator();
		Iterator<Integer> it4 = tm4.iterator();
		while(it2.hasNext()) {
			if(it4.hasNext()) {
				assertEquals(it2.next(), it4.next());
			}
			else {
				assertTrue("x".equals("a"));
			}
		}
		
		Iterator<Integer> it3 = tm3.iterator();
		Iterator<Integer> it5 = tm5.iterator();
		while(it3.hasNext()) {
			if(it5.hasNext()) {
				assertEquals(it3.next(), it5.next());
			}
			else {
				assertTrue("x".equals("a"));
			}
		}
	}
	
	@Test
	public void testJoinNull() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		tm1.insert(2, "b");
		tm1.insert(3, "d");
		tm1.join(null);;
	}
	
	@Test
	public void testJoin() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		System.out.println(tm1.toString());
		TreapMap<Integer, String> tm2 = new TreapMap<Integer, String>();
		tm2.insert(2, "b");
		tm2.insert(3, "d");
		System.out.println(tm2.toString());
		tm1.join(tm2);
		System.out.println(tm1.toString()); //needs to have same priorities
	}
	
	@Test
	public void testInsertRemove() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		tm1.insert(2, "b");
		String str = tm1.toString();
		tm1.insert(3, "d");
		tm1.remove(3);
		assertEquals(str, tm1.toString());
	}
	
	@Test
	public void testRemove() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(0, "a");
		tm1.insert(2, "b");
		tm1.remove(2);
	}
	
	@Test
	public void testBalanceFactor() {
		TreapMap<Integer, String> tm1 = new TreapMap<Integer, String>();
		tm1.insert(1, "b");
		tm1.insert(0, "a");
		tm1.insert(2, "a");
		System.out.println(tm1.toString());
		System.out.println(tm1.balanceFactor());
	}
}
