import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());

        String[] strings = bf.readLine().split(" ");
        int[] arr = new int[n];
        int[] max_sum = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        max_sum[0] = arr[0];
        for(int i=1; i<n; i++) {
            max_sum[i] = Math.max(max_sum[i-1] + arr[i], arr[i]);
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            max = Math.max(max, max_sum[i]);
        }

        System.out.println(max);

    }
}