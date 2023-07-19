import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = bf.readLine().split(" ");

        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        inputs = bf.readLine().split(" ");
        int[] arr = new int[N];
        int[] d = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        d[0] = arr[0];
        for(int i=1; i<N; i++) {
            d[i] = d[i - 1] + arr[i];
        }

        for(int i=0; i<M; i++) {
            inputs = bf.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);

            if(a==1) System.out.println(d[b-1]);
            else System.out.println(d[b-1]-d[a-2]);
        }

    }
}
