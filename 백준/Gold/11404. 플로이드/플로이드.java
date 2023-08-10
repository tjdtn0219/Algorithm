import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static final int MAX = 100*100000;


    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int n = Integer.parseInt(br.readLine());
    	int[][] graph = new int[n+1][n+1];
    	
    	int m = Integer.parseInt(br.readLine());
    	
    	for(int i=1; i<n+1; i++) {
    		for(int j=1; j<n+1; j++) {
    			if(i!=j) graph[i][j] = MAX;
    		}
    	}
    	
    	for(int i=0; i<m; i++) {
    		String[] strings = br.readLine().split(" ");
    		int v1 = Integer.parseInt(strings[0]);
    		int v2 = Integer.parseInt(strings[1]);
    		int cost = Integer.parseInt(strings[2]);
    		graph[v1][v2] = Math.min(graph[v1][v2], cost);
    	}
    	
    	for(int k=1; k<n+1; k++) {
    		for(int i=1; i<n+1; i++) {
    			if(i==k) continue;
    			for(int j=1; j<n+1; j++) {
    				if(i==j || k==j) continue;
    				graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
    			}
    		}
    	}
    	
    	for(int i=1; i<n+1; i++) {
    		for(int j=1; j<n+1; j++) {
    			if(graph[i][j]==MAX) sb.append("0 ");
    			else sb.append(graph[i][j] + " ");
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    	
    }
}