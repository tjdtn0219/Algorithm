import java.util.*;

class Pair {
    int join;
    int amount;
    public Pair(int join, int amount) {
        this.join = join;
        this.amount = amount;
    }
}

class Solution {
    
    static final int[] SALES = {10, 20, 30, 40};
    
    int[][] users;
    int[] emoticonCosts;
    int[] answer;
    int n;
    int[] comb;
    PriorityQueue<Pair> pq;
    
    public int[] solution(int[][] users, int[] emoticons) {
        init(users, emoticons);
        solve();
        return answer;
    }
    
    public void solve() {
        dfs(0);
        Pair polled = pq.poll();
        answer[0] = polled.join;
        answer[1] = polled.amount;
    }
    
    public void dfs(int k) {
        if(k == n) {
            purchaseEmoticons();
            return ;
        }
        
        for(int sale : SALES) {
            comb[k] = sale;
            dfs(k+1);
        }
    }
    
    public void purchaseEmoticons() {
        int join = 0;
        int amount = 0;
        for(int[] user : users) {
            int minPercent = user[0];
            int limitAmount = user[1];
            int totalAmount = 0;
            for(int i=0; i<n; i++) {
                if(comb[i] >= minPercent) {
                    int saledPrice = emoticonCosts[i] * (100 - comb[i]) / 100;
                    totalAmount += saledPrice;
                }
            }
            if(totalAmount >= limitAmount) {
                join++;
            } else {
                amount += totalAmount;
            }
        }
        // System.out.println("join : " + join + ", amount : " + amount);
        pq.add(new Pair(join, amount));
    }
    
    public void init(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticonCosts = emoticons;
        this.answer = new int[2];
        this.n = emoticons.length;
        this.comb = new int[n];
        this.pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.join == o2.join) {
                return o2.amount - o1.amount;
            } else {
                return o2.join - o1.join;
            }
        });
    }
}