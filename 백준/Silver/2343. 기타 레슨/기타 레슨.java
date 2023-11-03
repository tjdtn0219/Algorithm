import java.util.*;
import java.io.*;

public class Main {

    public static int n;
    public static int[] sum;
    public static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        strings = br.readLine().split(" ");
        arr = new int[n];
        sum = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(strings[i]);

        sum[0] = arr[0];
        int st = arr[0];      //arr[i] 중 최고 값
        for(int i=1; i<n; i++) {
            sum[i] = sum[i-1] + arr[i];
            st = Math.max(st, arr[i]);
        }
        int en = sum[n-1];

        while(st < en) {
            int mid = (st+en)/2;
//            System.out.println("mid : " + mid + " , " + getCount(mid));
            if(getCount(mid) <= m) {
                en = mid;
            } else {
                st = mid+1;
            }
        }

        System.out.println(st);
    }

    public static int getCount(int maxBlueRay) {
        int count = 1;
        int remain = maxBlueRay;
        for (int i = 0; i < n; i++) {
            if (remain < arr[i]) {
                remain = maxBlueRay;
                count++;
            }
            remain -= arr[i];
        }
        return count;
    }
}