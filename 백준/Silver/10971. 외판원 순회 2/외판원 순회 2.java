import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static final int MAX = 1000001;
	public static int n;
	public static int[][] arr;
	public static boolean[] vis;
	public static int[] path;
	public static int ans = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	n = Integer.parseInt(br.readLine());
    	
    	arr = new int[n][n];
    	vis = new boolean[n];
    	path = new int[n+1];
    	
    	for(int i=0; i<n; i++) {
    		String[] strings = br.readLine().split(" ");
    		for(int j=0; j<n; j++) {
    			arr[i][j] = Integer.parseInt(strings[j]);
    		}
    	}
    	
    	btk(0);
    	
    	System.out.println(ans);
    	
    }
    
    public static void btk(int k) {
    	if(k==n) {
    		path[n] = path[0];
    		getMinCost();
    		return;
    	}
    	
    	for(int i=0; i<n; i++) {
    		if(vis[i]) continue;
    		vis[i] = true;
    		path[k] = i;
    		btk(k+1);
    		vis[i] = false;
    	}
    }
    
    public static void getMinCost() {
    	int sum = 0;
    	boolean flag = false;
    	for(int i=0; i<n; i++) {
    		int st = path[i];
    		int en = path[i+1];
    		if(arr[st][en] == 0) {
    			flag = true;
    			break;
    		}
    		sum += arr[st][en];
    	}
    	if(!flag) {
			ans = Math.min(ans, sum);
		}
    }
}