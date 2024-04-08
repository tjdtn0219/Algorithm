import java.io.*;
import java.util.*;
class Main {
    static long Comb(long n){
        if (n < 2)
            return 0;
        else
            return n * (n - 1) / 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long[] S = new long[n];
        int[] M = new int[n];
        int[] C = new int[m];
        for (int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            if (i == 0)
                S[i] = num;
            else
                S[i] = S[i - 1] + num;
            M[i] = (int)(S[i] % m);
            C[M[i]]++;
        }
        long sum = C[0];
        for (int i = 0; i < m; i++)
            sum += Comb(C[i]);
        System.out.println(sum);
    }
}