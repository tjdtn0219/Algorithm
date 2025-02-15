import java.util.*;

class Solution {
    
    int[] A, B;
    int answer;
    
    public int solution(int[] arrayA, int[] arrayB) {
        init(arrayA, arrayB);
        solve();
        return answer;
    }
    
    public void solve() {
        int gcdA = A[0];
        int gcdB = B[0];
        for(int i=1; i<A.length; i++) {
            gcdA = gcd(gcdA, A[i]);
            gcdB = gcd(gcdB, B[i]);
        }
        
        // System.out.println("gcd A, B : " + gcdA + ", " + gcdB);
        
        if(!isDivide(A, gcdB)) {
            answer = Math.max(answer, gcdB);
        }
        
        if(!isDivide(B, gcdA)) {
            answer = Math.max(answer, gcdA);
        }
        
    }
    
    public boolean isDivide(int[] arr, int num) {
        for(int n : arr) {
            if(n % num == 0) return true;
        }
        return false;
    }
    
    public int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    
    public void init(int[] A, int[] B) {
        this.A = A;
        this.B = B;
    }
}