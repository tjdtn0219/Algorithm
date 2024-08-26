import java.io.*;
import java.util.Stack;

public class Main{

    int n, k;
    String nums;
    Stack<Integer> stk;

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
            String[] N_K = br.readLine().split(" ");
            n = Integer.parseInt(N_K[0]);
            k = Integer.parseInt(N_K[1]);
            nums = br.readLine();
            stk = new Stack<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int cnt = 0;
        for(char c : nums.toCharArray()) {
            int num = c - '0';
            while (!stk.isEmpty() && cnt < k && num > stk.peek()) {
                cnt++;
                stk.pop();
            }
            stk.push(num);
        }
        while(cnt < k) {
            cnt++;
            stk.pop();
        }

        StringBuilder sb = new StringBuilder();
        for(int num : stk) {
            sb.append(num);
        }
        System.out.println(sb);
    }
}