import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	int n;
	int[] arr;
	int b, c;

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}

	public void solution() {
		input();
		solve();
	}

	public void solve() {
		long total = n;
        for(int a : arr) {
			long cnt = getMinCnt(a);
			// System.out.println("cnt : " + cnt);
			total += cnt;
		}
		System.out.println(total);

		// long sum = n;
        // for (int i = 0; i < n; i++) {
        //     int cur = arr[i] - b;
        //     if (cur <= 0) continue;
        //     sum+=cur/c + (cur%c==0?0:1);
        // }
        // System.out.println(sum);
    }

	public long getMinCnt(int num) {
		long cnt = 0L;
		num -= b;
		if(num <= 0) return 0L;
		if(num % c == 0) {
			cnt += num / c;
		} else {
			cnt += num / c + 1;
		}
		return cnt;
	}

	public void input() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			String[] b_c = br.readLine().split(" ");
			b = Integer.parseInt(b_c[0]);
			c = Integer.parseInt(b_c[1]);
		} catch (Exception e) {
			System.out.println("INPUT ERROR!");
			throw new RuntimeException(e);
		}
	}
}
