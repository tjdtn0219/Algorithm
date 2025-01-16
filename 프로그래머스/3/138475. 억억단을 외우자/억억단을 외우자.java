import java.util.*;

class Solution {
    
    static final int N = 5_000_000;
    
    int[] divCntArr;
    int e;
    int[] starts;
    int[] dp;
    int[] maxFreq;
    
    public int[] solution(int e, int[] starts) {
        init(e, starts);
        return solve();
    }
    
    public int[] solve() {
        int maxIdx = e;
        for(int i = e; i>0; i--){
            if(divCntArr[i] >= divCntArr[maxIdx]) {
                maxIdx = i;
            }
            maxFreq[i] = maxIdx;
        }
        
        int[] answer = new int[starts.length];
        for(int i=0; i<starts.length; i++) {
            int s = starts[i];
            answer[i] = maxFreq[s];
        }
        return answer;
    }
    
    public void init(int e, int[] starts) {
        this.e = e;
        this.starts = starts;
        this.dp = new int[e+1];
        this.maxFreq = new int[e+1];

        initDivCntArr();
    }
    
    public void initDivCntArr() {
        divCntArr = new int[e+1];
        for(int i=1; i<=e; i++) {
            for(int j=1; j<=e/i; j++) {
                divCntArr[i*j]++;
            }
        }
    }
}