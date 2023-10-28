class Solution {
    
    public int[] dx = {-1,0,1,0};
    public int[] dy = {0,1,0,-1};
    public int rLen;
    public int cLen;
    
    public int[] solution(String[] park, String[] routes) {
        int[] ans = new int[2];
        
        rLen = park.length; cLen = park[0].length();
        char[][] board = new char[rLen][cLen];
        
        int cx = 0; int cy = 0;
        for(int i=0; i<park.length; i++) {
            for(int j=0; j<park[i].length(); j++) {
                board[i][j] = park[i].charAt(j);
                if(board[i][j]=='S') {
                    cx = i; cy = j;
                }
            }
        }
        
        for(String route : routes) {
            
            int dir = 0;
            if(route.charAt(0)=='N') {
                dir = 0;
            } else if(route.charAt(0)=='E') {
                dir = 1;
            } else if(route.charAt(0)=='S') {
                dir = 2;
            } else {    //W
                dir = 3;
            }
            int n = route.charAt(2) - '0';
            boolean flag = false;
            int nx = cx; int ny = cy;
            for(int i=1; i<=n; i++) {
                nx = cx + i*dx[dir];
                ny = cy + i*dy[dir];
                if(nx<0 || ny<0 || nx>=rLen || ny>=cLen) {
                    flag = true;
                    break;
                }
                if(board[nx][ny] == 'X') {
                    flag = true;
                    break;
                }
            }
            if(flag) continue;
            cx = nx; cy = ny;
        }
        System.out.println(cx + " , " + cy);
        ans[0] = cx; ans[1] = cy;
        
        return ans;
    }
}