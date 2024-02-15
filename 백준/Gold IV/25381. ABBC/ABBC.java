import java.io.*;
import java.util.*;

public class Main {

    String input;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        init();
        System.out.println(solve());
    }

    public void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = br.readLine();
        } catch (Exception e) {
            System.out.println("INPUT ERROR");
            throw new RuntimeException(e);
        }

    }

    public int solve() {
        int cnt = 0;
        Queue<Integer> bIdxQueue = new LinkedList<>();
        HashSet<Integer> usedBIdx = new HashSet<>();
        for(int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if(c == 'B') {
                bIdxQueue.add(i);
            } else if(c == 'C' && !bIdxQueue.isEmpty()) {
                if(i > bIdxQueue.peek()) {
                    bIdxQueue.poll();
                    cnt++;
                }
            }
        }

        for(int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if(c == 'A') {
                while(!bIdxQueue.isEmpty()) {
                    if(i < bIdxQueue.peek()) break;
                    bIdxQueue.poll();
                }
                if(!bIdxQueue.isEmpty() && i < bIdxQueue.peek()) {
                    bIdxQueue.poll();
                    cnt++;
                }
            }
        }

        return cnt;
    }

}