import java.util.*;

class Solution {
    
    static final int MOD = 10007;
    
    public int solution(int n, int[] tops) {
        
        int[] a = new int[n]; // a[k]: k번째 아래 방향 정삼각형을 3번 방법으로 덮은 경우의 수
        int[] b = new int[n]; // b[k]: k번째 아래 방향 정삼각형을 1, 2, 4번 방법으로 덮은 경우의 수

        a[0] = 1;
        b[0] = tops[0] == 1 ? 3 : 2;

        for (int k = 1; k < n; k++) {
            if (tops[k] == 1) {
                a[k] = (a[k - 1] + b[k - 1]) % MOD;
                b[k] = (a[k - 1] * 2 + b[k - 1] * 3) % MOD;
            } else {
                a[k] = (a[k - 1] + b[k - 1]) % MOD;
                b[k] = (a[k - 1] + b[k - 1] * 2) % MOD;
            }
        }

        return (a[n - 1] + b[n - 1]) % MOD;
    }
    
}