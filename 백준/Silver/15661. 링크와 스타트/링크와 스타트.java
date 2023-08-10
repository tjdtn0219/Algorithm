import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int n;
	public static int team_len;
	public static int[][] arr;
	public static boolean[] team;
	public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    	n = Integer.parseInt(br.readLine());
    	
    	arr = new int[n+1][n+1];
    	team = new boolean[n+1];
    	
    	for(int i=1; i<=n; i++) {
    		String[] strings = br.readLine().split(" ");
    		for(int j=1; j<=n; j++) {
    			arr[i][j] = Integer.parseInt(strings[j-1]);
    		}
    	}
    	
    	for(int i=1; i<=n/2; i++) {
    		team_len = i;
    		btk(0,0);
    	}
    	    	
    	System.out.println(ans);
    	
	}
    
    public static void btk(int k, int li) {	//li는 이전 재귀(k-1)깊이의 i값
    	if(k==(team_len)) {
    		int score_t = getScore(true);	//true 팀
    		int score_f = getScore(false);	//false팀
    		ans = Math.min(ans, Math.abs(score_t - score_f));
//    		if(ans > score_t - score_f) ans = score_t - score_f;
    		
    		return ;
    	}
    	
    	for(int i=li+1; i<=n; i++) {
    		team[i] = true;
    		btk(k+1, i);
    		team[i] = false;
    	}
    }
    
    public static int getScore(boolean flag) {
    	int sum = 0;
    	for(int i=1; i<=n; i++) {
    		for(int j=1; j<=n; j++) {
    			if(i==j) continue;
    			if(team[i]==flag && team[j]==flag) {
    				sum += arr[i][j];
    			}
    		}
    	}
    	return sum;
    }
}