import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        Deque<Integer> dq = new LinkedList<>();
        for(int i=1; i<=n; i++) dq.addLast(i);

        strings = br.readLine().split(" ");
        int[] arr = new int[m];
        for(int i=0; i<m; i++) arr[i] = Integer.parseInt(strings[i]);

        int ans = 0;
        for(int num : arr) {
            Iterator iter = dq.iterator();

            int cntL = 0;
            int cntR = 0;
            while(iter.hasNext()) {
                if(iter.next().equals(num)) break;
                cntL++;
            }
            cntR = dq.size() - cntL;

            if(cntR > cntL) {
                shiftL(dq, cntL);
                ans += cntL;
            } else {
                shiftR(dq, cntR);
                ans += cntR;
            }
            dq.pollFirst();

        }

        System.out.println(ans);

    }

    public static void shiftR(Deque<Integer> dq, int n) {
        for(int i=0; i<n; i++) dq.addFirst(dq.pollLast());
    }

    public static void shiftL(Deque<Integer> dq, int n) {
        for(int i=0; i<n; i++) dq.addLast(dq.pollFirst());
    }
}
