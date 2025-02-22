import java.util.*;

class Solution {
    
    int k;
    int[][] ranges;
    int n;
    List<Integer> valList;
    double[] answer;
    
    public double[] solution(int k, int[][] ranges) {
        init(k, ranges);
        solve();
        return answer;
    }
    
    public void solve() {
        System.out.println("n : " + n);
        double[] arr = new double[n+1];   //i-1 ~ i 까지의 넓이
        double[] sums = new double[n+1];
        for(int i=1; i<=n; i++) {
            arr[i] = (double) (valList.get(i-1) + valList.get(i)) / 2.0;
            // System.out.println("i : " + i + ", arr[i] : " + arr[i]);
        }
        
        sums[1] = arr[1];
        for(int i=2; i<=n; i++) {
            sums[i] = sums[i-1] + arr[i];
            // System.out.println("i : " + i + ", sums[i] : " + sums[i]);
        }
        
        int idx = 0;
        for(int[] range : ranges) {
            int left = range[0];
            int right = n + range[1];
            if(left > right) answer[idx++] = -1.0;
            else answer[idx++] = sums[right] - sums[left];
            // System.out.println("left : " + left + ", right : " + right);
            // System.out.println("sumLeft : " + sums[left] + ", sumRight : " + sums[right]);
        }
        
    }
    
    public void init(int k, int[][] ranges) {
        this.k = k;
        this.ranges = ranges;
        this.answer = new double[ranges.length];
        initN();
    }
    
    public void initN() {
        valList = new ArrayList<>();
        this.n = dfs(k);
    }
    
    public int dfs(int cur) {
        valList.add(cur);
        if(cur == 1) {
            return 0;
        }
        
        if(cur % 2 == 0) {
            return dfs(cur/2) + 1;
        } else {
            return dfs(cur*3 + 1) + 1;
        }
    }
}