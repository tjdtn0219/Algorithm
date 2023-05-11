import java.util.*;
import java.io.*;
/**
 * @author HEESOO
 *
 */
class Main {
	static PriorityQueue<Node> pq;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		pq=new PriorityQueue<>();
		int v=Integer.parseInt(st.nextToken());
		int e=Integer.parseInt(st.nextToken());
		parent=new int[v+1];
		for(int i=1;i<=v;i++) parent[i]=i; // 부모는 나 자신으로 초기화
		
		for(int i=0;i<e;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			pq.offer(new Node(a, b, c)); // 큐에 다 넣기
		}
		
		System.out.println(solve());
	}
	
	public static int solve() {
		int sum=0; // 가중치 누적 합 계산
		
		while(!pq.isEmpty()) {
			Node node=pq.poll();
			// start, end의 부모 찾기
			int parentS=find(node.start);
			int parentE=find(node.end);
			// 부모가 다르다면(연결되어있지 않다면)
			if(parentS!=parentE) {
				union(parentS, parentE); // 둘을 연결
				sum+=node.weight; // 가중치 계산
			}
		}
		
		return sum;
	}
	
	public static int find(int x) {
		if(parent[x]==x) return x;
		else return parent[x]=find(parent[x]);
	}
	
	public static void union(int a, int b) {
		parent[a]=b;
	}
}

class Node implements Comparable<Node>{
	int start;
	int end;
	int weight;
	
	public Node(int s, int e, int w) {
		this.start=s;
		this.end=e;
		this.weight=w;
	}
	
	@Override
	public int compareTo(Node n) { // 가중치 기준 오름차순 정렬
		return this.weight-n.weight;
	}
}