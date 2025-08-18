import java.util.*;

class Solution {
    
    int n;
    int[] arr;
    int[] sumArr;
    int k;
    int[] answer;
    
    public int[] solution(int[] sequence, int k) {
        init(sequence, k);
        solve();
        return answer;
    }
    
    public void solve() {
        int left = 1;
        int right = 1;
        int minLen = n + 1;
        int idx = 0;
        while(left <= right && right <= n) {
            int sum = 0;
            if(left == right) {
                sum = arr[left];
            } else {
                sum = sumArr[right] - sumArr[left - 1];
            }
            // System.out.println("left : " + (left-1) + ", right : " + (right-1) + ", sum : " + sum);
            if(sum == k) {
                if(right - left + 1 < minLen) {
                    answer[0] = left - 1;
                    answer[1] = right - 1;
                    minLen = right - left + 1;
                }
                left++;
            } else if(sum > k) {
                left++;
            } else {
                right++;
            }
        }
    }
    
    public void init(int[] arr, int k) {
        this.n = arr.length;
        this.arr = new int[n + 1];
        this.sumArr = new int[n + 1];
        this.k = k;
        this.answer = new int[2];
        
        for(int i=0; i<n; i++) {
            this.arr[i+1] = arr[i];
        }
        for(int i=1; i<=n; i++) {
            this.sumArr[i] = this.sumArr[i-1] + this.arr[i];
        }
    }
}