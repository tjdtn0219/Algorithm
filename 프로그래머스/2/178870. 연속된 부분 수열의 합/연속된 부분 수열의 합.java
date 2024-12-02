import java.util.*;

class Solution {
    
    int[] arr;
    int[] sumArr;
    int n;
    int k;
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
            int sum;
            if(left == 0) sum = sumArr[right];
            else sum = sumArr[right] - sumArr[left-1];
            if(sum == k) {
                if(right - left < min) {
                    min = right - left;
                    ans[0] = left;
                    ans[1] = right;
                }
                right++;
            } else if(sum > k) {
                left++;
            } else {
                right++;
            }
        }
    }
    
    public void init(int[] arr, int k) {
        this.arr = arr;
        this.n = arr.length;
        this.sumArr = new int[n];
        sumArr[0] = arr[0];
        for(int i=1; i<n; i++) {
            sumArr[i] = sumArr[i-1] + arr[i];
        }
        this.k = k;
        this.ans = new int[2];
    }
}