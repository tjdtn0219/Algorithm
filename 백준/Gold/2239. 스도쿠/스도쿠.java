import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static final int LEN = 9;
    static final int BOX_LEN = 3;

    int[][] board;
    List<Point> zeroList;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       
            board = new int[LEN][LEN];
            zeroList = new ArrayList<>();
            for(int i=0; i<LEN; i++) {
                String tmp = br.readLine();
                for(int j=0; j<LEN; j++) {
                    int num = tmp.charAt(j) - '0';
                    board[i][j] = num;
                    if(num == 0) {
                        zeroList.add(new Point(i, j));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        // dfs(0);
        dfs(0, 0);
    }

    public void dfs(int idx) {
        if(idx == zeroList.size()) {
            printBoard();
            System.exit(0);
        }

        Point p = zeroList.get(idx);
        for(int val=1; val<=9; val++) {
            if(isPossible(p.x, p.y, val)) {
                board[p.x][p.y] = val;
                dfs(idx+1);
                board[p.x][p.y] = 0;
            }
        }
        board[p.x][p.y] = 0;
    }

    public boolean isPossible(int x, int y, int val) {
        boolean[] isExist = new boolean[LEN+1];
        setIsExistInRow(x, isExist);
        setIsExistInColumn(y, isExist);
        setIsExistInBlock(x, y, isExist);
    
        if(isExist[val]) return false;
        return true;
    }
    
    public void setIsExistInRow(int x, boolean[] isExist) {
        for(int j=0; j<LEN; j++) {
            int num = board[x][j];
            // System.out.println("row : num : " + num);
            isExist[num] = true;
        }
    }
    
    public void setIsExistInColumn(int y, boolean[] isExist) {
        for(int i=0; i<LEN; i++) {
            int num = board[i][y];
            // System.out.println("col : num : " + num);
            isExist[num] = true;
        }
    }
    
    public void setIsExistInBlock(int x, int y, boolean[] isExist) {
        int sx = (x / 3) * 3;
        int sy = (y / 3) * 3;
        for(int i=sx; i<sx + 3; i++) {
            for(int j=sy; j<sy + 3; j++) {
                int num = board[i][j];
                // System.out.println("block : num : " + num);
                isExist[num] = true;
            }
        }
    }    

    public void dfs(int x, int y) {
        if(y >= LEN) {
            dfs(x+1, 0);
            return ;
        }

        if(x >= LEN) {
            printBoard();
            System.exit(0);
        }

        if(board[x][y] > 0) {
            dfs(x, y+1);
            return ;
        }

        for(int i=1; i<=9; i++) {
            if(isPossible(x, y, i)) {
                board[x][y] = i;
                dfs(x, y+1);
                board[x][y] = 0;
            }
        }

        board[x][y] = 0;
        
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<LEN; i++) {
            for(int j=0; j<LEN; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}