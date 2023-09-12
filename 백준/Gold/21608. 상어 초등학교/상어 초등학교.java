import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int[] dx = {-1,0,0,1};
    public static int[] dy = {0,-1,1,0};
    public static int n;
    public static int[][] map;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
//        adjCnt = new int[n+1][n+1];


        arr = new int[n*n+1][5];
        for(int i=0; i<n*n; i++) {
            String[] strings = br.readLine().split(" ");
            for(int j=0; j<5; j++) {
                arr[i+1][j] = Integer.parseInt(strings[j]);
            }
        }

        for(int s=1; s<=n*n; s++) {
            List<Pair> list = new ArrayList<>();
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(map[i][j]>0) continue;
                    list.add(searchAdj(i,j,arr[s]));
                }
            }
            Collections.sort(list, (o1,o2)-> {
                if(o1.adjLike == o2.adjLike) {
//                    if(o2.adjEmpty==o2.adjEmpty) {
//                        if(o1.x==o2.x) return o1.y-o2.y;
//                        return o1.x - o2.x;
//                    }
                    return o2.adjEmpty - o1.adjEmpty;
                }
                return o2.adjLike - o1.adjLike;
            });
            Pair temp = list.get(0);
//            System.out.println("x : " + temp.x + " y : " + temp.y + " empty : " + temp.adjEmpty +
//                    " likes : " + temp.adjLike);
            map[temp.x][temp.y] = arr[s][0];
        }

        int ans = getSatisfy();
        System.out.println(ans);

    }

    public static int getSatisfy() {
        int score = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                int cnt = 0;
                int[] arr = getLikes(map[i][j]);
                for(int dir=0; dir<4; dir++) {
                    int ni = i + dx[dir];
                    int nj = j + dy[dir];
                    if(ni<1 || nj<1 || ni>n || nj>n) continue;
                    for(int s=1; s<arr.length; s++) {
                        if (map[ni][nj]==arr[s]) cnt++;
                    }
                }
                if(cnt<2) score += cnt;
                else if(cnt==2) score += 10;
                else if(cnt==3) score += 100;
                else if(cnt==4) score += 1000;
            }
        }
        return score;
    }

    public static int[] getLikes(int num) {
        for(int i=1; i<=n*n; i++) {
            if(arr[i][0]==num) return arr[i];
        }
        return null;
    }

    public static Pair searchAdj(int x, int y, int[] arr) {
        Pair pair = new Pair(x, y);
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(nx<1 || ny<1 || nx>n || ny>n) continue;
            if(map[nx][ny]==0) pair.adjEmpty++;
            for(int i=1; i<arr.length; i++) {
                if (map[nx][ny]==arr[i]) pair.adjLike++;
            }
        }
        return pair;
    }
}

class Pair {
    int x;
    int y;
    int adjEmpty = 0;
    int adjLike = 0;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

