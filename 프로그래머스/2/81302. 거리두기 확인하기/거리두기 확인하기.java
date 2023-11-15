class Solution {
    
    public int[] dx = {1,0,-1,0};
    public int[] dy = {0,1,0,-1};
    
    public int[] solution(String[][] places) {
        int[] ans = new int[5];
        
        for(int i=0; i<5; i++) {
            ans[i] = loopFunc(places[i]);
        }
        
        return ans;
    }
    
    public int loopFunc(String[] place) {
        char[][] board = new char[5][5];
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                board[i][j] = place[i].charAt(j);
            }
        }
        // printBoard(board);
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(board[i][j]=='P') {
                    if(!isKeepDistance(board, i, j)) return 0;
                }
            }
        }
        return 1;
    }
    
    public boolean isKeepDistance(char[][] board, int x, int y) {
        for(int d1=0; d1<4; d1++) {
            int nx1 = x + dx[d1];
            int ny1 = y + dy[d1];
            if(nx1<0 || ny1<0 || nx1>=5 || ny1>=5) continue;
            if(board[nx1][ny1]=='P') return false;
            for(int d2=0; d2<4; d2++) {
                int nx2 = nx1 + dx[d2];
                int ny2 = ny1 + dy[d2];
                if(nx2<0 || ny2<0 || nx2>=5 || ny2>=5) continue;
                if(nx2==x && ny2==y) continue;
                if(board[nx2][ny2]=='P' && board[nx1][ny1]!='X') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void printBoard(char[][] board) {
        System.out.println("==================");
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================");
    }
}