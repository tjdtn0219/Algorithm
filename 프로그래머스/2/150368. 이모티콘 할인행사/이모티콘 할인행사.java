import java.util.*;

class Pair {
    int join;
    int sum;
    public Pair(int join, int sum) {
        this.join = join;
        this.sum = sum;
    }
}

class Solution {
    
    static final int[] SALES = {10, 20, 30, 40};
    static final double[] DISCOUNTS = {0.9, 0.8, 0.7, 0.6};
    
    int n, m;
    int[][] users;
    int[] emoticons;
    PriorityQueue<Pair> pq;
    int[] ans;
    
    public int[] solution(int[][] users, int[] emoticons) {
        init(users, emoticons);
        solve();
        return ans;
    }
    
    public void solve() {
        dfs(0, new int[m]);
        ans[0] = pq.peek().join;
        ans[1] = pq.peek().sum;
    }
    
    public void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int num : arr) {
            sb.append(SALES[num]).append(" ");
        }
        System.out.println("Comb : " + sb);
    }
    
    public void dfs(int k, int[] comb) {
        if(k == m) {
            int joinNum = 0;
            int totalSum = 0;
            // printArr(comb);
            for(int[] user : users) {
                int sum = 0;
                for(int i=0; i<m; i++) {
                    // System.out.println("SALE : " + SALES[comb[i]] + ", USER : " + user[0]);
                    if(SALES[comb[i]] < user[0]) continue;
                    // 구매
                    sum += emoticons[i] * DISCOUNTS[comb[i]];
                }
                // System.out.println("sum : " + sum);
                if(sum >= user[1]) {
                    //플러스 가입
                    joinNum++;
                } else {
                    //이모티콘 구입
                    totalSum += sum;
                }
            }
            pq.add(new Pair(joinNum, totalSum));
            // System.out.println("join : " + joinNum + ", totalSum : " + totalSum);
            return ;
        }
        
        for(int i=0; i<4; i++) {
            comb[k] = i;
            dfs(k+1, comb);
        }
        
    }
    
    public void init(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.n = users.length;
        this.m = emoticons.length;
        this.pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.join == o2.join) {
                return o2.sum - o1.sum;
            }
            return o2.join - o1.join;
        });
        this.ans = new int[2];
    }
}