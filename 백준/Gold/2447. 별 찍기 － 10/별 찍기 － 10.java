import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());
        int tmp = N;

        boolean[][] arr_2 = new boolean[tmp+1][tmp+1];
        init_arr(arr_2, N+1);
        int k = 1;
        while(tmp/3 > 0){
            tmp /= 3;
            for(int i=0; i<k; i++){
                for(int j=0; j<k; j++){
                    make_hole(arr_2, tmp+tmp*3*i+1, tmp+tmp*3*j+1, tmp);
                }
            }
            k = k*3;
        }


        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                if(arr_2[i][j]) sb.append("*");
                else sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }

    public static void make_hole(boolean[][] arr, int p, int q, int m){
        for(int i=p; i<p+m; i++){
            for(int j=q; j<q+m; j++){
                arr[i][j] = false;
            }
        }
    }

    public static void init_arr(boolean[][] arr, int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                arr[i][j] = true;
            }
        }
    }
}
