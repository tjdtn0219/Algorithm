import java.io.*;
import java.util.*;

public class Main {

    List<Ball> balls;
    int n;

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
            n = Integer.parseInt(br.readLine());
            balls = new ArrayList<>();
            for(int i=0; i<n; i++) {
                String[] c_s = br.readLine().split(" ");
                balls.add(new Ball(i, Integer.parseInt(c_s[0]), Integer.parseInt(c_s[1])));
            }
            Collections.sort(balls, (o1, o2) -> {
                if(o1.size == o2.size) {
                    return o1.color - o2.color;
                }
                return o1.size - o2.size;
            });
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {

        StringBuilder sb = new StringBuilder();
        int[] answer = new int[n];
        int sum = 0;
        int[] color = new int[n+1];
        for(int i=0, j=0; i<balls.size(); i++) {
            Ball mine = balls.get(i);
            Ball other = balls.get(j);
            while(other.size < mine.size) {
                sum += other.size;
                color[other.color] += other.size;

                other = balls.get(++j);
            }

            answer[mine.idx] = sum - color[mine.color];
        }

        for(int ans : answer) {
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}

class Ball {
    int idx;
    int color;
    int size;
    public Ball(int idx, int color, int size) {
        this.idx = idx;
        this.color = color;
        this.size = size;
    }
}