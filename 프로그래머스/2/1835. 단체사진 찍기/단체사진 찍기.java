import java.util.*;

class Solution {
    
    static final int N = 8;
    static final char[] FRIENDS = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static int cnt = 0;
    
    int n;
    String[] data;
    HashMap<Character, Integer> posMap;
    boolean[] vis;
    int ans;
    
    public int solution(int n, String[] data) {
        init(n, data);
        solve();
        return ans;
    }
    
    public void solve() {
        dfs(0);
    }
    
    public void print() {
        StringBuilder sb = new StringBuilder();
        for(char c : FRIENDS) {
            sb.append(c).append(" : ").append(posMap.get(c)).append("\n");
        }
        System.out.println(sb);
    }
    
    public void dfs(int k) {
        if(k == N) {
            // if(cnt <= 100) {
            //     print();
            //     cnt++;
            // }          
            boolean flag = true;
            for(String cond : data) {
                char a = cond.charAt(0);
                char b = cond.charAt(2);
                char op = cond.charAt(3);
                int val = cond.charAt(4) - '0';
                int dis = Math.abs(posMap.get(a) - posMap.get(b)) - 1;
                if(op == '=') {
                    if(dis != val) {
                        // if(cnt <= 100) {
                        //     System.out.println("a : " + a + ", b : " + b + ", op : " + op + ", dis : " + dis + ", val : " + val);
                        //     cnt++;
                        // }
                        
                        flag = false;
                        break;
                    }
                } else if(op == '>') {
                    if(dis <= val) {
                        flag = false;
                        break;
                    }
                } else if(op == '<') {
                    if(dis >= val) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) {
                ans++;
            }
            return ;
        }
        
        for(int i=0; i<N; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            posMap.put(FRIENDS[i], k);
            dfs(k+1);
            vis[i] = false;
        }
    }
    
    public void init(int n, String[] data) {
        this.n = n;
        this.data = data;
        this.posMap = new HashMap<>();
        this.vis = new boolean[N];
        this.ans = 0;
    }
}