import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());
        int[][] arr = new int[N][3];

        for(int i=0; i<N; i++){
            String[] strings = bf.readLine().strip().split(" ");
            arr[i][0] = Integer.parseInt(strings[0]);
            arr[i][1] = Integer.parseInt(strings[1]);
        }
        int rank;
        for(int i=0; i<N; i++){
            rank = 1;
            for(int j=0; j<N; j++){
                if(i == j) continue;;
                if(arr[j][0] > arr[i][0] && arr[j][1] > arr[i][1]){
                    rank++;
                }
            }
            arr[i][2] = rank;
        }

        for(int i=0; i<N; i++){
            sb.append(arr[i][2]).append(" ");
        }
        System.out.println(sb);

    }
}
