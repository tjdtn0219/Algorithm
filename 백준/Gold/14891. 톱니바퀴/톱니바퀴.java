import java.io.*;
import java.util.*;

public class Main {

    static final int N = 0;
    static final int S = 1;

    int[][] gears;
    int[] tops;
    int k;
    int[][] commands;
    boolean[] states;

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
            gears = new int[4][8];
            tops = new int[4];
            for(int i=0; i<4; i++) {
                String s = br.readLine();
                for(int j=0; j<8; j++) {
                    gears[i][j] = s.charAt(j) - '0';
                }
            }
            k = Integer.parseInt(br.readLine());
            commands = new int[k][2];
            for(int i=0; i<k; i++) {
                String[] tmp = br.readLine().split(" ");
                commands[i][0] = Integer.parseInt(tmp[0]);
                commands[i][1] = Integer.parseInt(tmp[1]);
            }
            states = new boolean[3];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void initState() {
        //true 같은 극, false 다른 극
        for(int i=0; i<3; i++) {
            int idx1 = (tops[i] + 2) % 8;
            int idx2 = (tops[i+1] + 6) % 8;
//            System.out.println("i -> : " + gears[i][idx1] + ", i+1 <- : " + gears[i+1][idx2]);
            if(gears[i][idx1] == gears[i+1][idx2]) states[i] = true;
            else states[i] = false;
        }
    }

    public void solve() {
//        for(boolean t : states) {
//            System.out.println(t);
//        }
        initState();
//        for(boolean t : states) {
//            System.out.print(t + " ");
//        }
//        System.out.println("\n============");

        for(int[] command : commands) {
            int idx = command[0] - 1;
            int dir = command[1];
            rotate(idx, dir, new boolean[4]);
            initState();
//            for(boolean t : states) {
//                System.out.print(t + " ");
//            }
//            System.out.println("\n================");
        }
        getAnswer();

    }

    public void getAnswer() {
        int answer = 0;
        int point = 1;
        for(int i=0; i<4; i++) {
            if(gears[i][tops[i]]  == S) {
                answer += point;
            }
            point *= 2;
        }
        System.out.println(answer);
    }

    public void printStates() {
        for(int i=0; i<3; i++) {
            System.out.println("states[" + i + "] : " + states[i]);
        }
    }

    public void rotate(int idx, int dir, boolean[] vis) {
//        System.out.println(idx + "번째 톱니바퀴 회전, 방향 : " + dir);
        vis[idx] = true;
        rotateOneGear(idx, dir);
//        printStates();

        if(idx-1 >= 0 && !states[idx-1] && !vis[idx-1]) {
            rotate(idx-1, -dir, vis);
        }

        if(idx <= 2 && !states[idx] && !vis[idx+1]) {
           rotate(idx+1, -dir, vis);
        }
    }

    public void rotateOneGear(int idx, int dir) {
        if(dir == 1) {
            tops[idx]--;
        } else {
            tops[idx]++;
        }
        tops[idx] = (tops[idx] + 8) % 8;
    }

}