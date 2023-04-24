import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] arr;
    public static int[] cnt = new int[2];

    public static void main(String[] args) throws IOException {

       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();

       int N = Integer.parseInt(bf.readLine());
       arr = new int[N][N];

       for(int i=0; i<N; i++) {
           String input = bf.readLine();
           for (int j = 0; j < N; j++) {
               arr[i][j] = input.charAt(j) - '0';
           }
       }
//        for(int i=0; i<N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(arr[i][j]);
//            }
//            System.out.println("");
//        }
       recur(N, 0, 0);
    }

    public static void recur(int N, int x, int y) {

        if(check(N, x, y)) {
            System.out.print(arr[x][y]);
        }
        else {
            System.out.print("(");
            recur(N/2, x, y);
            recur(N/2 , x, y+N/2);
            recur(N/2 , x+N/2, y);
            recur(N/2 , x+N/2, y+N/2);
            System.out.print(")");
        }

    }

    public static boolean check(int N, int x, int y) {
        int n = arr[x][y];
        for(int i=x; i<x+N; i++) {
            for(int j=y; j<y+N; j++) {
                if(n != arr[i][j]) return false;
            }
        }
        return true;
    }
}
