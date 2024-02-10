import java.io.*;

public class Main {

    static final int[] dx = {1,0,-1,0};     //아래, 오른쪽, 위, 왼쪽
    static final int[] dy = {0,1,0,-1};
    static final int MAX_CNT = 10;
    int n;
    int m;
    char[][] board;
    Point red;
    Point blue;
    int answer;

    public static void main(String[] args) {

        new Main().solution();
    }

    public void solution() {
        input();

//        shake(3);
//        System.out.println("red : " + red.x + " " + red.y);
//        System.out.println("blue : " + blue.x + " " + blue.y);
        dfs(0);
        if(answer == MAX_CNT + 1) answer = -1;
        System.out.println(answer);
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
                        blue  = new Point(i, j);
                        board[i][j] = '.';
                    }
                    if(board[i][j] == 'R') {
                        red = new Point(i, j);
                        board[i][j] = '.';
                    }
                }
            }
            answer = MAX_CNT + 1;
        } catch(IOException e) {
            System.out.println("INPUT ERROR!!");
        }
    }

    public void dfs(int k) {
        if(red.x == -1 && red.y == -1) {
            if(blue.x == -1 && blue.y == -1) {
                return ;
            } else {
                answer = Math.min(answer, k);
            }
        }

        if(k==MAX_CNT) {
            return ;
        }

        Point tmpBlue = new Point(blue.x, blue.y);
        Point tmpRed = new Point(red.x, red.y);
        for(int dir=0; dir<4; dir++) {
            //아래, 오른쪽, 위, 왼쪽
            shake(dir);
            dfs(k+1);
            blue.x = tmpBlue.x;
            blue.y = tmpBlue.y;
            red.x = tmpRed.x;
            red.y = tmpRed.y;
        }
    }

    public void shake(int dir) {
        char firstMoveColor = selectFirstMove(dir);
        if(firstMoveColor == 'B') {
            move(blue, red, dir);
            move(red, blue, dir);
        } else {
            move(red, blue, dir);
            move(blue, red, dir);
        }
    }

    public void move(Point curBall, Point otherBall, int dir) {
        while(true) {
            int nx = curBall.x + dx[dir];
            int ny = curBall.y + dy[dir];
            if(nx<1 || ny<1 || nx>=n-1 || ny>=m-1) break;
//            System.out.println("nx : " + nx + " , ny : " + ny);
            if(nx == otherBall.x && ny == otherBall.y) break;
            if(board[nx][ny] == 'O') {
                curBall.x = -1;
                curBall.y = -1;
                break;
            } else if(board[nx][ny] == '.') {
                curBall.x = nx;
                curBall.y = ny;
            } else {
                //'#'
                break;
            }
        }
    }

    public char selectFirstMove(int dir) {
        if(dir == 0) {  //아래
            if(blue.x > red.x) return 'B';
            else return 'R';
        } else if(dir == 1) {   //오른쪽
            if(blue.y > red.y) return 'B';
            else return 'R';
        } else if(dir == 2) {   //위쪽
            if(blue.x > red.x) return 'R';
            else return 'B';
        } else {    //왼쪽
            if(blue.y > red.y) return 'R';
            else return 'B';
        }
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