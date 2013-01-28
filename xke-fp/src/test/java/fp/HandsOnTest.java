package fp;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class HandsOnTest {

	@Test
	public void testFact() {
		assertEquals(1, HandsOn.fact(0));
		assertEquals(1, HandsOn.fact(1));
		assertEquals(120, HandsOn.fact(5));
		assertEquals(3628800, HandsOn.fact(10));
	}

	@Test
	public void testFilter() {
		Iterable<Double> salaries = Arrays.asList(1000.0, 2000.0, 2500.0,
				3000.0, 4000.0);
		Iterable<Double> newSalaries = HandsOn.lessThan(3000.0, salaries);
		assertThat(newSalaries).containsOnly(1000.0, 2000.0, 2500.0);
	}
}
