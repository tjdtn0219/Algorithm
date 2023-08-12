import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static final int MAX = 101*100000;
	
    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int n = Integer.parseInt(br.readLine());
    	int m = Integer.parseInt(br.readLine());
    	
    	int[][] dis = new int[n+1][n+1];
    	int[][] nxt = new int[n+1][n+1];
    	//nxt[i][j]는 i에서 j로 가기 위해 처음으로 가야할 행선지
    	
    	for(int i=1; i<=n; i++) {
    		for(int j=1; j<=n; j++) {
    			if(i!=j) dis[i][j] = MAX;
    		}
    	}
    	
    	for(int i=0; i<m; i++) {
    		String[] strings = br.readLine().split(" ");
    		int s = Integer.parseInt(strings[0]);
    		int t = Integer.parseInt(strings[1]);
    		int cost = Integer.parseInt(strings[2]);
    		
    		dis[s][t] = Math.min(cost, dis[s][t]);
    		nxt[s][t] = t;
    	}
    	
    	for(int k=1; k<=n; k++) {
	    	for(int i=1; i<=n; i++) {
	    		for(int j=1; j<=n; j++) {
	    			if(dis[i][k]+dis[k][j] < dis[i][j]) {
	    				dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
	    				nxt[i][j] = nxt[i][k];	//i->j로 가기 위해 가야할 첫 행선지는 i->k로 가기위해 처음으로 가야할 행선지이다
//	    				nxt[i][j] = k;		//이거 아님 주의!
	    			}
	    		}
	    	}
    	}
    	
    	/* ======출력====== */
    	for(int i=1; i<=n; i++) {
    		for(int j=1; j<=n; j++) {
    			if(dis[i][j] == MAX) sb.append("0 ");
    			else sb.append(dis[i][j] + " ");
    		}
    		sb.append("\n");
    	}
    	for(int i=1; i<=n; i++) {
    		for(int j=1; j<=n; j++) {
    			if(dis[i][j]==0 || dis[i][j]==MAX) {
    				sb.append("0\n");
    				continue;
    			}
    			ArrayList<Integer> list = new ArrayList<>();
    			int st = i;
    			while(st!=j) {
    				list.add(st);
    				st = nxt[st][j];
    			}
    			list.add(j);
    			sb.append(list.size() + " ");
    			for(Integer num : list)
    				sb.append(num + " ");
    			sb.append("\n");
    		}
    			
    	}
    	
    	System.out.print(sb.toString());
    	
    	
    }
}