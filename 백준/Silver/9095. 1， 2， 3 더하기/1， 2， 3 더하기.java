import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        int[] d = new int[12];

        d[1] = 1; d[2] = 2; d[3] = 4;
        for(int j=4; j<12; j++) {
            d[j] = d[j-1] + d[j-2] + d[j-3];
        }

        for(int i=0; i<T; i++) {
            int n = Integer.parseInt(bf.readLine());
            System.out.println(d[n]);
        }
    }
}
