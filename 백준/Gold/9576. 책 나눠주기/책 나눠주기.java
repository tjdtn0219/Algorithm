import java.io.*;
import java.util.*;

public class Main {

    int T;
    int[] nArr;
    int[] mArr;
    HashMap<Integer, List<Node>> abMap;

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
            T = Integer.parseInt(br.readLine());
            nArr = new int[T];
            mArr = new int[T];
            abMap = new HashMap<>();
            for(int t=0; t<T; t++) {
                String[] N_M = br.readLine().split(" ");
                int N = Integer.parseInt(N_M[0]);
                int M = Integer.parseInt(N_M[1]);
                nArr[t] = N;
                mArr[t] = M;
                List<Node> list = new ArrayList<>();
                for(int i=0; i<M; i++) {
                    String[] A_B = br.readLine().split(" ");
                    int a = Integer.parseInt(A_B[0]);
                    int b = Integer.parseInt(A_B[1]);
                    list.add(new Node(a, b));
                }
                abMap.put(t, list);
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }


    public void solve() {
        for(int t=0; t<T; t++) {
            int N = nArr[t];
            int M = mArr[t];
            List<Node> abList = abMap.get(t);
            boolean[] isRent = new boolean[N+1];

            Collections.sort(abList, (o1, o2) -> {
                if(o1.b == o2.b) {
                    return o1.a - o2.a;
                }
                return o1.b - o2.b;
            });

            int ans = 0;
            for(Node node : abList) {
                int a = node.a;
                int b = node.b;
                for(int i=a; i<=b; i++) {
                    if(isRent[i]) continue;
                    isRent[i] = true;
                    ans++;
                    break;
                }
            }

            System.out.println(ans);
            
        }
    }

}

class Node {
    int a;
    int b;
    public Node(int a, int b) {
        this.a = a;
        this.b = b;
    }
}


