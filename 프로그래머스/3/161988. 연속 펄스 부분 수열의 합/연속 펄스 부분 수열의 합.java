import java.util.*;

class Solution {
    
    int n;
    int[] a;
    int[] b;
    long[] sumA;
    long[] sumB;
    
    public long solution(int[] sequence) {
        long answer = 0;
        init(sequence);
        return solve();
    }
    
    public long solve() {
        long res = 0;
        sumA[0] = a[0];
        sumB[0] = b[0];
        
        res = Math.max(a[0], b[0]);
        
        for(int i=1; i<n; i++) {
            sumA[i] = Math.max(sumA[i-1] + a[i], a[i]);
            sumB[i] = Math.max(sumB[i-1] + b[i], b[i]);
            long max = Math.max(sumA[i], sumB[i]);
            res = Math.max(max, res);
        }
        
        return res;
        
    }
    
    public void init(int[] sequence) {
        this.n = sequence.length;
        this.a = new int[n];
        this.b = new int[n];
        this.sumA = new long[n];
        this.sumB = new long[n];
        int flag = 1;
        for(int i=0; i<n; i++) {
            a[i] = flag * sequence[i];
            flag *= -1;
            b[i] = flag * sequence[i];
        }
    }
}