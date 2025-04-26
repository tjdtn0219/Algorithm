import java.util.*;

class Solution {
    
    int[] arr;
    int[] minArr1;
    int[] minArr2;
    int answer;
    int n;
    
    public int solution(int[] a) {
        init(a);
        solve();
        return answer;
    }
    
    public void solve() {
        // printArr(minArr1);
        // printArr(minArr2);
        for(int i=0; i<n; i++) {
            int num = arr[i];
            int min1;
            int min2;
            if(i == 0) {
                min1 = Integer.MAX_VALUE;
                min2 = minArr2[i+1];
            } else if(i == n-1) {
                min1 = minArr1[i-1];
                min2 = Integer.MAX_VALUE;
            } else {
                min1 = minArr1[i-1];
                min2 = minArr2[i+1];
            }
            // System.out.println("num : " + num + ", min1 : " + min1 + ", min2 : " + min2);
            if(num < min1 || num < min2) {
                answer++;
            }
            
        }
    }
    
    private void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int[] a) {
        this.arr = a;
        n = arr.length;
        this.minArr1 = new int[n];
        this.minArr2 = new int[n];
        minArr1[0] = arr[0];
        minArr2[n-1] = arr[n-1];
        for(int i=1; i<n; i++) {
            minArr1[i] = Math.min(minArr1[i-1], arr[i]);
        }
        for(int i=n-2; i>=0; i--) {
            minArr2[i] = Math.min(minArr2[i+1], arr[i]);
        }
    }
}