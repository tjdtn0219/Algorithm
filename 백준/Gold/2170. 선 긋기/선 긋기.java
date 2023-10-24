import java.util.*;
import java.io.*;

public class Main {

    public static long LEN = 1000000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][] arr = new long[n][2];
        for(int i=0; i<n; i++) {
            String[] strings = br.readLine().split(" ");
            arr[i][0] = Long.parseLong(strings[0]);
            arr[i][1] = Long.parseLong(strings[1]);
        }

        Arrays.sort(arr, (o1,o2)-> {
            if(o1[0]==o2[0]) return (int) (o1[1]-o2[1]);
            return (int) (o1[0]-o2[0]);
        });

        long ans = 0;
        long cur = -1000000002;
        for(long[] line : arr) {
//            System.out.print(line[0] + " , " + line[1]);
//            if(cur >= line[1]) {
//                //nothing
//            } else if(cur < line[y] && cur > line[0]) {
//                ans += line[1] - cur;
//            } else {
//                ans += line[1] - line[0];
//            }
            
            if (cur < line[0]) {
                ans += line[1] - line[0];
                cur = line[1];
            } else if (cur >= line[1]) {
                //nothing
            } else {
                ans += line[1]-cur;
                cur = line[1];
            }
            
//            System.out.println(" cur : " + cur + ", ans : " + ans);
        }
        System.out.println(ans);

    }
}