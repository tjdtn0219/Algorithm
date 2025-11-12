import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Cmd {
        int op;
        int key;
        int val;
        public Cmd(int op, int key, int val) {
            this.op = op;
            this.key = key;
            this.val = val;
        }
    }

    static int n, m, k;
    static TreeMap<Integer, Integer> map;
    static List<Cmd> cmdList;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        StringBuilder sb = new StringBuilder();
        for(Cmd cmd : cmdList) {
            int op = cmd.op;
            int key = cmd.key;
            int val = cmd.val;
//            System.out.println("op : " + op + " ,key : " + key + ", val : " + val);

            if(op == 1) {
                map.put(key, val);
            } else if(op == 2) {
                if(map.containsKey(key)) {
                    map.put(key, val);
                    continue;
                }
                int closeKey = getClose(key);
                if(closeKey > 0) {
                    map.put(closeKey, val);
                }
            } else {
                if(map.containsKey(key)) {
                    sb.append(map.get(key)).append("\n");
                    continue;
                }
                int closeKey = getClose(key);
//                System.out.println("closeKey : " + closeKey);
                if(closeKey == -1) {
                    sb.append("-1\n");
                } else if(closeKey == -2) {
                    sb.append("?\n");
                } else {
                    sb.append(map.get(closeKey)).append("\n");
                }
            }
        }

        System.out.println(sb);
    }

    public static int getClose(int key) {
        Integer key1 = map.ceilingKey(key);
        Integer key2 = map.floorKey(key);
//        System.out.println("key1 : " + key1 + ", key2 : " + key2);

        if(key1 == null && key2 == null) {
            return -1;
        }

        if(key1 == null) {
            if(Math.abs(key - key2) > k) return -1;
            return key2;
        }

        if(key2 == null) {
            if(Math.abs(key - key1) > k) return -1;
            return key1;
        }

        int diff1 = Math.abs(key1 - key);
        int diff2 = Math.abs(key2 - key);
        if(diff1 == diff2) return -2;
        if(diff1 < diff2 && diff1 <= k) {
            return key1;
        }
        if(diff2 < diff1 && diff2 <= k) {
            return key2;
        }

        return -1;
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_m_k = br.readLine().split(" ");
            n = Integer.parseInt(n_m_k[0]);
            m = Integer.parseInt(n_m_k[1]);
            k = Integer.parseInt(n_m_k[2]);
            map = new TreeMap<>();
            for(int i=0; i<n; i++) {
                String[] tmp = br.readLine().split(" ");
                int key = Integer.parseInt(tmp[0]);
                int val = Integer.parseInt(tmp[1]);
                map.put(key, val);
            }
            cmdList = new ArrayList<>();
            for(int i=0; i<m; i++) {
                String[] tmp = br.readLine().split(" ");
                int op = Integer.parseInt(tmp[0]);
                int key = Integer.parseInt(tmp[1]);
                int val = -1;
                if(op < 3) {
                    val = Integer.parseInt(tmp[2]);
                }
                cmdList.add(new Cmd(op, key, val));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}
