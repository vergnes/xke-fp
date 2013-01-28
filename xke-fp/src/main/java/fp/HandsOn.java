package fp;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class HandsOn {

	public static long fact(int n) {
		return fact(n, 1);
	}

	private static long fact(int n, long acc) {
		if (n == 0) {
			return acc;
		} else {
			return fact(n - 1, n * acc);
		}
	}

	public static Iterable<Double> lessThan(final double threshold,
			Iterable<Double> wages) {

		return Iterables.filter(wages, new Predicate<Double>() {

			@Override
			public boolean apply(@Nullable Double input) {
				return input < threshold;
			}
		});
	}

}
