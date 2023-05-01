
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        Integer[] lopes = new Integer[N];

        for(int i=0; i<N; i++) {
            lopes[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(lopes, (l1, l2)-> l2- l1);

        int max = 0;
        for(int i=0; i<N; i++) {
            max = Math.max(max, lopes[i]*(i+1));
        }

        System.out.println(max);

    }
}