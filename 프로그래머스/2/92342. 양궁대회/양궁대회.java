import java.util.*;

class Solution {
    
    static final int TARGET_CNT = 11;
    
    int n;
    int[] apeachInfo;
    int[] scoreLimit;
    int[] lionInfo;
    int maxDiff;
    int[] answer;
    HashMap<Integer, List<int[]>> answerMap;
    
    public int[] solution(int n, int[] info) {
        
        init(n, info);
        solve();
        return answer;
    }
    
    public void init(int n, int[] info) {
        this.n = n;
        apeachInfo = info;
        scoreLimit = new int[TARGET_CNT];
        for(int i=0; i<TARGET_CNT; i++) {
            scoreLimit[i] = apeachInfo[i] + 1;
        }
        lionInfo = new int[TARGET_CNT];
        maxDiff = 0;
        answer = new int[TARGET_CNT];
        answerMap = new HashMap<>();
    }
    
    public void solve() {
        dfs(0, 0);
        List<int[]> list = answerMap.getOrDefault(maxDiff, new ArrayList<>());
        if(list.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            Collections.sort(list, (o1, o2) -> {
               for(int i=TARGET_CNT-1; i>=0; i--) {
                   if(o1[i] != o2[i]) return o2[i] - o1[i];
               }
                return 0;
            });
            answer = list.get(0);
        }
        
        System.out.println("maxDiff : " + maxDiff);
    }
    
    public void dfs(int k, int start) {
        if(k==n) {
            int[] scores = getScore();
            int apeachScore = scores[0];
            int lionScore = scores[1];
            if(apeachScore < lionScore) {
                int diff = lionScore - apeachScore;
                if(diff >= maxDiff) {
                    List<int[]> list = answerMap.getOrDefault(diff, new ArrayList<>());
                    list.add(lionInfo.clone());
                    answerMap.put(diff, list);
                    maxDiff = diff;
                }
            }
            return ;
        }
        
        for(int i=start; i<TARGET_CNT; i++) {
            if(apeachInfo[i] < lionInfo[i]) continue;
                lionInfo[i]++;
                dfs(k+1, i);
                lionInfo[i]--;
        }
        
    }
    
    public int[] getScore() {
        int apeachScore = 0;
        int lionScore = 0;
        for(int i=0; i<TARGET_CNT; i++) {
            if(apeachInfo[i] == 0 && lionInfo[i] == 0) continue;
            if(apeachInfo[i] < lionInfo[i]) {
                lionScore += 10 - i;
            } else {
                apeachScore += 10 - i;
            }
        }
        int[] scores = new int[2];
        scores[0] = apeachScore;
        scores[1] = lionScore;
        return scores;
    }
    
 
}
