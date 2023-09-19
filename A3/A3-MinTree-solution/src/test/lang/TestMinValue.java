package lang;

import static org.junit.Assert.*;
import lang.ast.Leaf;
import lang.ast.Pair;
import lang.ast.Program;

import org.junit.Test;

@SuppressWarnings("unused")
public class TestMinValue {
	@Test
	public void localMin() {
		Leaf l1 = new Leaf(1);
		Leaf l2 = new Leaf(2);
		Leaf l3 = new Leaf(3);
		Pair p1 = new Pair(l2, l3);
		Pair p2 = new Pair(l1, p1);
		Program p = new Program(p2);
		
		assertEquals(1, l1.localMin());
		assertEquals(2, l2.localMin());
		assertEquals(3, l3.localMin());
		assertEquals(2, p1.localMin());
		assertEquals(1, p2.localMin());
	}
	
	@Test
	public void globalMin() {
		Leaf l1 = new Leaf(1);
		Leaf l2 = new Leaf(2);
		Leaf l3 = new Leaf(3);
		Pair p1 = new Pair(l2, l3);
		Pair p2 = new Pair(l1, p1);
		Program p = new Program(p2);
		
		assertEquals(1, l1.globalMin());
		assertEquals(1, l2.globalMin());
		assertEquals(1, l3.globalMin());
		assertEquals(1, p1.globalMin());
		assertEquals(1, p2.globalMin());
	}
	
	@Test
	public void isMinValue() {
		Leaf l1 = new Leaf(1);
		Leaf l2 = new Leaf(2);
		Leaf l3 = new Leaf(3);
		Pair p1 = new Pair(l2, l3);
		Pair p2 = new Pair(l1, p1);
		Program p = new Program(p2);
		
		assertTrue(l1.isMinValue());
		assertFalse(l2.isMinValue());
		assertFalse(l3.isMinValue());
	}
	
	@Test
	public void nbrOfMinValues() {
		Leaf l1 = new Leaf(1);
		Leaf l2 = new Leaf(2);
		Leaf l3 = new Leaf(3);
		Pair p1 = new Pair(l2, l3);
		Pair p2 = new Pair(l1, p1);
		Program p = new Program(p2);
		
		assertEquals(1, p.nbrOfMinValues());
	}

}
