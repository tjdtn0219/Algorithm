import java.util.*;

class Solution {
    
    static final int INF = Integer.MAX_VALUE;
    
    int n, m;
    int answer;
    int[] arr;
    int[] dist;
    int[] comb;
    int[] weak;
    
    public int solution(int n, int[] weak, int[] dist) {
        init(n, weak, dist);
        solve();
        return answer;
    }
    
    public void solve() {
        makeComb(0, new boolean[dist.length]);
        if(answer == Integer.MAX_VALUE) answer = -1;
    }
    
    public void makeComb(int k, boolean[] vis) {
        if(k == dist.length) {
            answer = Math.min(answer, getMinCnt());
            return ;
        }
        
        for(int i=0; i<dist.length; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            comb[k] = dist[i];
            makeComb(k+1, vis);
            vis[i] = false;
        }
    }
    
    public int getMinCnt() {
        int cnt = Integer.MAX_VALUE;
        for(int st : weak) {
            int idx = 0;
            for(int i=st; i<st+n; i++) {
                if(arr[i] == 0) continue;
                if(idx >= comb.length) {
                    idx = Integer.MAX_VALUE;
                    break;
                }
                i += comb[idx++];
            }
            cnt = Math.min(cnt, idx);
        }
        
        return cnt;
    }
    
    public void init(int n, int[] weak, int[] dist) {
        this.n = n;
        this.arr = new int[n*2];
        for(int num : weak) {
            arr[num]++;
            arr[num + n]++;
        }
        this.dist = dist;
        this.comb = new int[dist.length];
        this.weak = weak;
        this.answer = Integer.MAX_VALUE;
    }
    
    private void printArr(int[] arr) {
        int n = arr.length;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
}