import java.util.*;
import java.io.*;

public class Main {

    public static final int[] dx = {1,0,-1,0};
    public static final int[] dy = {0,-1,0,1};
    public static int[][] map;
    public static int n,m,t;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);
        t = Integer.parseInt(strings[2]);

        int acRow = 0;

        map = new int[n][m];
        for(int i=0; i<n; i++) {
            strings = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(strings[j]);
                if(map[i][j]==-1) {
                    acRow = i;
                }
            }
        }
        for(int i=0; i<t; i++) {
            spread();
            move1(acRow - 1);
            move2(acRow);
        }

        int ans = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
//                System.out.print(map[i][j] + " ");
                if(map[i][j]==-1) continue;
                ans += map[i][j];
            }
//            System.out.println();
        }
        System.out.println(ans);

    }

    public static void spread() {
        int[][] temp = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j]<1) continue;
                int sum = 0;
                for(int dir=0; dir<4; dir++) {
                    int ni = i + dx[dir];
                    int nj = j + dy[dir];
                    if(ni<0 || nj<0 || ni>=n || nj>=m) continue;
                    if(map[ni][nj]==-1) continue;
                    temp[ni][nj] += map[i][j]/5;
                    sum += map[i][j]/5;
                }
                temp[i][j] -= sum;
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }

    public static void move1(int row1) {

        int i = row1-1;
        while(i>=0) {
            if(map[i+1][0] == -1) map[i][0] = 0;
            else {
                map[i+1][0] = map[i][0];
            }
            i--;
        }
        int j = 1;
        while(j < m) {
            map[0][j-1] = map[0][j];
            j++;
        }
        i=1;
        while(i<=row1) {
            map[i-1][m-1] = map[i][m-1];
            i++;
        }
        j = m-1;
        while(j>1) {
            map[row1][j] = map[row1][j-1];
            j--;
        }
        map[row1][j] = 0;
    }

    public static void move2(int row2) {

        int i = row2+1;
        map[i++][0] = 0;
        while(i<n) {
            map[i-1][0] = map[i][0];
            i++;
        }

        int j = 1;
        while(j<m) {
            map[n-1][j-1] = map[n-1][j];
            j++;
        }

        i = n-2;
        while(i>=row2) {
            map[i+1][m-1] = map[i][m-1];
            i--;
        }

        j=m-2;
        while(j>0) {
            map[row2][j+1] = map[row2][j];
            j--;
        }
        map[row2][j+1] = 0;
    }
}
