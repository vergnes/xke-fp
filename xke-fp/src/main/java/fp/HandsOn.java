package fp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.AbstractIterator;
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
		List<T> wrapIterable = wrapIterable(values);
		Iterator<T> iterator = wrapIterable.iterator();
		if (!iterator.hasNext()) {
			return init;
		}
		T next = iterator.next();
		iterator.remove();
		return fold(wrapIterable, function.apply(init).apply(next), function);
	}

	public static <T1, T2, R> Iterable<R> zipWith(
			Function<T1, Function<T2, R>> function, Iterable<T1> list1,
			Iterable<T2> list2) {
		return zipWith(function, list1, list2, new LinkedList<R>());
	}

	private static <T1, T2, R> Iterable<R> zipWith(
			Function<T1, Function<T2, R>> function, Iterable<T1> list1,
			Iterable<T2> list2, List<R> result) {
		List<T1> list1Wrapper = wrapIterable(list1);
		Iterator<T1> i1 = list1Wrapper.iterator();
		List<T2> list2Wrapper = wrapIterable(list2);
		Iterator<T2> i2 = list2Wrapper.iterator();
		if (!i1.hasNext() || !i2.hasNext()) {
			return result;
		}
		T1 value1 = i1.next();
		i1.remove();
		T2 value2 = i2.next();
		i2.remove();
		result.add(function.apply(value1).apply(value2));
		return zipWith(function, list1Wrapper, list2Wrapper, result);
	}

	private static <T> List<T> wrapIterable(Iterable<T> list) {
		List<T> listWrapper = new LinkedList<T>();
		Iterables.addAll(listWrapper, list);
		return listWrapper;
	}

	public static Iterable<Integer> enumPositiveInts() {
		return new Iterable<Integer>() {

			@Override
			public Iterator<Integer> iterator() {
				return new AbstractIterator<Integer>() {
					private int i = 0;

					@Override
					protected Integer computeNext() {
						return i++;
					}
				};
			}
		};
	}

	public static Iterable<Integer> fibs() {
		return new Iterable<Integer>() {

			@Override
			public Iterator<Integer> iterator() {
				return new AbstractIterator<Integer>() {

					private int a = 0;
					private int b = 1;

					@Override
					protected Integer computeNext() {
						int result = a;
						a += b;
						b = result;
						return result;
					}
				};
			}
		};
	}
}
