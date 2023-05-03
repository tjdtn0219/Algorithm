import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        int[][] arr = new int[n][2];
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<n; i++) {
            String[] strings = bf.readLine().split(" ");
            arr[i][0] = dateToAmount(strings[0], strings[1]);
            arr[i][1] = dateToAmount(strings[2], strings[3]);
        }

        Arrays.sort(arr, (o1, o2)->{
            if(o1[0]==o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        int date_3_1 = dateToAmount("3", "1");
        int date_11_30 = dateToAmount("11", "30");
//        System.out.println("3/1 : " + date_3_1 + ", 11/30 : " + date_11_30);
//        for(int i=0; i<n; i++) {
//            System.out.println(arr[i][0] + " " + arr[i][1]);
//        }

        int ans = 0;
        int nxt_d = date_3_1;
        int max_d = 0;
        for(int i=0; i<n; i++) {
            int st = arr[i][0];
            int en = arr[i][1];

            if(st > nxt_d) {
                nxt_d = max_d;
                res.add(max_d);
            }
            if(st<=nxt_d && nxt_d<en) {
                if(en>date_11_30) {
                    res.add(en);
                    break;
                }
                max_d = Math.max(max_d, en);
                continue;
            }

        }

        if(res.size() == 0) ans = 0;
        else if(res.get(res.size()-1) <= date_11_30) ans = 0;
        else ans = res.size();
        System.out.println(ans);
    }

    public static int dateToAmount(String month, String day) {
        int m = Integer.parseInt(month);
        int d = Integer.parseInt(day);

        int ans = 0;
        for(int i=1; i<m; i++) {
            if(i==2) {
                ans += 28;
            }
            else if(i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12) ans += 31;
            else ans += 30;
        }
        return ans+d;
    }
}