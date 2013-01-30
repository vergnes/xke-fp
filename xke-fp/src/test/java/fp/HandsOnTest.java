package fp;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class HandsOnTest {

	private static final Function<Integer, Function<Integer, Integer>> sum = new Function<Integer, Function<Integer, Integer>>() {
		@Override
		@Nullable
		public Function<Integer, Integer> apply(@Nullable final Integer input1) {
			return new Function<Integer, Integer>() {
				@Override
				@Nullable
				public Integer apply(@Nullable Integer input2) {
					return input1 + input2;
				}
			};
		}

	};

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

	@Test
	public void testMap() {
		Iterable<Double> salaries = Arrays.asList(1000.0, 2000.0, 2500.0,
				3000.0, 4000.0);
		Iterable<Double> newSalaries = HandsOn.increaseSalaries(salaries, 0.02);
		assertThat(newSalaries).containsOnly(1020.0, 2040.0, 2550.0, 3060.0,
				4080.0);
	}

	@Test
	public void testFold() {
		List<Integer> values = Arrays.asList(1, 2, 3);
		Integer result = HandsOn.fold(values, 0, sum);
		assertThat(result).isEqualTo(6);
	}

	@Test
	public void testZip() {
		Iterable<Integer> result = HandsOn.zipWith(sum, Arrays.asList(1, 2),
				Arrays.asList(3, 4, 6));
		assertThat(result).containsOnly(4, 6);
	}

	@Test
	public void testEnumPositiveInts() {
		Iterable<Integer> fiveInts = Iterables.limit(
				HandsOn.enumPositiveInts(), 5);
		assertThat(fiveInts).containsOnly(0, 1, 2, 3, 4);

		Iterable<Integer> fiveNextInts = Iterables.limit(
				Iterables.skip(HandsOn.enumPositiveInts(), 5), 5);
		assertThat(fiveNextInts).containsOnly(5, 6, 7, 8, 9);
	}

	@Test
	public void testFibs() {
		assertThat(Iterables.limit(HandsOn.fibs(), 8)).containsOnly(0, 1, 1, 2,
				3, 5, 8, 13);
	}
}
