import java.util.*;
import java.io.*;


public class Main {
	
	public static final int MAX = 100000;
	
    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String[] strings = br.readLine().split(" ");
    	
    	int n = Integer.parseInt(strings[0]);
    	int m = Integer.parseInt(strings[1]);
    	int r = Integer.parseInt(strings[2]);
    	
    	int[] items = new int[n+1];
    	int[][] dis = new int[n+1][n+1];
    	
    	strings = br.readLine().split(" ");
    	for(int i=1; i<=n; i++) items[i] = Integer.parseInt(strings[i-1]);
    	
    	for(int i=1; i<n+1; i++) {
    		for(int j=1; j<n+1; j++) {
    			if(i==j) continue;
    			dis[i][j] = MAX;
    		}
    	}
    	
    	for(int i=0; i<r; i++) {
    		strings = br.readLine().split(" ");
    		int a = Integer.parseInt(strings[0]);
    		int b = Integer.parseInt(strings[1]);
    		int c = Integer.parseInt(strings[2]);
    		
    		dis[a][b] = c;
    		dis[b][a] = c;
    	}
    	
    	for(int k=1; k<=n; k++) {
    		for(int i=1; i<=n; i++) {
    			for(int j=1; j<=n; j++) {
    				if(dis[i][k] + dis[k][j] < dis[i][j])
    					dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);    			}
    		}
    	}
    	
    	int ans = 0;
    	for(int i=1; i<=n; i++) {
    		int sum = items[i];
    		for(int j=1; j<=n; j++) {
    			if(i==j) continue;
    			if(dis[i][j] <= m) sum += items[j];
    		}
    		
    		ans = Math.max(ans, sum);
    	}
    	
    	System.out.println(ans);
    	
    	
	}
}