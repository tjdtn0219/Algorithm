import java.io.*;
import java.util.*;

public class Main {

    public static final int MAX = 200001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        int[][] map = new int[n+1][n+1];
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(i==j) continue;
                map[i][j] = MAX;
            }
        }

        for(int i=0; i<m; i++) {
            strings = br.readLine().split(" ");
            int s = Integer.parseInt(strings[0]);
            int e = Integer.parseInt(strings[1]);
            int c = Integer.parseInt(strings[2]);

            map[s][e] = c;
        }

        int k = Integer.parseInt(br.readLine());
        strings = br.readLine().split(" ");
        int[] city = new int[k+1];
        for(int i=1; i<k+1; i++) {
            city[i] = Integer.parseInt(strings[i-1]);
        }

        /* i->j  의 최소시간 map 구하기(플로이드)ㅌ */
        for(int mid=1; mid<=n; mid++) {
            for(int i=1; i<=n; i++) {
                if(i==mid) continue;
                for(int j=1; j<=n; j++) {
                    if(i==j || j==mid) continue;
                    if(map[i][mid] + map[mid][j] < map[i][j]) map[i][j] = map[i][mid] + map[mid][j];
                }
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)->{
            if(o1.time==o2.time) {
                return o1.city - o2.city;
            }
            return o2.time - o1.time;
        });

//        for(int i=1; i<=n; i++) {
//            for (int j = 1; j <= n; j++)
//                System.out.print(map[i][j] + " ");
//            System.out.println();
//        }


        int[] max = new int[n + 1];
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                max[i] = Math.max(max[i], map[city[j]][i] + map[i][city[j]]);
            }
            min = Math.min(min, max[i]);
        }

        //최소값으로 갈 수 있는 도시 찾음.
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(min >= max[i]) result.add(i);
        }
        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        for(int c : result) {
            sb.append(c + " ");
        }
        System.out.println(sb.toString());
    }
}

class Pair {
    int city;
    int time;

    public Pair(int city, int time) {
        this.city = city;
        this.time = time;
    }
}