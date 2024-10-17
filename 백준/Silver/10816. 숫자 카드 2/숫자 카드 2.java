import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = Integer.MAX_VALUE;

    int n;
    int[] arr1;
    int m;
    int[] arr2;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            arr1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arr1);
            // printArr(arr1);
            m = Integer.parseInt(br.readLine());
            arr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        StringBuilder sb = new StringBuilder();
        for(int num : arr2) {
            sb.append(getUpperIdx(num) - getLowerIdx(num)).append(" ");
        }
        System.out.println(sb);
    }

    public int getLowerIdx(int tg) {
        int st = 0;
        int en = n;

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= arr1[mid]) {
                en = mid;
            } else {
                st = mid + 1;
            }
        }
        return st;
    }

    public int getUpperIdx(int tg) {
        int st = 0;
        int en = n;

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg < arr1[mid]) {
                en = mid;
            } else {
                st = mid + 1;
            }
        }
        return st;
    }

    private void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

}