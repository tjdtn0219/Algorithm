import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int ans = 0;
        int n = board.length;
        Stack<Integer> stk = new Stack<>();
        
        for(int m : moves) {
            for(int i=0; i<n; i++) {
                int picked = board[i][m-1];
                if(picked > 0) {
                    if(stk.isEmpty()) stk.push(picked);
                    else {
                        if(stk.peek()!=picked) stk.push(picked);
                        else {
                            stk.pop();
                            ans+=2;
                        }
                    }
                    board[i][m-1] = 0;
                    break;
                }
            }
            
        }
                   
        return ans;
    }
}