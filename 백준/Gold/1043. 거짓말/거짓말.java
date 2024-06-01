import java.io.*;
import java.util.*;

public class Main {

    static int MAX_LEN = 51;

    int n, m, k;
    boolean[] isKnowTrue;
    List<List<Integer>> parties;
    int[] parent;
    int answer;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
        System.out.println(answer);
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            isKnowTrue = new boolean[MAX_LEN];
            tmp = br.readLine().split(" ");
            k = Integer.parseInt(tmp[0]);
            for(int i=0; i<k; i++) {
                int num = Integer.parseInt(tmp[i+1]);
                isKnowTrue[num] = true;
            }
            parties = new ArrayList<>();
            for(int i=0; i<m; i++) parties.add(new ArrayList<>());
            parent = new int[MAX_LEN];
            for(int i=0; i<MAX_LEN; i++) parent[i] = i;
            for(int i=0; i<m; i++) {
                tmp = br.readLine().split(" ");
                int len = Integer.parseInt(tmp[0]);
                for(int j=0; j<len; j++) {
                    parties.get(i).add(Integer.parseInt(tmp[j + 1]));
                }
                Collections.sort(parties.get(i));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        fillParentArr();
//        for(int i=1; i<=n; i++) {
//            System.out.println("parent[" + i + "] : " + parent[i]);
//        }
        fillIsKnowTrue();
        findFalseParty();
    }

    public void fillParentArr() {
        for(List<Integer> party : parties) {
            int head = party.get(0);
            for(int i=0; i<party.size(); i++) {
                union(head, party.get(i));
            }
        }
    }

    public void fillIsKnowTrue() {
        for(int i=0; i<MAX_LEN; i++) {
            if(isKnowTrue[i]) {
                int v = find(i);
                isKnowTrue[v] = true;
            }
        }
    }

    public int find(int x) {
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    public void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u < v) {
            parent[v] = u;
        } else {
            parent[u] = v;
        }
    }

    public void findFalseParty() {
        for(List<Integer> party : parties) {
            int root = find(party.get(0));
            if(!isKnowTrue[root]) {
                answer++;
            }
        }
    }


}