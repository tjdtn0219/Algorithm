import java.io.*;
import java.util.*;

public class Main {

    public static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int max = Integer.parseInt(strings[1]);

        List<Delivery> list = new ArrayList<>();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            strings = br.readLine().split(" ");
            int s = Integer.parseInt(strings[0]);
            int d = Integer.parseInt(strings[1]);
            int w = Integer.parseInt(strings[2]);
            list.add(new Delivery(s,d,w));
        }

        Collections.sort(list, (o1,o2) -> {
            if(o1.dest==o2.dest) return o2.source-o1.source;
            return o1.dest - o2.dest;
        });

        int ans = 0;
        int[] weights = new int[n+1];
        for(Delivery del : list) {
            boolean flag = false;
            int over = 0;
            for(int i=del.source; i<=del.dest; i++) {
                if(weights[i] + del.w > max) {
                    over = Math.max(over, weights[i] + del.w - max);
                }
            }
            for(int i=del.source; i<=del.dest-1; i++) {
                weights[i] += del.w - over;
            }
            ans += del.w-over;
//            System.out.print("add : " + (del.w-over) +" || ");
//            for(int i=1; i<=n; i++) {
//                System.out.print(weights[i] + " ");
//            }
//            System.out.println();
        }
//        for(int i=1; i<=n; i++) {
//            System.out.print(weights[i] + " ");
//        }
//        System.out.println();

        System.out.println(ans);

    }
}

class Delivery {
    int source;
    int dest;
    int w;
    public Delivery(int source, int dest, int w) {
        this.source = source;
        this.dest = dest;
        this.w = w;
    }
}