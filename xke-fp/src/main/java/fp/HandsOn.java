package fp;

public class HandsOn {

	public static long fact(int n) {
		if (n == 0) {
			return 1;
		} else {
			return fact(n - 1) * n;
		}
	}

}
