import java.util.*;

class Solution {
    
    int n;
    int[] arr;
    int[] sums;
    int ans;
    
    public int solution(int[] elements) {
        init(elements);
        solve();
        return ans;
    }
    
    private void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void solve() {
        printArr(arr);
        printArr(sums);
        
        HashSet<Integer> set = new HashSet<>();
        for(int k=1; k<=n; k++) {
            // System.out.println("k : " + k);
            for(int i=0; i<n; i++) {
                int num = sums[i+k] - sums[i];
                // System.out.println("num : " + num);
                set.add(num);
            }
        }
        
        ans = set.size();
    }
    
    public void init(int[] elements) {
        this.n = elements.length;
        this.arr = new int[2*n+1];
        for(int i=0; i<2; i++) {
            for(int j=1; j<=n; j++) {
                arr[i*n + j] = elements[j-1];
            }
        }
        this.sums = new int[2*n+1];
        sums[0] = arr[0];
        for(int i=1; i<=2*n; i++) {
            sums[i] = sums[i-1] + arr[i];
        }
    }
}


// 1 1 4 7 9ㅌ