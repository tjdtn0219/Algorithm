import java.util.*;
import java.io.*;

public class Main {

    public static int n;
    public static List<Egg> eggList = new ArrayList<>();
    public static boolean[] broken;
    public static int ans = 0;
    public static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        broken = new boolean[n];
        for(int i=0; i<n; i++) {
            String[] strings = br.readLine().split(" ");
            int s = Integer.parseInt(strings[0]);
            int w = Integer.parseInt(strings[1]);
            eggList.add(new Egg(s, w));
        }

        btk(0);

        System.out.println(ans);

    }

    public static void btk(int k) {

        if(k==n) {
            ans = Math.max(ans, cnt);
            return ;
        }

        if(broken[k] || cnt==n-1) {
            btk(k+1);
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
                btk(k+1);
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
