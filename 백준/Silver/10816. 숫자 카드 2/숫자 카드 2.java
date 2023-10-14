import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] strings = br.readLine().split(" ");

        int[] a1 = new int[n];
        for(int i=0; i<n; i++) {
            a1[i] = Integer.parseInt(strings[i]);
        }
        Arrays.sort(a1);

        n = Integer.parseInt(br.readLine());
        strings = br.readLine().split(" ");
        int[] a2 = new int[n];
        for(int i=0; i<n; i++) {
            a2[i] = Integer.parseInt(strings[i]);
        }

        for(int tg : a2) {
//            sb.append(upper_idx(tg, a1) - lower_idx(tg, a1) + " ");
            int li = lower_idx(tg, a1);
            int ri = upper_idx(tg, a1);
            if(li >= a1.length) sb.append(0 + " ");
            else if(a1[li]!=tg) sb.append(0 + " ");
            else sb.append(ri-li).append(" ");
        }
//        n = Integer.parseInt(br.readLine());
//        strings = br.readLine().split(" ");
//        for(int i=0; i<n; i++) {
//            int num = Integer.parseInt(strings[i]);
//            int li = lower_idx(num, a1);
//            int ri = upper_idx(num, a1);
//            if(a1[li]!=num) sb.append(0 + " ");
//            else sb.append(ri-li).append(" ");
////            System.out.println("num : " + num + " li : " + li + " value : " + arr[li]);
////            System.out.println("num : " + num + " ri : " + ri + " value : " + arr[li]);
//        }
        System.out.println(sb.toString());

    }

    public static int lower_idx (int tg, int[] arr) {
        int st = 0;
        int en = arr.length;

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= arr[mid]) en = mid;
            else st = mid + 1;          //tg > arr[mid]
        }
        return st;
    }

    public static int upper_idx (int tg, int[] arr) {
        int st = 0;
        int en = arr.length;

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg < arr[mid]) en = mid;
            else st = mid + 1;          //tg >= arr[mid]
        }
        return st;
    }
}
