import java.io.*;
import java.util.*;


public class Main {

    public static final int INF = 50_000_000;

    BufferedReader br;

    int T;
    int N, M, W;
    List<List<Node>> graph;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        inputT();
        for(int t=0; t<T; t++) {
            input();
            solve();
        }
    }


    public void inputT() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void input() {
        try {
            String[] N_M_W = br.readLine().split(" ");
            N = Integer.parseInt(N_M_W[0]);
            M = Integer.parseInt(N_M_W[1]);
            W = Integer.parseInt(N_M_W[2]);
            // System.out.println(N + " " + M);
            graph = new ArrayList<>();
            for(int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<M; i++) {
                int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                // System.out.println(tmp[0] + "," + tmp[1] + "," + tmp[2]);
                graph.get(tmp[0]).add(new Node(tmp[1], tmp[2]));
                graph.get(tmp[1]).add(new Node(tmp[0], tmp[2]));
            }
            for(int i=0; i<W; i++) {
                int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                // System.out.println(tmp[0] + "," + tmp[1] + "," + tmp[2]);
                graph.get(tmp[0]).add(new Node(tmp[1], -tmp[2]));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }


    public void solve() {
        for(int i=1; i<=N; i++) {
            int[] dist = new int[N+1];
            Arrays.fill(dist, INF);
            if (bellmanFord(i, dist)) {
                System.out.println("YES");
                return ;
            }
        }
        System.out.println("NO");
        
    }

    public boolean bellmanFord(int st, int[] dist) {
        dist[st] = 0;
        boolean update = false;

        for (int i = 1; i < N; i++) {
            update = false;
            
            for (int j = 1; j <= N; j++) {
                for (Node node : graph.get(j)) {
                    if (dist[j] != INF && dist[node.E] > dist[j] + node.T) {
                        dist[node.E] = dist[j] + node.T;
                        update = true;
                    }
                }
            }
            
            if (!update) {
                break;
            }
        }

        if (update) {
            for (int i = 1; i <= N; i++) {
                for (Node node : graph.get(i)) {
                    if (dist[i] != INF && dist[node.E] > dist[i] + node.T) {
                        return true;
                    }
                }
            }
        }
    
        return false;
    }
}

class Node {
    int E, T;
    public Node(int E, int T) {
        this.E = E;
        this.T = T;
    }
}