import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
		
	public static int n,k;
	public static int ans;
	public static String[] words;
	public static boolean[] vis;

    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String[] strings = br.readLine().split(" ");
    	
    	n = Integer.parseInt(strings[0]);
    	k = Integer.parseInt(strings[1]);
    	
    	words = new String[n];
    	vis = new boolean[26];
    	
    	for(int i=0; i<n; i++) words[i] = br.readLine();
    	
//        for(int i = 0; i < n; i++) {
//            String word = br.readLine();
//            word = word.replace("anta", "");
//            word = word.replace("tica", "");
//            words[i] = word;
//        }
    	
    	String antic = "antic";		//anta tica -> a,n,t,i,c
    	for(int i=0; i<antic.length(); i++) vis[antic.charAt(i)-'a'] = true;
    	
    	if(k<5) ans = 0;
    	else if(k==26) ans = n;
    	else {
    		backtracking(0, 0);
    	}
    	
    	System.out.println(ans);
    	
    }
    
    public static void backtracking(int alpha, int len) {
        if(len == k - 5) {
            int count = 0;
            for(int i = 0; i < n; i++) { //뽑은 알파벳으로 몇개의 단어를 읽을 수 있는지 카운트.
                boolean read = true;
                for(int j = 4; j < words[i].length()-4; j++) {
                    if(!vis[words[i].charAt(j) - 'a']) {
                        read = false;
                        break;
                    }
                }
                if(read) count++;
            }
            ans = Math.max(ans, count);
            return;
        }
        
        for(int i = alpha; i < 26; i++) {
            if(vis[i] == false) {
                vis[i] = true;
                backtracking(i, len + 1);
                vis[i] = false;
            }
        }
    }
    
    public static void btk(int dpt, int li) {
    	if(dpt==k-5) {
    		func();
    		return ;
    	}
    	
    	for(int i=li; i<26; i++) {
    		if(vis[i]) continue;
    		vis[i] = true;
    		btk(dpt+1, li+1);
    		vis[i] = false;
    	}
    }
    
    public static void func() {
    	int cnt = 0;
    	for(String word : words) {
    		boolean flag = true;
    		for(int i=0; i<word.length(); i++) {
    			if(!vis[word.charAt(i)-'a']) flag = false;
    		}
    		if(flag) cnt++;
    	}
    	ans = Math.max(ans, cnt);
    }
    
}