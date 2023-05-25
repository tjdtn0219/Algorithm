import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int MAX_VALUE = 100000;
    public static int n,k;
    public static int dx[] = {-1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strings = bf.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        k = Integer.parseInt(strings[1]);

        int[] dis = new int[100005];
        for(int i=0; i<dis.length; i++) dis[i] = -1;

        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        dis[n] = 0;

        while(!q.isEmpty()) {
            int x = q.poll();
            if(x==k) break;

            int tmp = x*2;
            while(tmp<=MAX_VALUE) {
                if(tmp==0) break;
                if(dis[tmp]<0) {
                    dis[tmp] = dis[x];
                    q.add(tmp);
                    if(tmp==k) break;
                }
                tmp*=2;
            }

            for(int dir=0; dir<2; dir++) {
                int nx = x + dx[dir];
                if(nx<0 || nx>=MAX_VALUE) continue;
                if(dis[nx] >= 0) continue;
                dis[nx] = dis[x]+1;
                q.add(nx);
            }
        }

        System.out.println(dis[k]);
    }
}