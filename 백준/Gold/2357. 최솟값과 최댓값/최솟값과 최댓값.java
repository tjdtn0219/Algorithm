import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	int n, m;
	int[] arr;
	int[][] queries;
	int[] minTree;
	int[] maxTree;
	int min, max;

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}

	public void solution() {
		input();
		solve();
	}

	public void solve() {
		int size = getTreeSize();
		minTree = new int[size];
		maxTree = new int[size];
		initMinTree(1, n, 1);
		initMaxTree(1, n, 1);

		StringBuilder sb = new StringBuilder();
		for(int[] query : queries) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			int a = query[0];
			int b = query[1];
			findMin(1, n, 1, a, b);
			findMax(1, n, 1, a, b);
			sb.append(min + " " + max + "\n");
		}
		System.out.print(sb);
	}

	public void findMin(int left, int right, int node, int a, int b) {
		if(a > right || b < left) return ;
		if(a <= left && right <= b) {
			min = Math.min(min, minTree[node]);
			return ;
		}

		int mid = (left + right) / 2;
		findMin(left, mid, node*2, a, b);
		findMin(mid+1, right, node*2+1, a, b);
	}

	public void findMax(int left, int right, int node, int a, int b) {
		if(a > right || b < left) return ;
		if(a <= left && right <= b) {
			max = Math.max(max, maxTree[node]);
			return ;
		}

		int mid = (left + right) / 2;
		findMax(left, mid, node*2, a, b);
		findMax(mid+1, right, node*2+1, a, b);
	}


	public void initMinTree(int left, int right, int node) {
		if(left == right) {
			minTree[node] = arr[left];
		} else {
			int mid = (left + right) / 2;
			initMinTree(left, mid, node*2);
			initMinTree(mid+1, right, node*2 + 1);
			if(minTree[node*2] < minTree[node*2+1]) {
				minTree[node] = minTree[node*2];
			}else {
				minTree[node] = minTree[node*2+1];
			}
		}

	}

	public void initMaxTree(int left, int right, int node) {
		if(left == right) {
			maxTree[node] = arr[left];
		} else {
			int mid = (left + right) / 2;
			initMaxTree(left, mid, node*2);
			initMaxTree(mid+1, right, node*2 + 1);
			if(maxTree[node*2] > maxTree[node*2+1]) {
				maxTree[node] = maxTree[node*2];
			}else {
				maxTree[node] = maxTree[node*2+1];
			}
		}

	}

	public int getTreeSize() {
		int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
		return (int) Math.pow(2, h);
	}

	public void input() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] n_m = br.readLine().split(" ");
			n = Integer.parseInt(n_m[0]);
			m = Integer.parseInt(n_m[1]);
			arr = new int[n+1];
			queries = new int[m][2];
			for(int i=1; i<=n; i++) {
				arr[i] = Integer.parseInt(br.readLine());
			}
			for(int i=0; i<m; i++) {
				String[] a_b = br.readLine().split(" ");
				queries[i][0] = Integer.parseInt(a_b[0]);
				queries[i][1] = Integer.parseInt(a_b[1]);
			}
		} catch (Exception e) {
			System.out.println("INPUT ERROR!");
			throw new RuntimeException(e);
		}
	}
}
