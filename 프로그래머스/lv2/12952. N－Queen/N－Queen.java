import java.util.*;

class Solution {
    
    public static int[] queens;
    public static int n;
    public static int ans;
    
    public int solution(int num) {
        
        n = num;
        queens = new int[n];
        
        btk(0);
        
        return ans;
    }
    
    public void btk(int k) {
        if(k==n) {
            ans++;
            return ;
        }
        
        for(int j=0; j<n; j++) {
            if(!isAttack(k,j)) {
                queens[k] = j;
                btk(k+1);
            }
        }
    }
    
    public boolean isAttack(int k, int j) {        
        for(int i=0; i<k; i++) {
            if(queens[i] == j) return true;
            if((i + queens[i]) == (k + j)) return true;
            if((k-i) == (j-queens[i])) return true;
        }
        return false;
    }
    
}
