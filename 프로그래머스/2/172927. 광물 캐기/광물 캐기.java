import java.util.*;

class Solution {
    
    static final int[][] points = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    int answer;
    int[] picks;
    int[] comb;
    int[] minerals;
    int n;
    
    public int solution(int[] picks, String[] minerals) {
        init(picks, minerals);
        solve();
        return answer;
    }
    
    public void solve() {
        // System.out.println("n : " + n);
        makeComb(0);
    }
    
    public void makeComb(int k) {
        if(k == n) {
            // printArr(comb);
            answer = Math.min(answer, mine());
            return ;
        }
        
        for(int i=0; i<3; i++) {
            if(picks[i] > 0) {
                comb[k] = i;
                picks[i]--;
                makeComb(k+1);
                picks[i]++;
            }
        }
    }
    
    public int mine() {
        int[] arr = new int[n*5];
        for(int i=0; i<n; i++) {
            for(int j=0; j<5; j++) {
                arr[i*5 + j] = comb[i];
            }
        }
        int sum = 0;
        for(int i=0; i<Math.min(n*5, minerals.length); i++) {
            int pick = arr[i];
            int mineral = minerals[i];
            sum += points[pick][mineral];
        }
        return sum;
    }
    
    private void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int c : arr) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int[] picks, String[] minerals) {
        this.n = 0;
        for(int i=0; i<picks.length; i++) {
            n += picks[i];
        }
        this.picks = picks;
        this.comb = new int[n];
        this.answer = Integer.MAX_VALUE;
        this.minerals = new int[minerals.length];
        for(int i=0; i<minerals.length; i++) {
            if(minerals[i].equals("diamond")) {
                this.minerals[i] = 0;
            } else if(minerals[i].equals("iron")) {
                this.minerals[i] = 1;
            } else {
                this.minerals[i] = 2;
            }
        }
    }
}