import java.util.*;

class Solution {
    
    int[][] arr;
    int n;
    int[] answer;
    
    public int[] solution(int[][] arr) {
        init(arr);
        // printArr(arr);
        solve();
        return answer;
    }
    
    public void solve() {
        // int len = n;
        // int x = 0;
        // int y = 0;
        // if(len < 8) return ;
        // int flag = isCompress(4, 0, 4);
        // System.out.println(flag);
        dfs(n, 0, 0);
    }
    
    public void dfs(int len, int x, int y) {
        if(len == 1) {
            answer[arr[x][y]]++;
            return ;
        }
        int num = isCompress(len, x, y);
        // System.out.println(num);
        
        if(num > -1) {
            answer[num]++;
            return ;
        }
        
        dfs(len/2, x, y);
        dfs(len/2, x+len/2, y);
        dfs(len/2, x, y+len/2);
        dfs(len/2, x+len/2, y+len/2);
    }
    
    public int isCompress(int len, int x, int y) {
        int num = arr[x][y];
        for(int i=x; i<x+len; i++) {
            for(int j=y; j<y+len; j++) {
                if(num != arr[i][j]) {
                    return -1;
                }
            }
        }
        return num;
    }
    
    public void init(int[][] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.answer = new int[2];
    }
    
    private void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}