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
                eggList.add(new Egg(s, w));
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
        dfs(0);
        System.out.println(answer);
    }

    public void dfs(int k) {

        if(k==n) {
            answer = Math.max(answer, cnt);
            return ;
        }

        if(broken[k] || cnt==n-1) {
            dfs(k+1);
            return;
        }

        for(int i=0; i<n; i++) {
            if(i==k) continue;
            if(!broken[i]) {
                crush(eggList.get(k), eggList.get(i));
                if(eggList.get(k).s <= 0) {
                    broken[k] = true;
                    cnt++;
                }
                if(eggList.get(i).s <= 0) {
                    broken[i] = true;
                    cnt++;
                }
                dfs(k+1);
                rollBack(eggList.get(k), eggList.get(i));
                if(broken[k]) {
                    broken[k] = false;
                    cnt--;
                }
                if(broken[i]) {
                    broken[i] = false;
                    cnt--;
                }

            }
        }
//        System.out.println("k: " + k + " cnt: " + cnt);
    }

    public static void rollBack(Egg e1, Egg e2) {
        e1.s += e2.w;
        e2.s += e1.w;
    }

    public static void crush(Egg e1, Egg e2) {
        e1.s -= e2.w;
        e2.s -= e1.w;
    }
}

class Egg {
    int s;
    int w;
    public Egg(int s, int w) {
        this.s = s;
        this.w = w;
    }
}


//    public void dfs(int curIdx) {
////        System.out.println("curIdx : " + curIdx);
//
//        if(curIdx == n) {
//            int cnt = 0;
//            for(int i=0; i<n; i++) {
//                if(isBroken[i]) cnt++;
//            }
////            System.out.println("cnt : " + cnt);
//            answer = Math.max(answer, cnt);
//            return ;
//        }
//
//        if(isBroken[curIdx]) {
//            dfs(curIdx + 1);
//        }
//
//        for(int i=0; i<n; i++) {
//            if(curIdx == i) continue;
//            if(isBroken[i]) continue;
////            System.out.println("i : " + i);
//            crush(eggList.get(curIdx), eggList.get(i));
//            dfs(curIdx+1);
//            rollback(eggList.get(curIdx), eggList.get(i));
//        }
//    }
//
//    public void crush(Egg egg1, Egg egg2) {
//        egg1.s -= egg2.w;
//        egg2.s -= egg1.w;
//        if(egg1.s <= 0) isBroken[egg1.idx] = true;
//        if(egg2.s <= 0) isBroken[egg2.idx] = true;
//    }
//
//    public void rollback(Egg egg1, Egg egg2) {
//        egg1.s += egg2.w;
//        egg2.s += egg1.w;
//        if(egg1.s > 0) isBroken[egg1.idx] = false;
//        if(egg2.s > 0) isBroken[egg2.idx] = false;
//    }
//
//}
//
//class Egg {
//    int idx;
//    int s;
//    int w;
//
//    public Egg(int idx, int s, int w) {
//        this.idx = idx;
//        this.s = s;
//        this.w = w;
//    }
//}