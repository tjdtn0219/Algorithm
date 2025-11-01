import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int a, b;
        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static int n, m;
    static int[] arr;
    static List<Node> nodeList;
    static int[] minTree;
    static int[] maxTree;
    static int min, max;

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
            min = Integer.MAX_VALUE;
            max = -1;
            findMin(1, n, 1, a, b);
            findMax(1, n, 1, a, b);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }


    public static void findMin(int st, int en, int node, int left, int right) {
        if(left > en || right < st) return ;
        if(left <= st && en <= right) {
            min = Math.min(min, minTree[node]);
            return ;
        }
        int mid = (st + en) / 2;
        findMin(st, mid, node*2, left, right);
        findMin(mid+1, en, node*2 + 1, left, right);
    }

    public static void findMax(int st, int en, int node, int left, int right) {
        if(left > en || right < st) return ;
        if(left <= st && en <= right) {
            max = Math.max(max, maxTree[node]);
            return ;
        }
        int mid = (st + en) / 2;
        findMax(st, mid, node*2, left, right);
        findMax(mid+1, en, node*2 + 1, left, right);
    }

    public static void initMinTree(int st, int en, int node) {
        if(st == en) minTree[node] = arr[st];
        else {
            int mid = (st + en) / 2;
            initMinTree(st, mid, node * 2);
            initMinTree(mid+1, en, node * 2 + 1);
            minTree[node] = Math.min(minTree[node*2], minTree[node*2 + 1]);
        }
    }

    public static void initMaxTree(int st, int en, int node) {
        if(st == en) maxTree[node] = arr[st];
        else {
            int mid = (st + en) / 2;
            initMaxTree(st, mid, node * 2);
            initMaxTree(mid+1, en, node * 2 + 1);
            maxTree[node] = Math.max(maxTree[node*2], maxTree[node*2 + 1]);
        }
    }

    public static void initTree() {
        int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, h) - 1;
        minTree = new int[size + 1];
        maxTree = new int[size + 1];
        initMinTree(1, n, 1);
        initMaxTree(1, n, 1);
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_m = br.readLine().split(" ");
            n = Integer.parseInt(n_m[0]);
            m = Integer.parseInt(n_m[1]);
            arr = new int[n+1];
            for(int i=0; i<n; i++) {
                arr[i+1] = Integer.parseInt(br.readLine());
            }
            nodeList = new ArrayList<>();
            for(int i=0; i<m; i++) {
                String[] a_b_c = br.readLine().split(" ");
                int a = Integer.parseInt(a_b_c[0]);
                int b = Integer.parseInt(a_b_c[1]);
                nodeList.add(new Node(a, b));
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}