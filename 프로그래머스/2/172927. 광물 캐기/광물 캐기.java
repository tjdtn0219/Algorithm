class Solution {
    
    static final int LEN = 3;
    
    int ans;
    int[] picks;
    int[] minerals;
    int[][] tiredArr;   //tiredArr[곡괭이][광물]
    int[] comb;
    int n;
    
    public int solution(int[] picks, String[] minerals) {
        // 0: 돌, 1: 철, 2: 다이아몬드
        init(picks, minerals);
        // print();
        makeComb(0, new boolean[n]);
        return ans;
    }
    
    private void print() {
        System.out.println("====picks====");
        for(int p : picks) {
            System.out.print(p + " ");
        }
        System.out.println("\n============");
    }
    
    public void makeComb(int k, boolean[] vis) {
        if(k == n) {
            // StringBuilder sb = new StringBuilder();
            // for(int num : comb) {
            //     sb.append(num).append(" ");
            // }
            // System.out.println(sb);
            int[] pickArr = getPickArr();
            mine(pickArr);
            return ;
        }
        
        for(int i=0; i<n; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            comb[k] = i;
            makeComb(k+1, vis);
            vis[i] = false;
        }
    }
    
    public void mine(int[] pickArr) {
        int min = Math.min(pickArr.length, minerals.length);
        int sum = 0;
        for(int i=0; i<min; i++) {
            int p = pickArr[i];
            int m = minerals[i];
            sum += tiredArr[p][m];
        }
        ans = Math.min(ans, sum);
    }
    
    public int[] getPickArr() {
        int[] arr = new int[n*5];
        for(int i=0; i<n; i++) {
            int idx = comb[i];
            int pick = picks[idx];
            for(int j=0; j<5; j++) {
                arr[i*5 + j] = pick;
            }
        }
        return arr;
    }
    
    public void init(int[] picks, String[] minerals) {
        this.ans = Integer.MAX_VALUE;
        n = 0;
        int pickSum = 0;
        for(int pick : picks) {
            pickSum += pick;
        }
        int mineralCnt = minerals.length;
        if(pickSum*5 <= mineralCnt) {
            n = pickSum;
        } else {
            n = mineralCnt/5;
            if(mineralCnt%5 != 0) n++;
        }
        System.out.println("n : " + n);
        this.picks = new int[pickSum];
        int idx = 0;
        for(int i=0; i<LEN; i++) {
            for(int j=0; j<picks[i]; j++) {
                if(i == 0) this.picks[idx++] = 3;
                else if(i == 1) this.picks[idx++] = 2;
                else if(i == 2) this.picks[idx++] = 1;
            }
        }
        this.minerals = new int[minerals.length];
        for(int i=0; i<minerals.length; i++) {
            if(minerals[i].equals("diamond")) {
                this.minerals[i] = 3;
            } else if(minerals[i].equals("iron")) {
                this.minerals[i] = 2;
            } else {
                this.minerals[i] = 1;   
            }
        }
        this.tiredArr = new int[LEN+1][LEN+1];
        for(int i=1; i<=LEN; i++) {
            for(int j=1; j<=i; j++) {
                tiredArr[i][j] = 1;
            }
        }
        tiredArr[1][2] = 5;
        tiredArr[1][3] = 25;
        tiredArr[2][3] = 5;
        comb = new int[n];
    }
}