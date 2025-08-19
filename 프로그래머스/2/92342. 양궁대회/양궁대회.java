import java.util.*;

class Solution {
    
    static final int N = 11;
    
    int n;
    int[] apeach;
    int[] lion;
    int[] answer;
    int maxDiff;
    HashMap<Integer, List<int[]>> answerMap;
    
    public int[] solution(int n, int[] info) {
        
        init(n, info);
        solve();
        return answer;
    }
    
    public void solve() {
        makeComb(0, 0);
        if(maxDiff == 0) {
            answer = new int[1];
            answer[0] = -1;
            return ;
        }
        List<int[]> list = answerMap.get(maxDiff);
        Collections.sort(list, (arr1, arr2) -> {
            for(int i=N-1; i>=0; i--) {
                if(arr1[i] != arr2[i]) return arr2[i] - arr1[i];
            }
            return 0;
        });
        answer = list.get(0);
    }
    
    public void makeComb(int k, int last) {
        if(k == n) {
            int scoreDiff = getScoreDiff();
            if(scoreDiff > 0 && scoreDiff >= maxDiff) {
                List<int[]> list = answerMap.getOrDefault(scoreDiff, new ArrayList<>());
                list.add(lion.clone());
                answerMap.put(scoreDiff, list);
                maxDiff = scoreDiff;
            }
            return ;
        }
        
        for(int i=last; i<N; i++) {
            lion[i]++;
            makeComb(k+1, i);
            lion[i]--;
        }
    }
    
    public int getScoreDiff() {
        int apeachScore = 0;
        int lionScore = 0;
        for(int i=0; i<N; i++) {
            if(apeach[i] >= lion[i] && apeach[i] > 0) {
                apeachScore += 10 - i;
            } else if(apeach[i] < lion[i]) {
                lionScore += 10 - i;
            }
        }
        // System.out.println("lionScore : " + lionScore);
        // System.out.println("apeachScore : " + apeachScore);
        return lionScore - apeachScore;
    }
    
    private void printLion() {
        StringBuilder sb = new StringBuilder();
        for(int n : lion) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int n, int[] infos) {
        this.n = n;
        this.apeach = infos;
        this.lion = new int[N];
        this.answer = new int[N];
        this.maxDiff = 0;
        this.answerMap = new HashMap<>();
    }
}