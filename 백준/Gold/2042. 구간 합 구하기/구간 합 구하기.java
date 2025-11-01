import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int a, b;
        long c;
        public Node(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int n, m, k;
    static long[] arr;
    static List<Node> nodeList;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        initTree();
        StringBuilder sb = new StringBuilder();
        for(Node node : nodeList) {
            int a = node.a;
            int b = node.b;
            long c = node.c;
            if(a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, n, 1, b, diff);
            } else {
                long sum = getSum(1, n, 1, b, (int) c);
                sb.append(sum).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void update(int st, int en, int node, int idx, long diff) {
        if(idx < st || idx > en) return ;
        tree[node] += diff;
        if(st == en) return ;
        int mid = (st + en) / 2;
        update(st, mid, node*2, idx, diff);
        update(mid + 1, en, node*2 + 1, idx, diff);
    }

    public static long getSum(int st, int en, int node, int left, int right) {
        if(left > en || right < st) return 0;
        if(left <= st && en <= right) return tree[node];
        int mid = (st + en) / 2;
        return getSum(st, mid, node*2, left, right)
                + getSum(mid+1, en, node*2 + 1, left, right);
    }

    public static long initTreeDfs(int st, int en, int node) {
        if(st == en) return tree[node] = arr[st];
        int mid = (st + en) / 2;
        return tree[node] = initTreeDfs(st, mid, node * 2) + initTreeDfs(mid+1, en, node*2 + 1);
    }

    public static void initTree() {
        int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, h) - 1;
        tree = new long[n*4];
        initTreeDfs(1, n, 1);
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_m_k = br.readLine().split(" ");
            n = Integer.parseInt(n_m_k[0]);
            m = Integer.parseInt(n_m_k[1]);
            k = Integer.parseInt(n_m_k[2]);
            arr = new long[n+1];
            for(int i=0; i<n; i++) {
                arr[i+1] = Long.parseLong(br.readLine());
            }
            nodeList = new ArrayList<>();
            for(int i=0; i<m+k; i++) {
                String[] a_b_c = br.readLine().split(" ");
                int a = Integer.parseInt(a_b_c[0]);
                int b = Integer.parseInt(a_b_c[1]);
                long c = Long.parseLong(a_b_c[2]);
                nodeList.add(new Node(a, b, c));
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}