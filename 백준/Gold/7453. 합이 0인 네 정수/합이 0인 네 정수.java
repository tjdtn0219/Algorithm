import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long[] A;
    static long[] B;
    static long[] C;
    static long[] D;

    static long findByBinarySearch() {
        long[] AB = new long[n * n];
        long[] CD = new long[n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[i * n + j] = A[i] + B[j];
                CD[i * n + j] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long count = 0;
        for (int ab = 0; ab < n * n; ab++) {
            long target = -1 * AB[ab];

            int l = 0, r = n * n;
            while (l < r) {
                int m = (l + r) / 2;

                if (CD[m] < target) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

            int lowerBound = l;

            l = 0;
            r = n * n;
            while (l < r) {
                int m = (l + r) / 2;

                if (CD[m] <= target) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

            int upperBound = l;

            count += (upperBound - lowerBound);
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new long[n];
        B = new long[n];
        C = new long[n];
        D = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(findByBinarySearch());
        br.close();
    }
}