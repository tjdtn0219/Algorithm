import java.util.*;

class Node {
    int cnt1;
    int cnt2;
    public Node(int cnt1, int cnt2) {
        this.cnt1 = cnt1;
        this.cnt2 = cnt2;
    }
}

class Solution {
    
    static final int N = 20;
    static final int MAX = 100_000;
    static final int LIMIT = 1;
    static int limit = MAX;
    
    int target;
    int[] answer;
    int[][] dp;
    // int[] dp;
    PriorityQueue<Node> pq;
    
    public int[] solution(int target) {
        init(target);
        solve();
        return answer;
    }
    
    public void solve() {
        answer = throwDart(target);
    }
    
    int[] throwDart(int n) {
        if(dp[n][0] == MAX) {
            if(n >= 50) { // 불 맞추기
                int[] temp = throwDart(n - 50);
                if((temp[0] + 1 < dp[n][0]) || (temp[0] + 1 == dp[n][0] && temp[1] + 1 > dp[n][1])) {
                    dp[n][0] = 1 + temp[0];
                    dp[n][1] = 1 + temp[1];
                }
            }
            
            int start = n >= 20 ? 20 : n;
            for(int i=start;i>=1;i--) {
                for(int j=1;j<=3;j++) { // 싱글, 더블, 트리플 
                    if(n >= i * j) {
                        int[] temp = throwDart(n - i * j);
                        int cnt = j == 1 ? 1 : 0; // 싱글일 경우 카운트하기 
                        if((temp[0] + 1 < dp[n][0]) || (temp[0] + 1 == dp[n][0] && temp[1] + cnt > dp[n][1])) {
                            dp[n][0] = 1 + temp[0];
                            dp[n][1] = cnt + temp[1];
                        }
                    }
                }
            }
        }
        
        return dp[n];
    }
    
    
    public void init(int target) {
        this.target = target;
        this.answer = new int[2];
        dp = new int[MAX + 1][2];
        for(int i=1; i<=MAX; i++) {
            Arrays.fill(dp[i], MAX);
        }
    }
}