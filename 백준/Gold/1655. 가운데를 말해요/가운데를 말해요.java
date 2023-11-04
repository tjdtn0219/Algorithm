import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> left = new PriorityQueue<>((o1,o2)->o2-o1);  //맥스힙
        PriorityQueue<Integer> right = new PriorityQueue<>();  //미니힙

        StringBuilder sb = new StringBuilder();

        //left.peek()가 무조건 중앙값임
        left.add(Integer.parseInt(br.readLine()));
        sb.append(left.peek() + "\n");
        for(int i=1; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            int centerValue = left.peek();
            if(num <= centerValue) {
                left.add(num);
            } else {
                right.add(num);
            }

            if(left.size() > right.size()+1) {
                right.add(left.poll());
            }

            if(left.size() < right.size()) {
                left.add(right.poll());
            }
            sb.append(left.peek() + "\n");
        }
        System.out.print(sb);

    }
}