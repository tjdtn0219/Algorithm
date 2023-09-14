import java.io.*;

public class Main {

    public static final int[] dx = {-1,0,1,0};
    public static final int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //d = 0 북, 1 동, 2 남, 3 서

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int[][] map = new int[n][m];

        strings = br.readLine().split(" ");
        int x = Integer.parseInt(strings[0]);
        int y = Integer.parseInt(strings[1]);
        int d = Integer.parseInt(strings[2]);

        for(int i=0; i<n; i++) {
            strings = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(strings[j]);
            }
        }

        int ans = 0;
//        map[x][y] = 2;
        while(true) {
//            System.out.println("("+x+", "+y+")" + " d="+d);
            if(map[x][y]==0) {
                map[x][y] = 2;      //현재 칸 청소
                ans++;
            }
            boolean isClean = false;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(map[nx][ny]==0) isClean=true;
            }
            if(isClean) {
                for(int i=0; i<4; i++) {
                    d = rotate(d);
                    int fx = x + dx[d];
                    int fy = y + dy[d];
                    if(fx<0 || fy<0 || fx>=n || fy>=m) continue;
                    if(map[fx][fy]==0) {
                        x = fx; y = fy;
                        break;
                    }
                }
            }
            else {      //인접 4칸 청소O
                int bx = x - dx[d];
                int by = y - dy[d];
                if(map[bx][by]==1) break;
                x = bx;
                y = by;
            }
        }
        System.out.println(ans);

    }

    public static int rotate(int d) {
        if(d==0) return 3;
        return d-1;
    }
}


