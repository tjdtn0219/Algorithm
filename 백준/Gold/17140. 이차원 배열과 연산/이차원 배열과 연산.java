import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Node {
	int num;
	int cnt;
	public Node(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
}

public class Main {
	
	int answer;
	int r, c, k;
	int[][] arr;

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}

	public void solution() {
		input();
		solve();
	}

	public void solve() {
		int time = 0;
		boolean flag = false;
		// System.out.println("r, c, k : " + r + ", " + c + ", " + k);
		while(time <= 100) {
			// System.out.println("time : " + time + ", arr[r][c] : " + arr[r][c]);
			if(!isInner(r, c)) {
				operate();
				time++;
				continue;
			}
			if(arr[r][c] == k) {
				flag = true;
				break;
			}
			operate();
			time++;
		}

		if(!flag) {
			answer = -1;
		} else {
			answer = time;
		}
		System.out.println(answer);
	}

	public boolean isInner(int x, int y) {
		int n = arr.length;
		int m = arr[0].length;
		return 0<=x && 0<=y && x<n && y<m;
	}

	public void operate() {
		int n = arr.length;
		int m = arr[0].length;
		if(n >= m) {
			// System.out.println("operateR");
			operateR();
		} else {
			// System.out.println("operateC");
			operateC();
		}
		resetArr();
	}

	public void resetArr() {
		int n = Math.min(arr.length, 100);
		int m = Math.min(arr[0].length, 100);
		int[][] tmp = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		arr = tmp;
	}

	public void operateR() {
		int n = arr.length;
		int maxLen = -1;
		HashMap<Integer, List<Node>> arrMap = new HashMap<>();
		for(int i=0; i<n; i++) {
			List<Node> nodeList = getNodeList(arr[i]);
			maxLen = Math.max(maxLen, nodeList.size() * 2);
			arrMap.put(i, nodeList);
		}
		int[][] tmp = new int[n][maxLen];
		for(int i=0; i<n; i++) {
			List<Node> nodeList = arrMap.get(i);
			// System.out.println("size : " + nodeList.size());
			for(int j=0; j<nodeList.size(); j++) {
				tmp[i][j*2] = nodeList.get(j).num;
				tmp[i][j*2+1] = nodeList.get(j).cnt;
			}
		}

		// printArr(tmp);
		arr = tmp;
	}

	public void operateC() {
		int n = arr.length;
		int m = arr[0].length;
		int maxLen = -1;
		HashMap<Integer, List<Node>> arrMap = new HashMap<>();
		for(int j=0; j<m; j++) {
			int[] tmpArr = new int[n];
			for(int i=0; i<n; i++) {
				tmpArr[i] = arr[i][j];
			}
			List<Node> nodeList = getNodeList(tmpArr);
			maxLen = Math.max(maxLen, nodeList.size() * 2);
			arrMap.put(j, nodeList);
		}
		int[][] tmp = new int[maxLen][m];
		for(int j=0; j<m; j++) {
			List<Node> nodeList = arrMap.get(j);
			for(int i=0; i<nodeList.size(); i++) {
				// System.out.println("maxLen : " + maxLen + " , i : " + i);
				tmp[i*2][j] = nodeList.get(i).num;
				tmp[i*2 + 1][j] = nodeList.get(i).cnt;
			}
		}

		// printArr(tmp);
		arr = tmp;
	}

	private void printArr(int[][] arr) {
		int n = arr.length;
		int m = arr[0].length;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public List<Node> getNodeList(int[] arr) {
		HashMap<Integer, Integer> cntMap = new HashMap<>();
		for(int num : arr) {
			if(num == 0) continue;
			cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
		}
		List<Node> nodeList = new ArrayList<>();
		for(int key : cntMap.keySet()) {
			nodeList.add(new Node(key, cntMap.get(key)));
		}
		Collections.sort(nodeList, (o1, o2) -> {
			if(o1.cnt == o2.cnt) {
				return o1.num - o2.num;
			} else {
				return o1.cnt - o2.cnt;
			}
		});
		return nodeList;
	}

	public void input() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] r_c_k = br.readLine().split(" ");
			r = Integer.parseInt(r_c_k[0]) - 1;
			c = Integer.parseInt(r_c_k[1]) - 1;
			k = Integer.parseInt(r_c_k[2]);
			arr = new int[3][3];
			for(int i=0; i<3; i++) {
				arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
		} catch (Exception e) {
			System.out.println("INPUT ERROR!");
			throw new RuntimeException(e);
		}
	}
}
