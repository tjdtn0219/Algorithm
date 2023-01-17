import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int MAX_N = 10000001;

        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[MAX_N];

        for(int i=0; i<N; i++){
            arr[Integer.parseInt(bf.readLine())]++;
        }

        for(int i=1; i<MAX_N; i++){
            while(arr[i]>0){
                sb.append(i).append("\n");
                arr[i]--;
            }
        }
        System.out.println(sb);
    }
}