import java.util.*;

class Solution {
    
    int n, k;
    String s;
    int answer;
    
    public int solution(int n, int k) {
        init(n, k);
        solve();
        return answer;
    }
    
    public void solve() {
        System.out.println("S : " + s);
        String[] splits = s.split("0");
        for(String tmp : splits) {
            if(tmp.isEmpty()) continue;
            // System.out.println("tmp : " + tmp);
            long num = Long.parseLong(tmp);
            if(isPrime(num)) {
                answer++;
            }
        }
    }
    
    public boolean isPrime(long n) {
        if(n == 1) return false;
        for(int i=2; i<=Math.sqrt(n); i++) {
            if(n%i == 0) return false;
        }
        return true;
    }
    
    public void init(int n, int k) {
        this.n = n;
        this.k = k;
        this.s = Long.toString(n, k);
    }
}