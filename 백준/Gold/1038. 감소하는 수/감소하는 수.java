import java.io.*;
        import java.util.*;

public class Main {

    static final int MAX = 1_000_000;
    static final int[] NUMS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    int n;
    List<Long> answerList;

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
            answerList = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }

    }

    public void solve() {
        fillAnswerList();
        Collections.sort(answerList);
        if(n >= answerList.size()) System.out.println(-1);
        else System.out.println(answerList.get(n));
    }


    public void fillAnswerList() {
        for(int i=0; i<10; i++) {
            dfs(i);
        }
    }

    public void dfs(long num) {
        answerList.add(num);
        int mod = (int) (num%10);

        for(int i=mod-1; i>=0; i--) {
            long nxt = num*10 + i;
            dfs(nxt);
        }

    }
}