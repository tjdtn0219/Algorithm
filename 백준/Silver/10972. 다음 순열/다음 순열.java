import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] strings = br.readLine().split(" ");


        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        // 4 3 2 1 5
        // 1 5 4 3 2 -> 2 5 3 2 1
        // 1 4 3 2 5

        int i = n-1;
        while(i>0 && arr[i-1] > arr[i]) i--;
        if(i==0) {
            System.out.println(-1);
            return ;
        }

        int j = n-1;
        while(arr[i-1] > arr[j]) j--;

        swap(arr, i-1, j);

        //i-1부터 오름차순으로 뒤집기
        j = n-1;
        while(i<j) {
            swap(arr, i, j);
            i++; j--;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);

    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
