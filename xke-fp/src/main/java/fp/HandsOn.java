package fp;

import java.util.Iterator;

import javax.annotation.Nullable;

import com.google.common.base.Function;
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

	public static Iterable<Double> increaseSalaries(Iterable<Double> salaries,
			final double d) {
		return Iterables.transform(salaries, new Function<Double, Double>() {
			@Override
			@Nullable
			public Double apply(@Nullable Double input) {
				return input * (1 + d);
			}
		});
	}

	public static <T, R> R fold(Iterable<T> values, R init,
			Function<R, Function<T, R>> function) {
		Iterator<T> iterator = values.iterator();
		if (!iterator.hasNext()) {
			return init;
		}
		T next = iterator.next();
		iterator.remove();
		return fold(values, function.apply(init).apply(next), function);
	}

}
