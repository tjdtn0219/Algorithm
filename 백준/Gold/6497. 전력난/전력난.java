import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int weight;

	Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}

}

public class Main {
	static int[] parent;
	static ArrayList<Edge> edgeList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());

			if (M == 0 && N == 0) {
				break;
			}

			edgeList = new ArrayList<>();

			int cost = 0; // 전체 비용
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());

				edgeList.add(new Edge(x, y, z));
				cost += z;
			}
			Collections.sort(edgeList);

			parent = new int[M];
			for (int i = 0; i < M; i++) {
				parent[i] = i;
			}

			int minCost = 0; // 최소 비용
			for (int i = 0; i < edgeList.size(); i++) {
				Edge edge = edgeList.get(i);

				if (find(edge.start) != find(edge.end)) {
					minCost += edge.weight;
					union(edge.start, edge.end);
				}
			}

			bw.write((cost - minCost) + "\n"); // 절약된 비용
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}

}