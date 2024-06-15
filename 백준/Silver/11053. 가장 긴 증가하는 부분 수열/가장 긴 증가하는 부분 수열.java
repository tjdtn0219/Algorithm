import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] arr;
    List<Integer> stk;

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
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            stk = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        System.out.println(LTS());
    }

    public int LTS() {
        for(int num : arr) {
//            System.out.println("num : " + num);
//            printStk();
            if(stk.isEmpty()) {
                stk.add(num);
            } else if(stk.get(stk.size()-1) < num) {
                stk.add(num);
            } else {
                int idx = lowerIdx(num);
                if(idx == stk.size()) stk.add(num);
                else stk.set(idx, num);
            }
        }
        return stk.size();
    }

    public void printStk() {
        StringBuilder sb = new StringBuilder();
        for(int num : stk) {
            sb.append(num + " ");
        }
        System.out.println(sb);
    }
    private int lowerIdx(int tg) {
        int st = 0;
        int en = stk.size();

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= stk.get(mid)) en = mid;
//            if(tg <= arr[mid]) en = mid;
            else st = mid + 1;
        }

        return st;
    }

}