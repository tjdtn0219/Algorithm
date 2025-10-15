import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Stone {
    int a, b, c;
    public Stone(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

public class Main {

    static int a, b, c;
    static boolean[][] vis;
    static int size;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        if(size % 3 != 0) {
            System.out.println(0);
            return ;
        }

        if(bfs(a, b, c)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

//    static boolean bfs(int a, int b, int c){
//        Queue<Stone> queue = new LinkedList<>();
//        vis[a][b] = true;	//[A][B] 방문
//        vis[b][c] = true;	//[B][C] 방문
//        vis[a][c] = true;	//[A][C] 방문
//        queue.add(new Stone(a,b,c));
//        while(!queue.isEmpty()){
//            Stone cur = queue.poll();
//            int A = cur.a;
//            int B = cur.b;
//            int C = cur.c;
//            if(A==B &&  B==C)	//모두 같은 무게일 때
//                return true;
//            stoneMove(queue, A,B,C,1);	//A->B
//            stoneMove(queue, A,C,B,2);	//A->C
//            stoneMove(queue, B,C,A,3);	//B->C
//        }
//        return false;
//
//    }

    public static boolean bfs(int a, int b, int c) {
        Queue<Stone> q = new LinkedList<>();
        vis[a][b] = true;
        vis[b][c] = true;
        vis[c][a] = true;
        q.add(new Stone(a, b, c));

        while(!q.isEmpty()){
            Stone cur = q.poll();
            int A = cur.a;
            int B = cur.b;
            int C = cur.c;
            if(A==B &&  B==C)	//모두 같은 무게일 때
                return true;
            stoneMove(q, A,B,C,1);	//A->B
            stoneMove(q, A,C,B,2);	//A->C
            stoneMove(q, B,C,A,3);	//B->C
        }
        return false;
    }

    public static void stoneMove(Queue<Stone> queue, int stone1, int stone2,int remainder, int check){
        //무게가 더 작은 그룹으로 돌 이동하기 위해 그룹별 무게 비교
        if(stone1>stone2){
            stone1 -= stone2;
            stone2 += stone2;
        }else{
            stone2 -= stone1;
            stone1 += stone1;
        }
        if(check==1){	//A->B
            if(!vis[stone1][stone2]){
                vis[stone1][stone2] = true;
                queue.add(new Stone(stone1, stone2, remainder));
            }
        }else if(check==2){		//A->C
            if(!vis[stone1][stone2]){
                vis[stone1][stone2] = true;
                queue.add(new Stone(stone1, remainder, stone2));
            }
        }
        else{		//B->C
            if(!vis[stone1][stone2]){
                vis[stone1][stone2] = true;
                queue.add(new Stone(remainder, stone1, stone2));
            }
        }
        return;
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            a = Integer.parseInt(tmp[0]);
            b = Integer.parseInt(tmp[1]);
            c = Integer.parseInt(tmp[2]);
            size = a + b + c;
            vis = new boolean[size + 1][size + 1];
        } catch(Exception e) {
            throw new RuntimeException("INPUT ERROR");
        }
    }
}