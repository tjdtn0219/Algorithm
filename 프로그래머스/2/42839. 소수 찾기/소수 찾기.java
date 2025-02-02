import java.util.*;

class Solution {
    
    int n;
    int[] nums;
    boolean[] vis;
    HashSet<Integer> set;
    
    public int solution(String numbers) {
        int answer = 0;
        init(numbers);
        return solve();
        // return answer;
    }
    
    public int solve() {
        int ans = 0;
        for(int i=1; i<=n; i++) {
            dfs(i, 0, "");
        }
        for(int n : set) {
            // System.out.println("n : " + n);
            if(isPrime(n)) ans++;
        }
        return ans;
    }
    
    public boolean isPrime(int num) {
        if(num <= 1) return false;
        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
    
    public void dfs(int limit, int k, String s) {
        if(k == limit) {
            // System.out.println("s : " + s);
            set.add(Integer.parseInt(s));
            return ;
        }
        
        for(int i=0; i<n; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            dfs(limit, k+1, s + nums[i]);
            vis[i] = false;
        }
    }
    
    public void init(String numbers) {
        this.n = numbers.length();
        this.nums = new int[n];
        this.vis = new boolean[n];
        this.set = new HashSet<>();
        for(int i=0; i<n; i++) {
            nums[i] = numbers.charAt(i) - '0';
        }
    }
}