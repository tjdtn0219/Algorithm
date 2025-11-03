import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int i, j;
        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
        @Override
        public String toString() {
            return i + ", " + j;
        }
    }

    static int n, k;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        boolean[][] vis = new boolean[2][n];
        vis[0][0] = true;

        int cnt = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Node cur = q.poll();
                if(isOutRange(cur)) {
                    System.out.println(1);
                    return ;
                }
                Node nxt1 = new Node(cur.i, cur.j + 1);
                if(isOutRange(nxt1)) {
                    System.out.println(1);
                    return;
                } else {
                    if(nxt1.j > cnt && arr[nxt1.i][nxt1.j] == 1 && !vis[nxt1.i][nxt1.j]) {
                        vis[nxt1.i][nxt1.j] = true;
                        q.add(nxt1);
//                        System.out.println(cur + " -> " + nxt1 + " " + arr[nxt1.i][nxt1.j]);
                    }
                }
                Node nxt2 = new Node(cur.i, cur.j - 1);
                if(isOutRange(nxt2)) {
                    System.out.println(1);
                    return;
                } else {
                    if(nxt2.j > cnt && arr[nxt2.i][nxt2.j] == 1 && !vis[nxt2.i][nxt2.j]) {
                        vis[nxt2.i][nxt2.j] = true;
                        q.add(nxt2);
//                        System.out.println(cur + " -> " + nxt2 + " " + arr[nxt2.i][nxt2.j]);
                    }
                }
                Node nxt3 = new Node((cur.i + 1) % 2, cur.j + k);
                if(isOutRange(nxt3)) {
                    System.out.println(1);
                    return;
                } else {
                    if(nxt3.j > cnt && arr[nxt3.i][nxt3.j] == 1 && !vis[nxt3.i][nxt3.j]) {
                        vis[nxt3.i][nxt3.j] = true;
                        q.add(nxt3);
//                        System.out.println(cur + " -> " + nxt3);
                    }
                }
            }
            cnt++;
        }

        System.out.println(0);
    }

    public static boolean isOutRange(Node node) {
        if(node.j >= n) {
            return true;
        } else {
            return false;
        }
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_k = br.readLine().split(" ");
            n = Integer.parseInt(n_k[0]);
            k = Integer.parseInt(n_k[1]);
            arr = new int[2][n];

            for(int i=0; i<2; i++) {
                String s = br.readLine();
                for(int j=0; j<n; j++) {
                    arr[i][j] = s.charAt(j) - '0';
                }
            }
//            StringBuilder sb = new StringBuilder();
//            for(int i=0; i<2; i++) {
//                for(int j=0; j<n; j++) {
//                    sb.append(arr[i][j]).append(" ");
//                }
//                sb.append("\n");
//            }
//            System.out.println(sb);
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}