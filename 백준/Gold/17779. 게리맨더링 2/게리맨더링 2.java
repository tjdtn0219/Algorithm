import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;


public class Main {

	static final int INF = Integer.MAX_VALUE;

	int n;
	int[][] arr;
	int[][] board;

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}

	public void solution() {
		input();
		solve();
	}

	public void solve() {

		// makeBoundary(2, 4, 2, 1);

		int answer = INF;
		for(int i=0; i<n-2; i++) {
			for(int j=1; j<n-1; j++) {
				for(int d1=1; d1<n; d1++) {
					for(int d2=1; d2<n; d2++) {
						board = new int[n][n];
						int diff = makeBoundary(i, j, d1, d2);
						if(diff == INF) continue;
						answer = Math.min(answer, diff);
					}
				}
			}
		}

		System.out.println(answer);
	}

	public int makeBoundary(int x, int y, int d1, int d2) {
		// 1번 경계선
		for(int d=0; d<=d1; d++) {
			int nx = x + d;
			int ny = y - d;
			if(!isInner(nx, ny)) return INF;
			board[nx][ny] = 5;
		}

		for(int i=0; i<x + d1; i++) {
			for(int j=0; j<=y; j++) {
				if(board[i][j] == 5) break;
				board[i][j] = 1;
			}
		}

		//2번 경계선
		for(int d=0; d<=d2; d++) {
			int nx = x + d;
			int ny = y + d;
			if(!isInner(nx, ny)) return INF;
			board[nx][ny] = 5;
		}

		for(int i=0; i<=x + d2; i++) {
			for(int j=n-1; j>y; j--) {
				if(board[i][j] > 0) break;
				board[i][j] = 2;
			}
		}

		//3번 경계선
		for(int d=0; d<=d2; d++) {
			int nx = x + d1 + d;
			int ny = y - d1 + d;
			if(!isInner(nx, ny)) return INF;
			board[nx][ny] = 5;
		}

		for(int i=x+d1; i<n; i++) {
			for(int j=0; j<y - d1 + d2; j++) {
				if(board[i][j] > 0) break;
				board[i][j] = 3;
			}
		}

		//4번 경계선
		for(int d=0; d<=d1; d++) {
			int nx = x + d2 + d;
			int ny = y + d2 - d;
			if(!isInner(nx, ny)) return INF;
			board[nx][ny] = 5;
		}

		for(int i=x+d2+1; i<n; i++) {
			for(int j=n-1; j>=0; j--) {
				if(board[i][j] > 0) break;
				board[i][j] = 4;
			}
		}

		int diff = getMinDiff();
		// System.out.println("diff : " + diff);
		// printArr(board);
		return diff;
	}

	public int getMinDiff() {
		HashMap<Integer, Integer> cntMap = new HashMap<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(board[i][j] == 0) {
					board[i][j] = 5;
				}
				cntMap.put(board[i][j], cntMap.getOrDefault(board[i][j], 0) + arr[i][j]);
			}
		}

		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int key : cntMap.keySet()) {
			// System.out.println("key : " + key + ", val : " + cntMap.get(key));
			max = Math.max(max, cntMap.get(key));
			min = Math.min(min, cntMap.get(key));
		}
		return Math.abs(max - min);
	}

	public boolean isInner(int x, int y) {
		return 0<=x && 0<=y && x<n && y<n;
	}

	private void printArr(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public void input() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			board = new int[n][n];
			for(int i=0; i<n; i++) {
				arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
		} catch (Exception e) {
			System.out.println("INPUT ERROR!");
			throw new RuntimeException(e);
		}
	}
}
