import java.util.*;

class Solution {
    
    boolean[] isNot;
    int[][] scores;
    int[] wonho;
    
    public int solution(int[][] scores) {
        int answer = 0;
        init(scores);
        // System.out.println("wonho : " + wonho[0] + ", " + wonho[1]);
        return solve();
        // return answer;
    }
    
    public int solve() {
        Arrays.sort(scores, (s1, s2) -> {
            if(s1[0] == s2[0]) return s1[1] - s2[1];
            return s2[0] - s1[0];
        });
        
        int rank = 1;
        int myPoint = wonho[0] + wonho[1];
        int peerPoint = 0;
        for(int[] score : scores) {
            if(score[1] < peerPoint) {
                if(wonho[0] == score[0] && wonho[1] == score[1])
                    return -1;
            }
            else {
                peerPoint = Math.max(score[1], peerPoint);
                if(myPoint < score[0] + score[1])
                    rank++;
            }
        }
        return rank;
    }
    
    public void init(int[][] scores) {
        this.scores = scores;
        this.isNot = new boolean[scores.length];
        this.wonho = scores[0];
    }
}