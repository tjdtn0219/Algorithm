import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] arr;
    public static int[] temp;
    public static boolean[] vis;
    public static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strings = bf.readLine().split(" ");

        N = Integer.parseInt(strings[0]);
        M = Integer.parseInt(strings[1]);

        arr = new int[N];
        vis = new boolean[N];
        temp = new int[M];

        strings = bf.readLine().split(" ");
        for(int i=0; i<strings.length; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        Arrays.sort(arr);

        recur(0, 0);

    }

    public static void recur(int k, int n) {

        if(k==M) {
            StringBuilder sb = new StringBuilder();
            for(int num : temp) {
                sb.append(num + " ");
            }
            System.out.println(sb);
            return;
        }


        for(int i=0; i<arr.length; i++) {
            if(!vis[i]) {
                if(k==0) {
                    temp[k] = arr[i];
                    vis[i] = true;

                    recur(k + 1, n + 1);
                    vis[i] = false;
                } else {
                    if(temp[k-1] < arr[i]) {
                        temp[k] = arr[i];
                        vis[i] = true;

                        recur(k + 1, n + 1);
                        vis[i] = false;
                    }
                }
            }
        }
    }

}
