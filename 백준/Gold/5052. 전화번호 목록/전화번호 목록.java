import java.io.*;
import java.util.*;

public class Main {

    int T;
    int n;
    List<String> list;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        // solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; t++) {
                n = Integer.parseInt(br.readLine());
                list = new ArrayList<>();
                for(int i=0; i<n; i++) {
                    list.add(br.readLine());
                }
                solve();
                // System.out.println();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        Collections.sort(list);

        boolean flag = true;
        for(int i=0; i<n-1; i++) {
            String s1 = list.get(i);
            String s2 = list.get(i+1);
            // System.out.println("s1 : " + s1 + ", s2 : " + s2);
            if(s2.startsWith(s1)) {
                flag = false;
                break;
            }
        }

        if(flag) System.out.println("YES");
        else System.out.println("NO");
        
    }

}