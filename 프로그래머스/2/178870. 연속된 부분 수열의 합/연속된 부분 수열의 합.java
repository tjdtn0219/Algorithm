import java.util.*;

class Solution {
    
    int n;
    int[] sequence;
    int k;
    int[] sums;
    int[] ans;
    
    public int[] solution(int[] sequence, int k) {
        init(sequence, k);
        solve();
        return ans;
    }
    
    public void solve() {
        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        while(left <= right && right < n) {
            int sum = 0;
            if(left == 0) sum = sums[right];
            else sum = sums[right] - sums[left-1];
            
            if(sum == k) {
                if(right - left < min) {
                    min = right - left;
                    ans[0] = left;
                    ans[1] = right;
                }
                right++;
            } else if(sum > k) {
                sum = left++;
            } else {
                right++;
            }
        }
    }
    
    public void init(int[] sequence, int k) {
        this.n = sequence.length;
        this.sequence = sequence;
        this.k = k;
        this.sums = new int[n];
        sums[0] = sequence[0];
        for(int i=1; i<n; i++) {
            sums[i] = sums[i-1] + sequence[i];
        }
        this.ans = new int[2];
    }
}