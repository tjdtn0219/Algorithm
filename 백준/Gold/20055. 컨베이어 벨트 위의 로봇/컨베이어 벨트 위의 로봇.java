import java.io.*;
import java.util.*;

public class Main {

    int n, k;
    LinkedList<Container> belts;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            k = Integer.parseInt(tmp[1]);
            belts = new LinkedList<>();
            tmp = br.readLine().split(" ");
            for(int i=0; i<2*n; i++) {
                int durability = Integer.parseInt(tmp[i]);
                belts.add(new Container(durability, false));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        doStep();
    }

    public void doStep() {
        int step = 0;
        while(k>0) {
//            printBelts();
            rotateBelt();
            moveRobot();
            putRobot();
            step++;
        }
        System.out.println(step);
    }

    public void rotateBelt() {
        Container removed = belts.removeLast();
        belts.addFirst(removed);
        belts.get(n-1).isRobot = false;
    }

    public void moveRobot() {
        for(int i=n-2; i>=0; i--) {
            if(belts.get(i).isRobot && belts.get(i+1).durability > 0 && !belts.get(i+1).isRobot) {
                belts.get(i).isRobot = false;
                belts.get(i+1).isRobot = true;
                belts.get(i+1).durability--;
                if(belts.get(i+1).durability == 0) k--;
            }
        }
        belts.get(n-1).isRobot = false;
    }

    public void putRobot() {
        if(belts.get(0).durability > 0) {
            belts.get(0).isRobot = true;
            belts.get(0).durability--;
            if(belts.get(0).durability == 0) k--;
        }
    }

    public void printBelts() {
        for(Container cont : belts) {
            System.out.print(cont.durability + " ");
        }
        System.out.println();
    }

}

class Container {
    int durability;
    boolean isRobot;
    public Container(int durability, boolean isRobot) {
        this.durability = durability;
        this.isRobot = isRobot;
    }
}