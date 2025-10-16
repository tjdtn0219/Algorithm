import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static int limit;
    static int[] sumArr;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        Arrays.sort(arr);
        sumArr = new int[n];
        sumArr[0] = arr[0];
        for(int i=1; i<n; i++) {
            sumArr[i] = sumArr[i-1] + arr[i];
        }

        int max = arr[arr.length - 1];
        bSearch(max);
        System.out.println(answer);
    }

    public static int bSearch(int max) {
        int st = 0;
        int en = max;

        while(st <= en) {
            int mid = (st + en) / 2;
//            System.out.println("st, en, mid : " + st + ", " + en + ", " + mid);
            int sum = calculate(mid);
            if(sum <= limit) {
                answer = Math.max(answer, mid);
                st = mid + 1;
            } else {
                en = mid - 1;
            }
        }

        return answer;
    }

    public static int calculate(int price) {
        int sum = 0;
        int upperIdx = getUpperIdx(price);
//        System.out.println("upperIdx : " + upperIdx);
        if(upperIdx > 0) {
            sum += sumArr[upperIdx - 1];
        }
        int len = arr.length - upperIdx;
        sum += price * len;

//        System.out.println("sum : " + sum);
        return sum;
    }

    public static int getUpperIdx(int tg) {
        int st = 0;
        int en = arr.length;

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg < arr[mid]) en = mid;
            else st = mid + 1;
        }
        return st;
    }

    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            limit = Integer.parseInt(br.readLine());
            answer = -1;
        } catch(Exception e) {
            throw new RuntimeException("INPUT ERROR");
        }
    }
}