import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 10_000;
    
    int n;
    int[][] arr;
    boolean[] vis;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void solve() {
        for(int[] a_b : arr) {
            int a = a_b[0];
            int b = a_b[1];
            String res = getCommands(a, b);
            System.out.println(res);
        }
    }

    public String getCommands(int a, int b) {
        HashSet<Integer> visSet = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(a, ""));
        visSet.add(a);

        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.num == b) {
                return cur.cmd;
            }
            int d = funcD(cur.num);
            if(!visSet.contains(d)) {
                visSet.add(d);
                q.add(new Node(d, cur.cmd + "D"));
            }
            int s = funcS(cur.num);
            if(!visSet.contains(s)) {
                visSet.add(s);
                q.add(new Node(s, cur.cmd + "S"));
            }
            int l = funcL(cur.num);
            if(!visSet.contains(l)) {
                visSet.add(l);
                q.add(new Node(l, cur.cmd + "L"));
            }
            int r = funcR(cur.num);
            if(!visSet.contains(r)) {
                visSet.add(r);
                q.add(new Node(r, cur.cmd + "R"));
            }
        }
        return "";
    }

    public int funcD(int num) {
        return (num * 2) % MOD;
    }

    public int funcS(int num) {
        if(num == 0) return 9999;
        return num-1;
    }

    public int funcL(int num) {
        return (num % 1000) * 10 + num / 1000;
    }

    public int funcR(int num) {
        return (num % 10) * 1000 + num / 10;
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            arr = new int[n][2];
            for(int i=0; i<n; i++) {
                String[] a_b = br.readLine().split(" ");
                int a = Integer.parseInt(a_b[0]);
                int b = Integer.parseInt(a_b[1]);
                arr[i][0] = a;
                arr[i][1] = b;
            }
            vis = new boolean[MOD+1];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

}

class Node {
    int num;
    String cmd;

    public Node(int num, String cmd) {
        this.num = num;
        this.cmd = cmd;
    }
}