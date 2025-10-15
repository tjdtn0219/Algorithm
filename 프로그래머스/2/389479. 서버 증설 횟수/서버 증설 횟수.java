import java.util.*;

class Solution {
    
    static final int HOUR = 24;
    
    int answer;
    int[] arr;
    int m, k;
    Queue<Integer> q;
    
    public int solution(int[] players, int m, int k) {
        init(players, m, k);
        solve();
        return answer;
    }
    
    public void solve() {
        for(int i=0; i<HOUR; i++) {
            int playerCnt = arr[i];
            // System.out.println("[현재 시간] : " + i + ", 사용자 수 : " + playerCnt);
            while(!q.isEmpty()) {
                if(i >= q.peek()) {
                    q.poll();
                } else {
                    break;
                }
            }
            int serverCnt = q.size() + 1;
            // System.out.println("현재 서버 수 : " + serverCnt);
            if(playerCnt >= serverCnt * m) {
                int diff = playerCnt - (serverCnt * m);
                int needCnt = diff / m + 1;
                answer += needCnt;
                // System.out.println("초과 사용자 수 : " + diff + ", 필요 서버 수 : " + needCnt);
                for(int j=0; j<needCnt; j++) {
                    q.add(i + k);
                }
            }
        }
    }
    
    public void init(int[] players, int m, int k) {
        this.arr = players;
        this.m = m;
        this.k = k;
        this.q = new LinkedList<>();
    }
}