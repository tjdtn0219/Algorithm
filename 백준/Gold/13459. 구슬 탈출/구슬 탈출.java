import java.io.*;

public class Main {

    static final int[] DX = {1,0,-1,0}; //남 동 북 서
    static final int[] DY = {0,1,0,-1};

    int n, m;
    char[][] board;
    Point blue;
    Point red;
    Point originBlue;
    Point originRed;
    int[] comb;
    boolean ans;

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
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            board = new char[n][m];
            for(int i=0; i<n; i++) {
                String str = br.readLine();
                for(int j=0; j<m; j++) {
                    board[i][j] = str.charAt(j);
                    if(board[i][j] == 'B') {
                        blue = new Point(i, j);
                        board[i][j] = '.';
                    }
                    if(board[i][j] == 'R') {
                        red = new Point(i, j);
                        board[i][j] = '.';
                    }
                }
            }
            originBlue = new Point(blue.x, blue.y);
            originRed = new Point(red.x, red.y);
            comb = new int[10];
            ans = false;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        dfs(0, -1);
        if(ans) System.out.println(1);
        else System.out.println(0);
    }

    public void initBall() {
        blue.x = originBlue.x;
        blue.y = originBlue.y;
        red.x = originRed.x;
        red.y = originRed.y;
    }

    public void dfs(int k, int last) {
        if(k==10) {
//            printComb();
            ans |= move();
            initBall();
            return ;
        }

        for(int i=0; i<4; i++) {
            if(i == last) continue;
            comb[k] = i;
            dfs(k+1, i);
        }
    }

    public void printBall() {
        System.out.println("red : " + red.x + ", " + red.y);
        System.out.println("blue : " + blue.x + ", " + blue.y);
    }

    public boolean move() {
        //남 동 북 서
        for(int dir : comb) {
            if(dir == 0) moveDown();
            else if(dir == 1) moveRight();
            else if(dir == 2) moveUp();
            else moveLeft();
//            printBall();
            if(blue.x==-1 && blue.y==-1) {
                return false;
            } else if((red.x==-1 && red.y==-1) && !(blue.x==-1 && blue.y==-1)) {
                return true;
            }

        }
        return false;
    }

    public void moveDown() {
        if(red.x < blue.x) {
            //blue먼저 욺긴 후 red욺기기
            moveBall(blue, 0, false);
            moveBall(red, 0, true);
        } else {
            moveBall(red, 0, true);
            moveBall(blue, 0, false);
        }
    }

    public void moveRight() {
        if(red.y < blue.y) {
            //blue먼저 욺긴 후 red욺기기
            moveBall(blue, 1, false);
            moveBall(red, 1, true);
        } else {
            moveBall(red, 1, true);
            moveBall(blue, 1, false);
        }
    }

    public void moveUp() {
        if(red.x < blue.x) {
            //red먼저 욺긴 후 blue욺기기
            moveBall(red, 2, true);
            moveBall(blue, 2, false);
        } else {
            moveBall(blue, 2, false);
            moveBall(red, 2, true);
        }
    }

    public void moveLeft() {
        if(red.y < blue.y) {
            //red먼저 욺긴 후 blue욺기기
            moveBall(red, 3, true);
            moveBall(blue, 3, false);
        } else {
            moveBall(blue, 3, false);
            moveBall(red, 3, true);
        }
    }

    public void moveBall(Point color, int dir, boolean isRed) {
        int x = color.x;
        int y = color.y;
        int otherX = -1;
        int otherY = -1;
        if(isRed) {
            otherX = blue.x;
            otherY = blue.y;
        } else {
            otherX = red.x;
            otherY = red.y;
        }
        while(true) {
//            System.out.println("cur : " + x + ", " + y);
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(board[nx][ny] == 'O') {
                //구멍에 빠졌을 때
                color.x = -1;
                color.y = -1;
                break;
            }
            if(board[nx][ny] != '.' || (nx==otherX && ny==otherY)) {
                //막혔을 때
                color.x = x;
                color.y = y;
                break;
            }
            x = nx;
            y = ny;
        }
    }

    public void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int n : comb) {
            sb.append(n + " ");
        }
        System.out.println(sb);
    }
}


class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
