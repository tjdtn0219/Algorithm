import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] arr;

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
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        ArrayList<Integer> stk = new ArrayList<>();
        for(int num : arr) {
            if(stk.isEmpty()) {
                stk.add(num);
            } else {
                int peek = stk.get(stk.size()-1);
                if(peek < num) {
                    stk.add(num);
                } else if (peek > num){
                    int lowerIdx = getLowerIdx(stk, num);
                    stk.set(lowerIdx, num);
                }
            }
        }
        System.out.println(stk.size());
    }

    public int getLowerIdx(ArrayList<Integer> stk, int tg) {
        int st = 0;
        int en = stk.size();

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= stk.get(mid)) {
                en = mid;
            } else {
                st = mid + 1;
            }
        }

        return st;
    }
}