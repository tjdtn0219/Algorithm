import java.io.*;
import java.util.*;

public class Main {

    int n;
    List<Egg> eggList;
    boolean[] broken;
    int cnt;
    int answer;

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
            eggList = new ArrayList<>();
            for(int i=0; i<n; i++) {
                String[] tmp = br.readLine().split(" ");
                int s = Integer.parseInt(tmp[0]);
                int w = Integer.parseInt(tmp[1]);
                eggList.add(new Egg(i, s, w));
            }
            broken = new boolean[n];
            cnt = 0;
            answer = 0;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int i=0; i<eggList.size(); i++) {
//            System.out.println("i : " + i + ", egg.idx : " + eggList.get(i).idx);
        }
        dfs(0);
        System.out.println(answer);
    }

    public void dfs(int curIdx) {

        if(curIdx==n) {
//            cnt = 0;
//            for(int i=0; i<n; i++) {
//                if(broken[i]) cnt++;
//            }
            answer = Math.max(answer, cnt);
            return ;
        }

        if(broken[curIdx] || cnt==n-1) {
            dfs(curIdx+1);
            return;
        }

        for(int i=0; i<n; i++) {
            if(i==curIdx) continue;
            if(!broken[i]) {
                crush(eggList.get(curIdx), eggList.get(i));
                dfs(curIdx+1);
                rollBack(eggList.get(curIdx), eggList.get(i));

            }
        }
    }

    public void rollBack(Egg e1, Egg e2) {
        e1.s += e2.w;
        e2.s += e1.w;
        if(broken[e1.idx]) {
            broken[e1.idx] = false;
            cnt--;
        }
        if(broken[e2.idx]) {
            broken[e2.idx] = false;
            cnt--;
        }
    }

    public void crush(Egg e1, Egg e2) {
        e1.s -= e2.w;
        e2.s -= e1.w;
        if(e1.s <= 0) {
            broken[e1.idx] = true;
            cnt++;
        }
        if(e2.s <= 0) {
            broken[e2.idx] = true;
            cnt++;
        }
    }
}

class Egg {
    int idx;
    int s;
    int w;
    public Egg(int idx, int s, int w) {
        this.idx = idx;
        this.s = s;
        this.w = w;
    }
}

