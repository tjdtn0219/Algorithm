import java.util.*;

class Solution {
    
    int answer;
    int n;
    int[] arr;
    int[] sums;
    
    public int solution(int[] cookie) {
        init(cookie);
        solve();
        return answer;
    }
    
    public void solve() {
        printArr(arr);
        printArr(sums);
        
        for(int i=1; i<n; i++) {
            int leftSum = arr[i];
            int rightSum = arr[i+1];
            
            int left = i;
            int right = i+1;
            
            while(1 <= left && right <= n) {
                if(leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }
                
                if(leftSum <= rightSum && left > 1) {
                    leftSum += arr[--left];
                } else if(rightSum < leftSum && right < n) {
                    rightSum += arr[++right];
                } else {
                    break;
                }
            }
        }
    }
    
    private void printArr(int[] a) {
        StringBuilder sb = new StringBuilder();
        for(int n : a) {
            sb.append(n + " ");
        }
        System.out.println(sb);
    }
    
    public void init(int[] arr) {
        this.n = arr.length;
        this.arr = new int[n+1];
        for(int i=0; i<n; i++) {
            this.arr[i+1] = arr[i];
        }
        this.sums = new int[n+1];
        sums[1] = arr[0];
        for(int i=2; i<=n; i++) {
            sums[i] = sums[i-1] + arr[i-1];
        }
    }
}