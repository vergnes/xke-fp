package fp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HandsOnTest {

	@Test
	public void testFact() {
		assertEquals(1, HandsOn.fact(0));
		assertEquals(1, HandsOn.fact(1));
		assertEquals(120, HandsOn.fact(5));
		assertEquals(3628800, HandsOn.fact(10));
	}
}
