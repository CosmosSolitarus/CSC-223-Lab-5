package input.components.point;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointNodeTest {

	@Test
	void Equalstest() {
		// NAMING SCHEME
		// u = unnamed
		// n = named
		// z = integer
		// q = decimal
		// Q = decimal (shortened)
		// r = irrational
		
		// unnamed PointNode with integers only
		PointNode uzz = new PointNode(12, -2);
		assertEquals(new PointNode(12, -2), uzz);
		
		// unnamed PointNode with integer and decimal
		PointNode uzq = new PointNode(0, -7.7);
		assertEquals(new PointNode(0, -7.7), uzq);
		
		// unnamed PointNode with integer and long decimal
		PointNode uzQ = new PointNode(-4, 0.1234567);
		assertEquals(new PointNode(-4, 0.12345), uzQ);
		
		// unnamed PointNode with integer and irrational
		PointNode uzr = new PointNode(9, Math.sqrt(3));
		assertEquals(new PointNode(9, 1.73205), uzr);
		
		// unnamed PointNode with decimals only
		PointNode uqq = new PointNode(-8.47, 0.001);
		assertEquals(new PointNode(-8.47, 0.001), uqq);
		
		// unnamed PointNode with decimal and long decimal
		PointNode uqQ = new PointNode(11.42, 1.0 / 7);
		assertEquals(new PointNode(11.42, 0.14285), uqQ);
		
		// unnamed PointNode with decimal and irrational
		PointNode uqr = new PointNode(-0.13, Math.PI);
		assertEquals(new PointNode(-0.13, 3.14159), uqr);
		
		// unnamed PointNode with long decimals only
		PointNode uQQ = new PointNode(89.00000003, 14.298565645);
		assertEquals(new PointNode(89, 14.29856), uQQ);
		
		// unnamed PointNode with long decimal and irrational
		PointNode uQr = new PointNode(6, Math.E);
		assertEquals(new PointNode(6, 2.71828), uQr);
		
		// unnamed PointNode with irrationals only
		PointNode urr = new PointNode(Math.cbrt(13), Math.log(2));
		assertEquals(new PointNode(2.35133, 0.69314), urr);
		
		
		// names + a decent into madness
		// named PointNode with integers only
		PointNode nzz = new PointNode("A", 12, -2);
		assertEquals(new PointNode("A", 12, -2), nzz);
		
		// named PointNode with integer and decimal
		PointNode nzq = new PointNode("", 0, -7.7);
		assertEquals(new PointNode("", 0, -7.7), nzq);
		
		// named PointNode with integer and long decimal
		PointNode nzQ = new PointNode("Paul", -4, 0.1234567);
		assertEquals(new PointNode("Paul", -4, 0.12345), nzQ);
		
		// named PointNode with integer and irrational
		PointNode nzr = new PointNode("__UNNAMED", 9, Math.sqrt(3));
		assertEquals(new PointNode("__UNNAMED", 9, 1.73205), nzr);
		
		// named PointNode with decimals only
		PointNode nqq = new PointNode("sdfsdf", -8.47, 0.001);
		assertEquals(new PointNode("sdfsdf", -8.47, 0.001), nqq);
		
		// named PointNode with decimal and long decimal
		PointNode nqQ = new PointNode("42", 11.42, 1.0 / 7);
		assertEquals(new PointNode("42", 11.42, 0.14285), nqQ);
		
		// named PointNode with decimal and irrational
		PointNode nqr = new PointNode("Try Minecraft", -0.13, Math.PI);
		assertEquals(new PointNode("Try Minecraft", -0.13, 3.14159), nqr);
		
		// named PointNode with long decimals only
		PointNode nQQ = new PointNode("Wake Up!", 89.00000003, 14.298565645);
		assertEquals(new PointNode("Wake Up!", 89, 14.29856), nQQ);
		
		// named PointNode with long decimal and irrational
		PointNode nQr = new PointNode("EEEEEEEEEE", 6, Math.E);
		assertEquals(new PointNode("EEEEEEEEEE", 6, 2.71828), nQr);
		
		// named PointNode with irrationals only
		PointNode nrr = new PointNode("DefinitelyNotAZipBomb.zip", Math.cbrt(13), Math.log(2));
		assertEquals(new PointNode("DefinitelyNotAZipBomb.zip", 2.35133, 0.69314), nrr);

		// named PointNode against an unnamed PointNode
		PointNode named = new PointNode("a name", 2, 2);
		assertEquals(new PointNode(2, 2), named);
		
		//tests that fail equals
		//swapped x and y
		assertNotEquals(new PointNode(2, 3), new PointNode(3, 2));

		//decimal vs integer
		assertNotEquals(new PointNode(2.2, 3), new PointNode(2, 3));

		//each decimal is 0.00001 off, just enough to count
		assertNotEquals(new PointNode(1.23456, 9.87654), new PointNode(1.23457, 9.87653));
		
		//same as last, but with an irrational
		assertNotEquals(new PointNode(2, Math.sqrt(3)), new PointNode(2, 1.73204));
	}
	
	@Test
	void toStringTest() {
		// NAMING SCHEME
		// u = unnamed
		// n = named
		// z = integer
		// q = decimal
		// Q = decimal (shortened)
		// r = irrational
		
		// unnamed PointNode with integers only
		PointNode uzz = new PointNode(12, -2);
		assertEquals("(12, -2)", uzz.toString());
		
		// unnamed PointNode with integer and decimal
		PointNode uzq = new PointNode(0, -7.7);
		assertEquals("(0, -7.7)", uzq.toString());
		
		// unnamed PointNode with integer and long decimal
		PointNode uzQ = new PointNode(-4, 0.1234567);
		assertEquals("(-4, 0.12345)", uzQ.toString());
		
		// unnamed PointNode with integer and irrational
		PointNode uzr = new PointNode(9, Math.sqrt(3));
		assertEquals("(9, 1.73205)", uzr.toString());
		
		// unnamed PointNode with decimals only
		PointNode uqq = new PointNode(-8.47, 0.001);
		assertEquals("(-8.47, 0.001)", uqq.toString());
		
		// unnamed PointNode with decimal and long decimal
		PointNode uqQ = new PointNode(11.42, 1.0 / 7);
		assertEquals("(11.42, 0.14285)", uqQ.toString());
		
		// unnamed PointNode with decimal and irrational
		PointNode uqr = new PointNode(-0.13, Math.PI);
		assertEquals("(-0.13, 3.14159)", uqr.toString());
		
		// unnamed PointNode with long decimals only
		PointNode uQQ = new PointNode(89.00000003, 14.298565645);
		assertEquals("(89, 14.29856)", uQQ.toString());
		
		// unnamed PointNode with long decimal and irrational
		PointNode uQr = new PointNode(6, Math.E);
		assertEquals("(6, 2.71828)", uQr.toString());
		
		// unnamed PointNode with irrationals only
		PointNode urr = new PointNode(Math.cbrt(13), Math.log(2));
		assertEquals("(2.35133, 0.69314)", urr.toString());
		
		
		// names + a decent into madness
		// named PointNode with integers only
		PointNode nzz = new PointNode("A", 12, -2);
		assertEquals("A(12, -2)", nzz.toString());
		
		// named PointNode with integer and decimal
		PointNode nzq = new PointNode("", 0, -7.7);
		assertEquals("(0, -7.7)", nzq.toString());
		
		// named PointNode with integer and long decimal
		PointNode nzQ = new PointNode("Paul", -4, 0.1234567);
		assertEquals("Paul(-4, 0.12345)", nzQ.toString());
		
		// named PointNode with integer and irrational
		PointNode nzr = new PointNode("__UNNAMED", 9, Math.sqrt(3));
		assertEquals("__UNNAMED(9, 1.73205)", nzr.toString());
		
		// named PointNode with decimals only
		PointNode nqq = new PointNode("sdfsdf", -8.47, 0.001);
		assertEquals("sdfsdf(-8.47, 0.001)", nqq.toString());
		
		// named PointNode with decimal and long decimal
		PointNode nqQ = new PointNode("42", 11.42, 1.0 / 7);
		assertEquals("42(11.42, 0.14285)", nqQ.toString());
		
		// named PointNode with decimal and irrational
		PointNode nqr = new PointNode("Try Minecraft", -0.13, Math.PI);
		assertEquals("Try Minecraft(-0.13, 3.14159)", nqr.toString());
		
		// named PointNode with long decimals only
		PointNode nQQ = new PointNode("Wake Up!", 89.00000003, 14.298565645);
		assertEquals("Wake Up!(89, 14.29856)", nQQ.toString());
		
		// named PointNode with long decimal and irrational
		PointNode nQr = new PointNode("EEEEEEEEEE", 6, Math.E);
		assertEquals("EEEEEEEEEE(6, 2.71828)", nQr.toString());
		
		// named PointNode with irrationals only
		PointNode nrr = new PointNode("DefinitelyNotAZipBomb.zip", Math.cbrt(13), Math.log(2));
		assertEquals("DefinitelyNotAZipBomb.zip(2.35133, 0.69314)", nrr.toString());
	}
}
