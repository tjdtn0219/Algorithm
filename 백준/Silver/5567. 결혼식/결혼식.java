import java.util.*;
import java.io.*;

public class Main {

    public static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++) {
            String[] strings = br.readLine().split(" ");
            int u = Integer.parseInt(strings[0]);
            int v = Integer.parseInt(strings[1]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        HashSet<Integer> hSet = new HashSet<>();

        for(int fr : graph.get(1)) {
            hSet.add(fr);
            for(int fr2 : graph.get(fr)) {
                hSet.add(fr2);
            }
        }
        hSet.remove(1);
//        for(int num : hSet) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
        System.out.println(hSet.size());

    }
}