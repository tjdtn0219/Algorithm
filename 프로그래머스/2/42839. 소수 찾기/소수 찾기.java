import java.util.*;

class Solution {
    
    int[] numbers;
    int n;
    int[] comb;
    boolean[] vis;
    HashSet<Integer> primeNumSet;
    int limit;
    
    public int solution(String numbers) {
        init(numbers);
        return solve();
    }
    
    public void init(String num) {
        n = num.length();
        numbers = new int[n];
        for(int i=0; i<n; i++) {
            numbers[i] = num.charAt(i) - '0';
        }
        comb = new int[n];
        vis = new boolean[n];
        primeNumSet = new HashSet<>();
        limit = 0;
    }
    
    public int solve() {
        for(int limit=1; limit<=n; limit++) {
            this.limit = limit;
            makeComb(0, limit);
        }
        // makeComb(0, 1);
        System.out.println("ans : " + primeNumSet.size());
        return primeNumSet.size();
    }
    
    public void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int num : comb) {
            sb.append(num + " ");
        }
        System.out.println(sb);
    }
    
    public void makeComb(int k, int limit) {
        if(k == limit) {
            // System.out.println("k : " + k + " ,limit : " + limit);
            // printComb();
            System.out.println("num : " + makeNumber());
            int num = makeNumber();
            if(isPrime(num)) {
                primeNumSet.add(num);
                // System.out.println("pNum : " + num);
            }
            return ;
        }
        
        for(int i=0; i<n; i++) {
            if(vis[i]) continue;
            comb[k] = i;
            vis[i] = true;
            makeComb(k+1, limit);
            vis[i] = false;
        }
    }
    
    public int makeNumber() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<limit; i++) {
            sb.append(numbers[comb[i]]);
        }
        return Integer.parseInt(sb.toString());
    }
    
    public boolean isPrime(int num) {
        if(num < 2) return false;
        int sqrt = (int) Math.sqrt(num);
        for(int i=2; i<=sqrt; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}