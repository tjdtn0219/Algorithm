import java.util.*;

class Solution {
    
    int n;
    int[] numbers;
    int target;
    boolean[] isPlus;
    int ans;
    
    public int solution(int[] numbers, int target) {
        init(numbers, target);
        solve();
        return ans;
    }
    
    public void solve() {
        dfs(0);   
    }
    
    private void printArr(boolean[] arr) {
        StringBuilder sb = new StringBuilder();
        for(boolean tf : arr) {
            sb.append(tf).append(" ");
        }
        System.out.println(sb);
    }
    
    public void dfs(int k) {
        if(k == n) {
            // printArr(isPlus);
            int res = getResult();
            if(res == target) {
                ans++;
            }
            return ;
        }
        
        isPlus[k] = true;
        dfs(k+1);
        isPlus[k] = false;
        dfs(k+1);
    }
    
    public int getResult() {
        int sum = 0;
        for(int i=0; i<n; i++) {
            if(isPlus[i]) {
                sum += numbers[i];
            } else {
                sum -= numbers[i];   
            }
        }
        return sum;
    }
    
    public void init(int[] numbers, int target) {
        this.n = numbers.length;
        this.isPlus = new boolean[n];
        this.numbers = numbers;
        this.target = target;
    }
}