import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String[] strings = br.readLine().split(" ");
    	
    	int v  = Integer.parseInt(strings[0]);
    	int e = Integer.parseInt(strings[1]);
    	int st = Integer.parseInt(br.readLine());

    	ArrayList<Pair>[] graph = new ArrayList[v+1];
    	for(int i=1; i<=v; i++) graph[i] = new ArrayList<>();
    	
    	int[] dis = new int[v+1];
    	for(int i=1; i<=v; i++) dis[i] = Integer.MAX_VALUE;
    	PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)->o1.w-o2.w);
    	
    	dis[st] = 0;
    	pq.add(new Pair(st, 0));
    	for(int i=0; i<e; i++) {
    		strings = br.readLine().split(" ");
    		int v1 = Integer.parseInt(strings[0]);
    		int v2 = Integer.parseInt(strings[1]);
    		int w = Integer.parseInt(strings[2]);
    		
    		graph[v1].add(new Pair(v2,w));
    	}
    	
    	while(!pq.isEmpty()) {
    		Pair popped = pq.poll();
    		for(Pair adj : graph[popped.v]) {
    			if(dis[popped.v] + adj.w < dis[adj.v]) {
    				dis[adj.v] = dis[popped.v] + adj.w;
    				pq.add(new Pair(adj.v, dis[adj.v]));
    			}
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i=1; i<=v; i++) {
    		if(dis[i] == Integer.MAX_VALUE) sb.append("INF\n");
    		else sb.append(dis[i] + "\n");
    	}
    	System.out.println(sb.toString());
    	
    	
    }
}

class Pair {
	int v;
	int w;
	
	public Pair(int v, int w) {
		this.v = v;
		this.w = w;
	}
}