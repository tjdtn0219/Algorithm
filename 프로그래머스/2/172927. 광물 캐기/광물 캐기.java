import java.util.*;

class Solution {
    
    static final int[][] points = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    int[] picks;
    int[] minerals;
    int answer;
    int[] comb;
    int n;
    
    public int solution(int[] picks, String[] minerals) {
        init(picks, minerals);
        solve();
        return answer;
    }
    
    public void solve() {
        makeComb(0);
    }
    
    private void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void makeComb(int k) {
        if(k == n) {
            // printArr(comb);
            int point = pickMineral();
            answer = Math.min(answer, point);
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
    
    public int pickMineral() {
        // dia = 0, iron = 1, stone = 2
        int res = 0;
        int[] pickArr = getPickArr();
        // printArr(pickArr);
        for(int i=0; i<Math.min(pickArr.length, minerals.length); i++) {
            int pick = pickArr[i];
            int mineral = minerals[i];
            res += points[pick][mineral];
        }
        return res;
    }
    
    public int[] getPickArr() {
        int[] arr = new int[n*5];
        int idx = 0;
        for(int pick : comb) {
            for(int i=0; i<5; i++) {
                arr[idx++] = pick;
            }
        }
        return arr;
    }
    
    public void init(int[] picks, String[] minerals) {
        this.picks = picks;
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
        this.n = 0;
        for(int pick : picks) {
            n += pick;
        }
        this.comb = new int[n];
        this.answer = Integer.MAX_VALUE;
    }
}