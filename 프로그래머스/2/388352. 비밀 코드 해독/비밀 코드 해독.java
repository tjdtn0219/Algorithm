import java.util.*;

class Solution {
    
    int answer;
    int n;
    HashSet<Integer>[] q;
    int[] ans;
    int[] comb;
    
    public int solution(int n, int[][] q, int[] ans) {
        init(n, q, ans);
        solve();
        return answer;
    }
    
    public void solve() {
        makeComb(0, 0);
    }
    
    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int num : comb) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void makeComb(int k, int last) {
        if(k == 5) {
            // if(comb[0] == 3) {
            //     printComb();
            //     boolean flag = isOkay();
            //     System.out.println("flag : " + flag);
            // }
            if(isOkay()) {
                answer++;
            }
            return ;
        }
        
        for(int i=last+1; i<=n; i++) {
            comb[k] = i;
            makeComb(k+1, i);
        }
    }
    
    public boolean isOkay() {
        for(int i=0; i<q.length; i++) {
            int cnt = 0;
            for(int j=0; j<5; j++) {
                if(q[i].contains(comb[j])) cnt++;
            }
            if(cnt != ans[i]) {
                return false;
            }
        }
        return true;
    }
    
    public void init(int n, int[][] q, int[] ans) {
        this.n = n;
        
        this.q = new HashSet[q.length];
        for(int i=0; i<q.length; i++) {
            this.q[i] = new HashSet<>();
            for(int j=0; j<5; j++) {
                this.q[i].add(q[i][j]);
            }
        }
        this.ans = ans;
        this.comb = new int[5];
    }
}