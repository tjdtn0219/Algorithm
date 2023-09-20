import java.util.*;
import java.io.*;

public class Main {

    public static final int[] dx = {1,0,-1,0};  //남,서,북,동
    public static final int[] dy = {0,-1,0,1};
    public static int[][] arr;
    public static int[][] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int r = Integer.parseInt(strings[2]);

        arr = new int[n][m];
        temp = new int[n][m];

        for(int i=0; i<n; i++) {
            strings = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(strings[j]);
            }
        }

        for(int i=0; i<r; i++) {
            int x1 = 0; int y1 = 0;
            int x2 = n-1; int y2 = m-1;
            while (true) {
                rotate(x1++, y1++, x2--, y2--);
//                print_temp(n,m);
//                System.out.println("X1, Y1 : " + x1 + ", " + y1);
//                System.out.println("X2, Y2 : " + x2 + ", " + y2);
//                System.out.println();
                if (x1 == x2 && y1 == y2) {
                    temp[x1][y1] = arr[x1][y1];
                    break;
                }
                if (x1 >= x2 || y1 >= y2) break;
            }
            cloneArr(n,m);
        }

        print_temp(n, m);

    }

    private static void print_temp(int n, int m) {
        for(int i = 0; i< n; i++) {
            for(int j = 0; j< m; j++) {
                System.out.print(temp[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void cloneArr(int n, int m) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    public static void rotate(int x1, int y1, int x2, int y2) {
        int r_len = x2 - x1 + 1;
        int c_len = y2 - y1 + 1;

        int x = x1; int y = y1;
        for(int i=0; i<r_len; i++) {    //아래
            if(x+1 > x2) {
                temp[x][y+1] = arr[x][y];
                break;
            }
            temp[x+1][y] = arr[x][y];
            x++;
        }
//        System.out.println("x,y : " + x + " ," + y);

        for(int i=0; i<c_len; i++) {    //오른쪽
            if(y+1 > y2) {
                temp[x-1][y] = arr[x][y];
                break;
            }
            temp[x][y+1] = arr[x][y];
            y++;
        }
//        System.out.println("x,y : " + x + " ," + y);

        for(int i=0; i<r_len; i++) {    //위쪽
            if(x-1 < x1) {
                temp[x][y-1] = arr[x][y];
                break;
            }
            temp[x-1][y] = arr[x][y];
            x--;
        }
//        System.out.println("x,y : " + x + " ," + y);

        for(int i=0; i<c_len; i++) {    //왼쪽
            if(y-1 < y1) {
                temp[x+1][y] = arr[x][y];
                break;
            }
            temp[x][y-1] = arr[x][y];
            y--;
        }
//        System.out.println("x,y : " + x + " ," + y + "\n");
    }
}
