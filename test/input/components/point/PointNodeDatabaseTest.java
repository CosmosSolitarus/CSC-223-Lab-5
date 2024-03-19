package input.components.point;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PointNodeDatabaseTest {

	@Test
	void putTest() {
		PointNodeDatabase database = new PointNodeDatabase();
		PointNode pn = new PointNode("PN 1", 1, 1);
		database.put(pn);
		assertEquals(database.getName(1, 1), "PN 1");
		assertEquals(database.getName(pn), "PN 1");

		PointNode pn2 = new PointNode("PN 2", 2, 2);
		database.put(pn2);
		assertEquals(database.getName(pn2), "PN 2");

		database.put(new PointNode(89.00000003, 14.298565645));
		assertTrue(database.contains(89, 14.298565645));
	}
	
	@Test 
	void containsNodeTest() {
		PointNode pn1 = new PointNode("PN 1", 1, 1);
		PointNode pnDuplicate = new PointNode("PN 1", 1, 1);
		PointNode pn2 = new PointNode("PN 2", 2.234567, 1.234567);
		PointNode pn3 = new PointNode("PN 3", Math.cbrt(13), Math.log(2));  //(2.35133, 0.69314)
		ArrayList<PointNode> pnlist = new ArrayList<PointNode> (Arrays.asList(pn1, pn2, pn3));
		PointNodeDatabase database = new PointNodeDatabase(pnlist);

		assertTrue(database.contains(pn1)); 
		assertTrue(database.contains(pnDuplicate));

		//even though pnDuplicate isn't technically in the database, an equal node is
		assertTrue(database.contains(pn2));
		assertTrue(database.contains(pn3));
		assertFalse(database.contains(new PointNode(2, 2)));
	}
	
	@Test
	void containsCoorTest() {
		PointNode pn1 = new PointNode("PN 1", 1, 1);
		PointNode pn2 = new PointNode("PN 2", 2.234567, 1.234567);
		PointNode pn3 = new PointNode("PN 3", Math.cbrt(13), Math.log(2));  //(2.35133, 0.69314)
		ArrayList<PointNode> pnlist = new ArrayList<PointNode> (Arrays.asList(pn1, pn2, pn3));
		PointNodeDatabase database = new PointNodeDatabase(pnlist);

		//pn1
		assertTrue(database.contains(1, 1)); 

		//pn2
		assertTrue(database.contains(2.234567, 1.234567));

		//pn2, rounded down
		assertTrue(database.contains(2.23456, 1.23456));

		//pn3
		assertTrue(database.contains(2.35133, 0.69314));

		//smoke test, does not contain
		assertFalse(database.contains(2, 2));

		//only slightly different from pn2, but different
		assertFalse(database.contains(2.23455, 1.23455));

		//x is right, y is wrong
		assertFalse(database.contains(2.234567, 1.23455));

		//x is wrong, y is right
		assertFalse(database.contains(2.23455, 1.234567));
	}

	@Test 
	void getNameNodeTest() {
		PointNode pn1 = new PointNode("PN 1", 1, 1);
		PointNode pnDuplicate = new PointNode("PN 1", 1, 1);
		PointNode pn2 = new PointNode("PN 2", 2.234567, 1.234567);
		PointNode pn3 = new PointNode("PN 3", Math.cbrt(13), Math.log(2));  //(2.35133, 0.69314)
		ArrayList<PointNode> pnlist = new ArrayList<PointNode> (Arrays.asList(pn1, pn2, pn3, pnDuplicate));
		PointNodeDatabase database = new PointNodeDatabase(pnlist);

		assertEquals(database.getName(pn1), "PN 1");
		assertEquals(database.getName(pn2), "PN 2");
		assertEquals(database.getName(pn3), "PN 3");
		assertEquals(database.getName(pnDuplicate), "PN 1");
		assertEquals(database.getName(new PointNode("name", 2, 2)), null);
	}
	
	@Test
	void getNameCoorTest() {
		PointNode pn1 = new PointNode("PN 1", 1, 1);
		PointNode pnDuplicate = new PointNode("PN 1 Dupe", 1, 1);
		PointNode pn2 = new PointNode("PN 2", 2.234567, 1.234567);
		PointNode pn3 = new PointNode("PN 3", Math.cbrt(13), Math.log(2));  //(2.35133, 0.69314)
		ArrayList<PointNode> pnlist = new ArrayList<PointNode> (Arrays.asList(pn1, pn2, pn3, pnDuplicate));
		PointNodeDatabase database = new PointNodeDatabase(pnlist);

		//pn1, gets pn1 and NOT pnDuplicate
		assertEquals(database.getName(1, 1), "PN 1");

		//pn2
		assertEquals(database.getName(2.234567, 1.234567), "PN 2");

		//pn2, rounded down
		assertEquals(database.getName(2.23456, 1.23456), "PN 2");

		//pn3 
		assertEquals(database.getName(2.35133, 0.69314), "PN 3");

		//smoke test, does not contain
		assertEquals(database.getName(2, 2), null);

		//only slightly different from pn2, but different
		assertEquals(database.getName(2.23455, 1.23455), null);

		//x is right, y is wrong
		assertEquals(database.getName(2.234567, 1.23455), null);

		//x is wrong, y is right
		assertEquals(database.getName(2.23455, 1.234567), null);
	}
	
	@Test 
	void getPointNodeTest() {
		PointNode pn1 = new PointNode("PN 1", 1, 1);
		PointNode pnDuplicate = new PointNode("PN 1 Dupe", 1, 1);
		PointNode pn2 = new PointNode("PN 2", 2.234567, 1.234567);
		PointNode pn3 = new PointNode("PN 3", Math.cbrt(13), Math.log(2));  //(2.35133, 0.69314)
		ArrayList<PointNode> pnlist = new ArrayList<PointNode> (Arrays.asList(pn1, pn2, pn3, pnDuplicate));
		PointNodeDatabase database = new PointNodeDatabase(pnlist);

		assertEquals(database.getPoint(pn1), pn1);

		//when it can differentiate between duplicates
		assertEquals(database.getPoint(pnDuplicate), pnDuplicate);
		assertEquals(database.getPoint(pn2), pn2);
		assertEquals(database.getPoint(pn3), pn3);
		assertEquals(database.getPoint(new PointNode(2,2)), null);
	}
	
	@Test 
	void getPointCoorTest() {
		PointNode pn1 = new PointNode("PN 1", 1, 1);
		PointNode pnDuplicate = new PointNode("PN 1 Dupe", 1, 1);
		PointNode pn2 = new PointNode("PN 2", 2.234567, 1.234567);
		PointNode pn3 = new PointNode("PN 3", Math.cbrt(13), Math.log(2));  //(2.35133, 0.69314)
		ArrayList<PointNode> pnlist = new ArrayList<PointNode> (Arrays.asList(pn1, pn2, pn3, pnDuplicate));
		PointNodeDatabase database = new PointNodeDatabase(pnlist);

		assertEquals(database.getPoint(1, 1), pn1); 	//does NOT return pnDuplicate
		assertEquals(database.getPoint(2.234567, 1.234567), pn2);
		assertEquals(database.getPoint(2.23456, 1.23456), pn2);	//rounded down
		assertEquals(database.getPoint(2.35133, 0.69314), pn3);

		//smoke test, does not contain
		assertEquals(database.getPoint(2, 2), null);

		//only slightly different from pn2, but different
		assertEquals(database.getPoint(2.23455, 1.23455), null);

		//x is right, y is wrong
		assertEquals(database.getPoint(2.234567, 1.23455), null);
		
		//x is wrong, y is right
		assertEquals(database.getPoint(2.23455, 1.234567), null);
	}
	
	@Test
	void toStringTest() {
		PointNodeDatabase database = new PointNodeDatabase();
		PointNode pn = new PointNode("PN 1", 1, 1);
		database.put(pn);
		PointNode pn2 = new PointNode("PN 2", 2, 2);
		database.put(pn2);
		System.out.println(database);
		assertEquals(database.toString(), "PN 1(1, 1)" + '\n' + "PN 2(2, 2)" + '\n');
	}
}
