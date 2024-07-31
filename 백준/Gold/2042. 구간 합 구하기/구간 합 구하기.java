import java.io.*;
import java.util.*;

public class Main {

    int N, M, K;
    long[] arr;
    List<String> inputList;
    long[] tree;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        initTree();
        solve();
    }


    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] N_M_K = br.readLine().split(" ");
            N = Integer.parseInt(N_M_K[0]);
            M = Integer.parseInt(N_M_K[1]);
            K = Integer.parseInt(N_M_K[2]);
            arr = new long[N+1];
            for(int i=0; i<N; i++) {
                arr[i+1] = Long.parseLong(br.readLine());
            }
            inputList = new ArrayList<>();
            for(int i=0; i<M+K; i++) {
                inputList.add(br.readLine());
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void initTree() {
        tree = new long[4*N];
        initTreeDfs(1, N, 1);
    }

    public long initTreeDfs(int st, int en, int node) {
        if(st == en) return tree[node] = arr[st];
        int mid = (st + en) / 2;
        return tree[node] = initTreeDfs(st, mid, node * 2) + initTreeDfs(mid+1, en, node*2 + 1);
    }

    public void update(int st, int en, int node, int idx, long diff) {
        if(idx < st || idx > en) return ;
        tree[node] += diff;
        if(st == en) return ;
        int mid = (st + en) / 2;
        update(st, mid, node*2, idx, diff);
        update(mid+1, en, node*2 + 1, idx, diff);
    }

    public long sum(int st, int en, int node, int left, int right) {
        if(left > en || right < st) return 0;
        if(left <= st && en <= right) return tree[node];
        int mid = (st + en) / 2;
        return sum(st, mid, node*2, left, right) + sum(mid+1, en, node*2 + 1, left, right);
    }

    public void solve() {
        List<Long> answerList = new ArrayList<>();
        for(String input : inputList) {
            String[] tmp = input.split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            long c = Long.parseLong(tmp[2]);
            if(a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, diff);
            } else {
                answerList.add(sum(1, N, 1, b, (int) c));
            }
        }
        for(long answer : answerList) {
            System.out.println(answer);
        }
    }

}

