import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        String[] strings = bf.readLine().split(" ");

        int[] a1 = new int[n];
        for(int i=0; i<n; i++) {
            a1[i] = Integer.parseInt(strings[i]);
        }
        Arrays.sort(a1);

        n = Integer.parseInt(bf.readLine());
        strings = bf.readLine().split(" ");
        int[] a2 = new int[n];
        for(int i=0; i<n; i++) {
            a2[i] = Integer.parseInt(strings[i]);
        }

        for(int tg : a2) {
            if(binarySearch(tg, a1)) System.out.println(1);
            else System.out.println(0);
        }

    }

    public static boolean binarySearch(int tg, int[] arr) {

        int st = 0;
        int en = arr.length - 1;

        while(st<=en) {
            int mid = (st + en) / 2;
            if(tg < arr[mid]) en = mid-1;
            else if(tg > arr[mid]) st = mid+1;
            else {
                return true;
            }
        }
        return false;
    }
}
